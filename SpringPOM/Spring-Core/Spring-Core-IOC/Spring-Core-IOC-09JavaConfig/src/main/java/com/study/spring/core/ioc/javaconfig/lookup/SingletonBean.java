package com.study.spring.core.ioc.javaconfig.lookup;

public class SingletonBean {

    /**
     * 获取依赖的prototype bean方法
     * 该方法可以为抽象方法，不必实现它，Spring产生的代理对象会实现该方法
     * @return
     */
    public PrototypeBean getPrototypeBean() {
        // 此处永不会被调用
        System.out.println("call getPrototypeBean()");
        return null;
    }
}
