package com.study.spring.core.ioc.di.method_injection.look_up;

import com.study.spring.core.ioc.di.method_injection.PrototypeBean;

public class SingletonBeanOfLookup {

    public void call(){
        PrototypeBean pro = getPrototypeBean();
        System.out.println(pro);
    }
    
    /**
     * 该方法将会被重写
     * @return
     */
    public PrototypeBean getPrototypeBean(){
        // 此处不会打印，因为该方法会被完全覆盖重写
        System.out.println("get prototype bean");
        return null;
    }
}
