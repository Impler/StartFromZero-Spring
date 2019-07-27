package com.study.spring.batch.helloworld;

import org.junit.Test;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestHelloWorld {

	@Test
	public void test() {
		ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext(
				"classpath:applicationContext.xml");

		JobLauncher launcher = (JobLauncher) ctx.getBean("jobLauncher");
		Job job = (Job) ctx.getBean("helloWorldJob");

		try {
			// JobParameters must not be null
			JobParameters params = new JobParameters();
			JobExecution result = launcher.run(job, params);
			System.out.println(result);
		} catch (JobExecutionAlreadyRunningException e) {
			e.printStackTrace();
		} catch (JobRestartException e) {
			e.printStackTrace();
		} catch (JobInstanceAlreadyCompleteException e) {
			e.printStackTrace();
		} catch (JobParametersInvalidException e) {
			e.printStackTrace();
		}finally{
			ctx.close();
		}
	}

}
