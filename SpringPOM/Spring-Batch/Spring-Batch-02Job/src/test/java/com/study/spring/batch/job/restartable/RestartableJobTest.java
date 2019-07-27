package com.study.spring.batch.job.restartable;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"/applicationContext.xml", "/beans-batch-restartable.xml"})
public class RestartableJobTest {

    @Autowired
    private JobLauncher launcher;

    @Autowired
    private Job helloJob;

    @Autowired
    private Job worldJob;
    
    @Test
    public void testRestartableJob() throws JobExecutionAlreadyRunningException, JobRestartException,
            JobInstanceAlreadyCompleteException, JobParametersInvalidException {

        JobParameters param = new JobParameters();
        JobExecution result1 = launcher.run(helloJob, param);
        System.out.println(result1);
        JobExecution result2 = launcher.run(helloJob, param);
        System.out.println(result2);
    }
    
    @Test(expected=JobRestartException.class)
    public void testUnRestartableJob() throws JobExecutionAlreadyRunningException, JobRestartException,
    JobInstanceAlreadyCompleteException, JobParametersInvalidException {
        
        JobParameters param = new JobParameters();
        JobExecution result1 = launcher.run(worldJob, param);
        System.out.println(result1);
        // 重启将抛出JobRestartException
        JobExecution result2 = launcher.run(worldJob, param);
        System.out.println(result2);
    }
    
    

}
