package com.study.spring.core.aop.springapi.advice.before;

public class FooService {

    public void save() throws KnownException{
        System.out.println("FooService.save()");
    }
}
