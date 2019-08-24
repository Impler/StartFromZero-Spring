package com.study.spring.core.aop.springapi.advisor;

public class Foo {
    public void sayHi(String name) {
        System.out.println("Foo>>hi " + name);
    }

    public void sayHello(String name) {
        System.out.println("Foo>>hello " + name);
    }
    
    public void sayGoodBye(String name) {
        System.out.println("Foo>>goodbye " + name);
    }
}
