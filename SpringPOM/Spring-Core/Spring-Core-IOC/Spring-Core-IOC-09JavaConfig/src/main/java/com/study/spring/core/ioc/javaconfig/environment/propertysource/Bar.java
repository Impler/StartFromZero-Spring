package com.study.spring.core.ioc.javaconfig.environment.propertysource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class Bar {

    @Value("${env.propertysource.address}")
    private String address;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

}
