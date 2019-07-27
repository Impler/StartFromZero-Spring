package com.study.spring.core.aop.springapi.advisor;

public class Bar {
    public void sayHi(String name) {
        System.out.println("Bar>>hi " + name);
    }

    public void sayHello(String name) {
        System.out.println("Bar>>hello " + name);
    }
    
    public void sayGoodBye(String name) {
        System.out.println("Bar>>goodbye " + name);
    }
}
