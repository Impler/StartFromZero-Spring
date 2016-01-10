package com.study.spring.transaction;

import java.sql.SQLException;

public class UserService {

	private IUserDao userDao;
	private ILogDao logDao;
	
	public IUserDao getUserDao() {
		return userDao;
	}

	public void setUserDao(IUserDao userDao) {
		this.userDao = userDao;
	}

	public ILogDao getLogDao() {
		return logDao;
	}

	public void setLogDao(ILogDao logDao) {
		this.logDao = logDao;
	}

	public void regist() throws SQLException{
		logDao.log();
		userDao.add();
		throw new SQLException();
	}
}
