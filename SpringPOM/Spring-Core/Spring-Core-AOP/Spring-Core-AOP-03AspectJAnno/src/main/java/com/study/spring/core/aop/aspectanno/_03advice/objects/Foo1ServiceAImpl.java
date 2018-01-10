package com.study.spring.core.aop.aspectanno._03advice.objects;

public class Foo1ServiceAImpl implements IServiceA {

    @Override
    public int save(Foo foo) {
        System.out.println("call Foo1ServiceAImpl>>save()");
        return 1;
    }

    @Override
    public void update(Foo foo) {
        System.out.println("call Foo1ServiceAImpl>>update()");
    }
    
}
