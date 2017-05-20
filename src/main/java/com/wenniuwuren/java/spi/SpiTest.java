package com.wenniuwuren.java.spi;

import java.util.Iterator;
import java.util.ServiceLoader;

/**
 * Created by hzzhuyibin on 2017/2/14.
 */
public class SpiTest {
    public static void main(String[] args) {

        ServiceLoader<Hello> helloServiceLoader = ServiceLoader.load(Hello.class);
        Iterator<Hello> helloIterator = helloServiceLoader.iterator();
        while (helloIterator.hasNext()) {
            Hello hello = helloIterator.next();
            hello.sayHello();
        }
    }
}

