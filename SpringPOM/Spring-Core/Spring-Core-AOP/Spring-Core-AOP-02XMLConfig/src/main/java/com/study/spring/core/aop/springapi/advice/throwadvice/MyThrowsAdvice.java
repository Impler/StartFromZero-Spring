package com.study.spring.core.aop.springapi.advice.throwadvice;

import java.lang.reflect.Method;
import java.sql.SQLException;

import org.springframework.aop.ThrowsAdvice;

public class MyThrowsAdvice implements ThrowsAdvice {

    public void afterThrowing(Method method, Object[] args, Object target, SQLException e){
        System.out.println("异常方法：" + method);
        System.out.println("异常信息：" + e);
    }
}
