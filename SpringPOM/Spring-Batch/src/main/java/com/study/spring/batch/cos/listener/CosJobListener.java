package com.study.spring.batch.cos.listener;

import java.text.SimpleDateFormat;

import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.stereotype.Component;

@Component
public class CosJobListener implements JobExecutionListener {

    private SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss SS");

    @Override
    public void beforeJob(JobExecution jobExecution) {
        System.out.println("任务开始");
        System.out.println("开始时间：" + format.format(jobExecution.getStartTime()));
    }

    @Override
    public void afterJob(JobExecution jobExecution) {
        System.out.println("任务结束");
        System.out.println("结束时间：" + format.format(jobExecution.getEndTime()));
        long timemills = jobExecution.getEndTime().getTime() - jobExecution.getStartTime().getTime();
        System.out.println("耗时：" + timemills + "毫秒");
    }

}
