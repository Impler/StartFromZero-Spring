package com.study.spring.core.ioc.javaconfig.environment.profile;

public class EmbedDataSource implements DataSource {

    @Override
    public String getName() {
        return "EmbedDS";
    }

}
