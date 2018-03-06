package com.study.spring.batch.remote.partition;

import org.springframework.beans.BeansException;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SlaverTest {

	public static void main(String[] args) {
		try {
			ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:beans-slave.xml");
		} catch (BeansException e) {
			e.printStackTrace();
		}
	}

}
