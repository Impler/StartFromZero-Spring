package com.study.spring.core.aop.aspectanno._03advice.objects;

public class Foo2ServiceAImpl implements IServiceA {

    @Override
    public int save(Foo foo) throws Exception{
        System.out.println("call Foo2ServiceAImpl>>save()");
        throw new Exception("Foo2ServiceAImpl>>save() throw exception");
    }

    @Override
    public void update(Foo foo) {
        System.out.println("call Foo2ServiceAImpl>>update()");
    }
    
}
