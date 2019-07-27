package com.study.spring.core.ioc.javaconfig.environment.profile;

public class DevDataSource implements DataSource {

    @Override
    public String getName() {
        return "DevDS";
    }

}
