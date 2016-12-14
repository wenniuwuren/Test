package com.wenniuwuren.spring.aop.jdk;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 创建自定义 InvocationHandler，用于对接口提供的方法进行增强
 * Created by hzzhuyibin on 2016/11/24.
 */
public class MyInvocationHandler implements InvocationHandler {

    // 目标对象
    private Object target;

    public MyInvocationHandler(Object target) {
        super();
        this.target = target;
    }


    /**
     * 执行目标对象的方法。相对应的，Spring 的 JdkDynamicAopProxy 也实现 InvocationHandler，所以核心代理实现也在相应的 invoke 方法里
     * @param proxy
     * @param method
     * @param args
     * @return
     * @throws Throwable
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        // 目标方法执行前简单打印下
        System.out.println("------------before------------");

        Object result = method.invoke(target, args);

        // 执行后打印
        System.out.println("------------after-------------");

        return result;
    }

    /**
     * 获取目标对象的代理对象
     * @return
     */
    public Object getProxy() {
        return Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader(), target.getClass().getInterfaces(), this);
    }
}

