
package com.wenniuwuren.java8.completablefuture;

import java.util.Random;

/**
 * Created by hzzhuyibin on 2017/5/22.
 */
public class Util {

    private static final Random random = new Random();

    public static void delay() {
        try {
            Thread.sleep(1000L);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void randomDelay() {
        int delay = 500 + random.nextInt(2000);

        try {
            Thread.sleep(delay);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}

