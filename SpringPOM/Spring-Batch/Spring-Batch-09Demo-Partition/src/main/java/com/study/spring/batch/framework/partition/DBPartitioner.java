package com.study.spring.batch.framework.partition;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.springframework.batch.core.partition.support.Partitioner;
import org.springframework.batch.core.scope.context.JobSynchronizationManager;
import org.springframework.batch.item.ExecutionContext;

import com.study.spring.batch.dao.UserDao;
import com.study.spring.batch.domain.dto.Pagination;

public class DBPartitioner implements Partitioner {

    public static final String _MINRECORD = "_minRecord";
    public static final String _MAXRECORD = "_maxRecord";

    private UserDao userDao;

    public UserDao getUserDao() {
        return userDao;
    }
    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public Map<String, ExecutionContext> partition(int gridSize) {
        HashMap<String, ExecutionContext> resultMap = new HashMap<String, ExecutionContext>();
        System.out.println(Arrays.asList(JobSynchronizationManager.getContext().attributeNames()));
        Map<String, Object> jobExtContext = JobSynchronizationManager.getContext().getJobExecutionContext();
        int startId = (int) jobExtContext.get("startId");
        int endId = (int) jobExtContext.get("endId");
        int total = (int) jobExtContext.get("dataCount");
        int partitionSize = total / gridSize;
        if(total % gridSize != 0) {
            partitionSize += 1;
        }
        
        int count = 0;
        
        Pagination partitionPage = new Pagination();
        partitionPage.setDataCount(partitionSize);
        partitionPage.setStartId(startId);
        
        while (++count <= gridSize) {
            ExecutionContext context = new ExecutionContext();
            partitionPage = userDao.queryBatchDataRange(partitionPage);
            System.out.println("partition page:" + partitionPage);
            
            context.putInt(_MINRECORD, partitionPage.getStartId());
            
            if(count == gridSize && partitionPage.getEndId() > endId) {
                partitionPage.setEndId(endId);
            }
            
            context.putInt(_MAXRECORD, partitionPage.getEndId());

            resultMap.put("p" + count, context);
            
            partitionPage.setStartId(partitionPage.getEndId() + 1);
            partitionPage.setEndId(-1);
        }
        return resultMap;
    }

}
