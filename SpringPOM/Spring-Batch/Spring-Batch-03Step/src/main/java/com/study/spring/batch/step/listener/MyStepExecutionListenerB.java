package com.study.spring.batch.step.listener;

import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;

public class MyStepExecutionListenerB implements StepExecutionListener {

    @Override
    public void beforeStep(StepExecution stepExecution) {
        System.out.println("step execution listener b --> beforeStep");
    }

    @Override
    public ExitStatus afterStep(StepExecution stepExecution) {
        System.out.println("step execution listener b --> afterStep");
        return ExitStatus.FAILED;
    }

}
