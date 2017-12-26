package com.study.spring.core.ioc.javaconfig.environment.profile;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class AnnoJavaConfigTest {

    @Test
    /**
     * DEV 环境的数据源
     */
    public void testDevDataSource() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.getEnvironment().setActiveProfiles("dev");
        context.register(AnnoJavaConfig.class);
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
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.getEnvironment().setActiveProfiles("prd");
        context.register(AnnoJavaConfig.class);
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
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        // Spring默认profile名为default
//        context.getEnvironment().setActiveProfiles("default");
        context.register(AnnoJavaConfig.class);
        context.refresh();
        DataSource ds = context.getBean(DataSource.class);
        Assert.assertNotNull(ds);
        Assert.assertTrue(ds instanceof EmbedDataSource);
        Assert.assertEquals("EmbedDS", ds.getName());
        context.close();
    }

}
