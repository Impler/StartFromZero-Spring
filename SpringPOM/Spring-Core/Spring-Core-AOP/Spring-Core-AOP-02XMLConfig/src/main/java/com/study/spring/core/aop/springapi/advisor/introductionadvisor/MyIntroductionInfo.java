package com.study.spring.core.aop.springapi.advisor.introductionadvisor;

import org.springframework.aop.IntroductionInfo;

public class MyIntroductionInfo implements IntroductionInfo {

    @Override
    public Class<?>[] getInterfaces() {
        
        return new Class<?>[] {IServiceA.class};
    }

}
