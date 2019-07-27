package com.study.spring.core.aop.springapi.advisor.pointcutadvisor.controlflow;

import com.study.spring.core.aop.springapi.advisor.Foo;

public class FooDelegate {
    
    private Foo foo;

    public void setFoo(Foo foo) {
        this.foo = foo;
    }

    public void doService(String name) {
        foo.sayHi(name);
        foo.sayGoodBye(name);
    }
}
