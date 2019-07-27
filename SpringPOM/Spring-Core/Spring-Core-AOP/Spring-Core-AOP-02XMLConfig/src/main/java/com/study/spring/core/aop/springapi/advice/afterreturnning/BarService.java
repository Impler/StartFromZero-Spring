package com.study.spring.core.aop.springapi.advice.afterreturnning;

public class BarService {

    public int save() {
        System.out.println("BarService.save()");
        throw new RuntimeException();
    }
}
