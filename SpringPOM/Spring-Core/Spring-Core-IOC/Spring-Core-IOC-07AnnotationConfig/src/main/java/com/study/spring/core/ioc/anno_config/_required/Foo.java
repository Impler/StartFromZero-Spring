package com.study.spring.core.ioc.anno_config._required;

import org.springframework.beans.factory.annotation.Required;

public class Foo {

    private Bar bar;

    public Bar getBar() {
        return bar;
    }

    @Required
    public void setBar(Bar bar) {
        this.bar = bar;
    }

}
