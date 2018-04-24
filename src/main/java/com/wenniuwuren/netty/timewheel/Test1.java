package com.wenniuwuren.netty.timewheel;

import io.netty.util.HashedWheelTimer;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.TimeUnit;

/**
 * @author zhuyibin
 */
public class Test1 {

    public static void main(String[] args) {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            HashedWheelTimer hashedWheelTimer = new HashedWheelTimer(100, TimeUnit.MILLISECONDS);
            System.out.println("start:" + LocalDateTime.now().format(formatter));
            hashedWheelTimer.newTimeout(timeout -> {
                System.out.println("task1:" + LocalDateTime.now().format(formatter));
            }, 3, TimeUnit.SECONDS);
//            hashedWheelTimer.newTimeout(timeout -> System.out.println("task2:" + LocalDateTime.now().format(
//                    formatter)), 4, TimeUnit.SECONDS);
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}