package com.wenniuwuren.spring.aop.cglib;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * Created by hzzhuyibin on 2016/11/24.
 */
public class EnhancerDemo {

    public static void main(String[] args) {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(EnhancerDemo.class);
        enhancer.setCallback(new MethodInterceptorImpl());

        EnhancerDemo enhancerDemo = (EnhancerDemo) enhancer.create();
        enhancerDemo.test();
        System.out.println(enhancerDemo);
    }

    public void test() {
        System.out.println("EnhancerDemo test()");
    }

    // Spring 的 CglibAopProxy 类中的内部类 DynamicAdvisedInterceptor 也实现了 MethodInterceptor
    private static class MethodInterceptorImpl implements MethodInterceptor {

        @Override
        public Object intercept(Object o, Method method, Object[] args, MethodProxy proxy) throws Throwable {
            System.err.println("Before invoke: " + method);
            Object result = proxy.invokeSuper(o, args);
            System.err.println("After invoke: " + method);

            return result;
        }
    }

}

