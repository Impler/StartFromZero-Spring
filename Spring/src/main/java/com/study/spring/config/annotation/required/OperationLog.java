package com.study.spring.config.annotation.required;

import java.util.Date;

import org.springframework.beans.factory.annotation.Required;
import com.study.spring.base.domain.User;

/**
 * @Required
 * 对bean进行依赖检查
 * @author Impler
 * @date 2015年11月23日
 */
public class OperationLog {
	private User operator;
	private Date time = new Date();
	
	public OperationLog() {
		super();
	}
	
	public OperationLog(User operator) {
		this();
		this.operator = operator;
	}

	public User getOperator() {
		return operator;
	}
	
	@Required
	public void setOperator(User operator) {
		this.operator = operator;
	}
	
	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}
	
	public String toString(){
		return this.time + ":" + this.operator;
	}
}
