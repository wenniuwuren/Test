package com.wenniuwuren.java.spi.impl;

import com.wenniuwuren.java.spi.Hello;

/**
 * Created by hzzhuyibin on 2017/2/14.
 */
public class JavaHello implements Hello{
    @Override
    public void sayHello() {
        System.out.println("Java Hello");
    }
}

