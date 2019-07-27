package com.study.spring.batch.remote.partition;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.batch.core.Job;
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
@ContextConfiguration("classpath:beans-master.xml")
public class MasterTest {

    
    @Autowired
    private JobLauncher jobLauncher;
    
    @Autowired
    private Job userJob;
    
    @Test
    public void test() {
        try {
            jobLauncher.run(userJob, new JobParameters());
        } catch (JobExecutionAlreadyRunningException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (JobRestartException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (JobInstanceAlreadyCompleteException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (JobParametersInvalidException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}
