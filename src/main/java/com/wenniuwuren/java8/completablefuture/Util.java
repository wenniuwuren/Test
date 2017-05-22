
package com.wenniuwuren.java8.completablefuture;

/**
 * Created by hzzhuyibin on 2017/5/22.
 */
public class Util {

    public static void delay() {
        try {
            Thread.sleep(1000L);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

