
package com.wenniuwuren.java.dynamicproxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

/**
 * @author wenniuwuren
 */
public class Client {

    public static void main(String[] args) {
        RealSubject realSubject = new RealSubject(); // 被代理类
        InvocationHandler invocationHandler = new DynamicSubject(realSubject); // 初始化代理类
        Subject subject = (Subject) Proxy.newProxyInstance(RealSubject.class.getClassLoader(),
                RealSubject.class.getInterfaces(), invocationHandler);
        subject.request();
    }
}