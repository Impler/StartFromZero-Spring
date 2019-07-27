package com.study.spring.batch.framework.tasklet;

import java.util.Random;

import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.scope.context.StepContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import com.study.spring.batch.dao.DataExtStatusDao;
import com.study.spring.batch.dao.UserDao;
import com.study.spring.batch.domain.DataExtStatus;
import com.study.spring.batch.domain.dto.Pagination;

public class DataDispatcherTasklet implements Tasklet {

    @Value("${slaveCount}")
    private int slaveCount;

    @Autowired
    private UserDao userDao;
    @Autowired
    private DataExtStatusDao dataExtStatusDao;

    @Override
    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
        
        DataExtStatus dataStatus = dataExtStatusDao.getTodayStatus();
        
        Pagination page = dataStatus.getPage();
        
        System.out.println("data range:" + page);
        // 单台机器需要处理的数据量
        int singleSlaveDataCount = page.getDataCount() / slaveCount;
        if(page.getDataCount() % slaveCount != 0) {
            singleSlaveDataCount += 1;
        }
        StepContext stepContext = chunkContext.getStepContext();

        Pagination batchPage = new Pagination();
        batchPage.setDataCount(singleSlaveDataCount);
        boolean flag = true;
        while(flag) {
            int assignCount = dataStatus.getAssignCount();
            if (assignCount <= 0) {
                assignCount = 0;
                batchPage.setStartId(page.getStartId());
            }else {
             // 计算当前机器处理数据的起始位置
                batchPage.setStartId(page.getLastId() + 1);
            }
            
            batchPage = userDao.queryBatchDataRange(batchPage);
            System.out.println("slave page: " + batchPage);
            // 更新状态
            page.setLastId(batchPage.getEndId());
            dataStatus.setAssignCount(++assignCount);
            dataStatus.incrementVersion();
            Random r = new java.util.Random();
            if(r.nextInt(3) % 2 == 1) {
                Thread.sleep(r.nextInt(100) * 5);
            }
            flag = !dataExtStatusDao.update(dataStatus);
            if(flag) {
                System.out.println("分配失败，重新分配");
                dataStatus = dataExtStatusDao.getTodayStatus();
                System.out.println(dataStatus);
                page = dataStatus.getPage();
                if(r.nextInt(3) % 2 == 1) {
                    Thread.sleep(r.nextInt(200) * 5);
                }
            }
        }
        
        ExecutionContext extContext = stepContext.getStepExecution().getJobExecution().getExecutionContext();

        extContext.putInt("startId", batchPage.getStartId());
        extContext.putInt("endId", batchPage.getEndId());
        extContext.putInt("dataCount", batchPage.getDataCount());

        return RepeatStatus.FINISHED;
    }

}
