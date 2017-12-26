package com.study.spring.core.ioc.classpath_scan.component;

import org.springframework.stereotype.Repository;

@Repository
public class UserDao {

    public void save() {
        System.out.println("UserDao>>save()");
    }
}
