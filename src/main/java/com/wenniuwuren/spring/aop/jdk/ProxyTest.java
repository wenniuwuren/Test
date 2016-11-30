package com.wenniuwuren.spring.aop.jdk;

/**
 * 这是 AOP JDK版的简单实现
 * Created by hzzhuyibin on 2016/11/24.
 */
public class ProxyTest {

    public static void main(String[] args) {
        // 实例化目标对象
        UserService userService = new UserServiceImpl();

        // 实例化 InvocationHandler
        MyInvocationHandler myInvocationHandler = new MyInvocationHandler(userService);

        // 根据目标对象生成代理对象
        UserService proxy = (UserService) myInvocationHandler.getProxy();

        // 调用代理对象的方法
        proxy.add();
    }
}

