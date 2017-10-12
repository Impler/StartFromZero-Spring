package com.study.spring.core.ioc.base_config;

/**
 * a pojo in service layer which depends on a BusinessDao instance
 * 
 * @author impler
 * @date 2017-02-27
 */
public class BusinessService {

	private BusinessDao businessDao;

	private String serviceName;

	public BusinessDao getBusinessDao() {
		return businessDao;
	}

	public void setBusinessDao(BusinessDao businessDao) {
		this.businessDao = businessDao;
	}

	public String getServiceName() {
		return serviceName;
	}

	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}

	public void doService(){
		System.out.println("do Service: " + this.serviceName);
		System.out.println("call bussinessDao start");
		this.businessDao.save();
		System.out.println("call bussinessDao end");
	}
}
