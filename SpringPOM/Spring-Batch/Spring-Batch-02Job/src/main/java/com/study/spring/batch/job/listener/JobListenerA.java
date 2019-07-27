package com.study.spring.batch.job.listener;

import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;

public class JobListenerA implements JobExecutionListener {

    @Override
    public void beforeJob(JobExecution jobExecution) {
        System.out.println("listener a --> beforeJob");
    }

    @Override
    public void afterJob(JobExecution jobExecution) {
        System.out.println("listener a --> afterJob");
    }

}
