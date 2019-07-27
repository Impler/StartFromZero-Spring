package com.study.spring.batch.framework.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;

public class StepTraceListener implements StepExecutionListener {

    private static final Logger log = LoggerFactory.getLogger(StepTraceListener.class);

    @Override
    public void beforeStep(StepExecution stepExecution) {
        log.info("before-->" + stepExecution);
    }

    @Override
    public ExitStatus afterStep(StepExecution stepExecution) {
        log.info("after-->stepExecution");
        return ExitStatus.COMPLETED;
    }

}
