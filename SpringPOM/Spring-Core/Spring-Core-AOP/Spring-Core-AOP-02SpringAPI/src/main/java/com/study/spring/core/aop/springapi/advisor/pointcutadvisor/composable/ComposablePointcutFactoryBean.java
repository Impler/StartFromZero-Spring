package com.study.spring.core.aop.springapi.advisor.pointcutadvisor.composable;

import org.springframework.aop.Pointcut;
import org.springframework.aop.support.ComposablePointcut;
import org.springframework.aop.support.ControlFlowPointcut;
import org.springframework.aop.support.NameMatchMethodPointcut;
import org.springframework.beans.factory.FactoryBean;

import com.study.spring.core.aop.springapi.advisor.pointcutadvisor.controlflow.FooDelegate;

/**
 * 复合切点工厂
 */
public class ComposablePointcutFactoryBean implements FactoryBean<ComposablePointcut> {

    @Override
    public ComposablePointcut getObject() throws Exception {
        
        ComposablePointcut cp = new ComposablePointcut();
        // 流程切点
        ControlFlowPointcut controlflow = new ControlFlowPointcut(FooDelegate.class, "doService");
        // 静态方法切点
        NameMatchMethodPointcut nameMatch = new NameMatchMethodPointcut();
        nameMatch.addMethodName("sayHi");
        // 节点交叉
        cp.intersection((Pointcut)controlflow).intersection((Pointcut)nameMatch);
        return cp;
    }

    @Override
    public Class<?> getObjectType() {
        return ComposablePointcut.class;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }

}
