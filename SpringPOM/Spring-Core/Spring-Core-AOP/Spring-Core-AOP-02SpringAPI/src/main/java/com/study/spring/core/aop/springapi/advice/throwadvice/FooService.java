package com.study.spring.core.aop.springapi.advice.throwadvice;

import java.sql.SQLException;

public class FooService {

    public int save() throws Throwable{
        System.out.println("FooService.save()");
        throw new SQLException("value is too large...");
    }
}
