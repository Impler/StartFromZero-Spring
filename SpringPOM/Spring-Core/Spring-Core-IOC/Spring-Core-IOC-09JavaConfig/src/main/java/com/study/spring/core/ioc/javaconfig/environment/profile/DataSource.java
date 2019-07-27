package com.study.spring.core.ioc.javaconfig.environment.profile;

/**
 * 自定义数据源
 * @author impler
 * @date 2017年12月19日
 */
public interface DataSource {

    /**
     * 获取数据源名称
     * @return
     */
    String getName();
}
