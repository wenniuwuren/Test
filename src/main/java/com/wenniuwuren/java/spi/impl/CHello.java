package com.wenniuwuren.java.spi.impl;

import com.wenniuwuren.java.spi.Hello;

/**
 * Created by hzzhuyibin on 2017/2/14.
 */
public class CHello implements Hello{
    @Override
    public void sayHello() {
        System.out.println("C Hello");
    }
}

