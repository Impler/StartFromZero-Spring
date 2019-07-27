package com.study.spring.core.ioc.classpath_scan.component;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserDao userDao;
    
    public void saveUser() {
        System.out.println("UserService>>saveUser()");
        userDao.save();
    }
}
