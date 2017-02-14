package com.wenniuwuren.spi.impl;

import com.wenniuwuren.spi.Hello;

/**
 * Created by hzzhuyibin on 2017/2/14.
 */
public class CHello implements Hello{
    @Override
    public void sayHello() {
        System.out.println("C Hello");
    }
}

