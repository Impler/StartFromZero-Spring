package com.study.spring.integration.helloworld.service;

public class HelloServiceImpl implements HelloService {

  @Override
  public void sayHello(String name) {
    System.out.println("hello " + name);
  }

  @Override
  public String getHello(String name) {
    return "hello " + name;
  }
}
