package com.study.spring.batch.cos.method;

import java.util.Random;

import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.stereotype.Component;

import com.study.spring.batch.cos.constant.Constants;

@Component
public class StartMethod implements CosMethod, Tasklet  {

    public enum ServiceResult{
        NONE, RUNNING, FINISHED, ERROR;
    }
    
    @Override
    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
        System.out.println("---" + getMethodName() + "---");
        ServiceResult serviceResult = queryServiceResult();
        switch (serviceResult) {
        case NONE:
            System.out.println("条件满足，开始执行任务");
            break;
        case ERROR:
            // TODO 异常处理
            System.out.println("上次任务出现异常");
            contribution.setExitStatus(ExitStatus.NOOP);
            break;
        case FINISHED:
            System.out.println("任务已执行过");
            contribution.setExitStatus(ExitStatus.NOOP);
            break;
        case RUNNING:
            System.out.println("已有相同的任务正在执行");
            contribution.setExitStatus(ExitStatus.NOOP);
            break;
        default:
            break;
        }
        chunkContext.getStepContext().getStepExecution();
        return RepeatStatus.FINISHED;
    }

    private ServiceResult queryServiceResult() {
        Random r = new Random();
        int index = r.nextInt(4);
        ServiceResult[] cons = ServiceResult.class.getEnumConstants();
        return cons[index];
    }

    @Override
    public int getOrder() {
        return Constants.START_METHOD_ORDER;
    }

    @Override
    public String getMethodName() {
        return Constants.START_METHOD_NAME;
    }

}
