package com.study.spring.batch.cos.method;

import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.stereotype.Component;

import com.study.spring.batch.cos.constant.Constants;

@Component
public class CompressMethod implements CosMethod, Tasklet{

    @Override
    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
        System.out.println("---" + getMethodName() + "---");
        return RepeatStatus.FINISHED;
    }

    @Override
    public int getOrder() {
        return Constants.COMPRESS_METHOD_ORDER;
    }

    @Override
    public String getMethodName() {
        return Constants.COMPRESS_METHOD_NAME;
    }

}
