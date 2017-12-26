package com.study.spring.core.ioc.javaconfig.environment.profile;

public class PrdDataSource implements DataSource {

    @Override
    public String getName() {
        return "PrdDS";
    }

}
