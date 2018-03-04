package com.study.spring.batch.job.listener;

import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;

public class JobListenerB implements JobExecutionListener {

    @Override
    public void beforeJob(JobExecution jobExecution) {
        System.out.println("listener b --> beforeJob");
    }

    @Override
    public void afterJob(JobExecution jobExecution) {
        System.out.println("listener b --> afterJob");
    }

}
