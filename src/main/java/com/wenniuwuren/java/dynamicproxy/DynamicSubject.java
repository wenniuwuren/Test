
package com.wenniuwuren.java.dynamicproxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @author wenniuwuren
 */
public class DynamicSubject implements InvocationHandler{

    private Object realSubject;



    public DynamicSubject(Object realSubject) {
        this.realSubject = realSubject;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("before calling " + method);
        method.invoke(realSubject, args);
        System.out.println("after calling " + method);
        return null;
    }
}