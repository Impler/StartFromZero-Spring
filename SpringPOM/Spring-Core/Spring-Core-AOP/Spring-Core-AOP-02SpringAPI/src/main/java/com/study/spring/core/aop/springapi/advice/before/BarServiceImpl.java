package com.study.spring.core.aop.springapi.advice.before;

public class BarServiceImpl implements IBarService {

    @Override
    public void save() {
        System.out.println("BarServiceImpl.save()");
    }

}
