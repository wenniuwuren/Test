
package com.wenniuwuren.java.designpattern.proxy;


/**
 * @author wenniuwuren
 */
public class RealSubject implements Subject{
    @Override
    public void request() {
        System.out.println("real request");
    }
}