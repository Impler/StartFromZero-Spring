package com.study.spring.core.ioc.javaconfig.annotationconfigapplicationcontext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FooService {

    @Autowired
    private BarService barService;
    
    public void foo() {
      barService.bar();  
    }
    
    public BarService getBarService() {
        return this.barService;
    }
}
