package com.study.spring.integration.helloworld.service;

public interface HelloService {

  /**
   * say hello to someone
   * @param name
   */
  void sayHello(String name);


  /**
   * get hello message
   * @param name
   * @return
   */
  String getHello(String name);
}
