package com.wenniuwuren.spi.impl;

import com.wenniuwuren.spi.Hello;

/**
 * Created by hzzhuyibin on 2017/2/14.
 */
public class JavaHello implements Hello{
    @Override
    public void sayHello() {
        System.out.println("Java Hello");
    }
}

