package com.study.spring.batch;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.launch.JobOperator;
import org.springframework.batch.core.launch.NoSuchJobException;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.study.spring.batch.dao.DataExtStatusDao;
import com.study.spring.batch.domain.DataExtStatus;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/applicationContext.xml")
public class PartitionTest {


    @Autowired
    private JobLauncher launcher;

    @Autowired
    private JobOperator jobOperator;
    
    @Autowired
    private Job userJob;

    @Test
    public void test() throws JobExecutionAlreadyRunningException, JobRestartException,
            JobInstanceAlreadyCompleteException, JobParametersInvalidException, InterruptedException, UnknownHostException, NoSuchJobException {

        String host = InetAddress.getLocalHost().getHostAddress();
        /*if (new Random().nextInt(2) == 0) {
            host += "1";
        }*/
        JobParameters jobParams = new JobParametersBuilder()
                .addString("host", host).toJobParameters();

        launcher.run(userJob, jobParams);
        
//        Thread.sleep(Integer.MAX_VALUE);
        Set<String> names = jobOperator.getJobNames();
        System.out.println(names);
        List<Long> instances = jobOperator.getJobInstances("userJob", 0, 1);
        System.out.println(instances);
    }
    
    @Test
    public void testWithMultiThread() throws InterruptedException {
        final List<Integer> list = new ArrayList<Integer>();
        list.add(1);
        for(int i=0; i<2; i++) {
            
            new Thread(new Runnable() {
                @Override
                public void run() {
                    JobParameters jobParams = new JobParametersBuilder().addString("host", "host" + list.get(0))
                            .toJobParameters();
                    list.set(0, list.get(0)+1);
                    try {
                        launcher.run(userJob, jobParams);
                    } catch (JobExecutionAlreadyRunningException | JobRestartException
                            | JobInstanceAlreadyCompleteException | JobParametersInvalidException e) {
                        e.printStackTrace();
                    }
                }

            }).start();
            Thread.sleep(100);
        }
        Thread.sleep(Integer.MAX_VALUE);
    }

    
    @Autowired
    private DataExtStatusDao extDao;
    @Test
    public void testMysql() {
        boolean flag = false;
        do{
            DataExtStatus status = extDao.getTodayStatus();
            status.incrementVersion();
            flag = !extDao.update(status);
            System.out.println(status);
        }while(flag);
    }
}
