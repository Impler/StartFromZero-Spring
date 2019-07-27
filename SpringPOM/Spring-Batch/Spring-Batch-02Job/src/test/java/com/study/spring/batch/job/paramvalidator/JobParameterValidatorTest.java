package com.study.spring.batch.job.paramvalidator;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "/applicationContext.xml", "/beans-batch-validator.xml" })
public class JobParameterValidatorTest {

    @Autowired
    private JobLauncher launcher;

    @Autowired
    private Job helloJob;

    @Test(expected=JobParametersInvalidException.class)
    public void testFailValidate() throws JobExecutionAlreadyRunningException, JobRestartException,
            JobInstanceAlreadyCompleteException, JobParametersInvalidException {

        launcher.run(helloJob, new JobParameters());
    }
    
    @Test
    public void testSuccessValidator() throws JobExecutionAlreadyRunningException, JobRestartException, JobInstanceAlreadyCompleteException, JobParametersInvalidException {
        JobParameters jobParameters = new JobParametersBuilder().addString("sysCode", "test").toJobParameters();
        launcher.run(helloJob, jobParameters);
    }

}
