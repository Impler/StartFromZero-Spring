package com.study.spring.core.aop.springapi.advice.around;

public class FooService {

    public int save() {
        System.out.println("FooService.save()");
        return 1;
    }
}
