package com.study.spring.core.ioc.javaconfig.environment.profile;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class XmlConfigTest {

    @Test
    /**
     * DEV 环境的数据源
     */
    public void testDevDataSource() {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("beans-env-profile.xml");
        context.getEnvironment().setActiveProfiles("dev");
        context.refresh();
        DataSource ds = context.getBean(DataSource.class);
        Assert.assertNotNull(ds);
        Assert.assertTrue(ds instanceof DevDataSource);
        Assert.assertEquals("DevDS", ds.getName());
        context.close();
    }
    @Test
    /**
     * PRD 环境的数据源
     */
    public void testProductionDataSource() {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("beans-env-profile.xml");
        context.getEnvironment().setActiveProfiles("prd");
        context.refresh();
        DataSource ds = context.getBean(DataSource.class);
        Assert.assertNotNull(ds);
        Assert.assertTrue(ds instanceof PrdDataSource);
        Assert.assertEquals("PrdDS", ds.getName());
        context.close();
    }

    @Test
    /**
     * 默认 环境的数据源
     */
    public void testDefaultDataSource() {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("beans-env-profile.xml");
        // Spring默认profile名为default
//        context.getEnvironment().setActiveProfiles("default");
        context.refresh();
        DataSource ds = context.getBean(DataSource.class);
        Assert.assertNotNull(ds);
        Assert.assertTrue(ds instanceof EmbedDataSource);
        Assert.assertEquals("EmbedDS", ds.getName());
        context.close();
    }

}
