package com.study.spring.core.aop.springapi.advice.afterreturnning;

public class FooService {

    public int save() {
        System.out.println("FooService.save()");
        return 1;
    }
}
