
package com.wenniuwuren.java.designpattern.proxy;


/**
 * 代理类，使用真正类对象去调用方法
 * @author wenniuwuren
 */
public class Proxy implements Subject{

    RealSubject realSubject;

    @Override
    public void request() {
        if (realSubject == null) {
            realSubject = new RealSubject();
        }
        realSubject.request();
    }
}