package com.wenniuwuren.netty.timewheel;

import io.netty.util.HashedWheelTimer;
import io.netty.util.Timeout;
import io.netty.util.Timer;
import io.netty.util.TimerTask;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class HashedWheelTimerTest {


    public static void main(String[] argv) {

        final Timer timer = new HashedWheelTimer(Executors.defaultThreadFactory(), 100,
                TimeUnit.MILLISECONDS, 10);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        System.out.println("start:" + LocalDateTime.now().format(formatter));
        TimerTask task1 = new TimerTask() {
            public void run(Timeout timeout) throws Exception {
                System.out.println("task 1: " + LocalDateTime.now().format(formatter));
                timer.newTimeout(this, 5, TimeUnit.SECONDS);//结束时候再次注册
            }
        };
        timer.newTimeout(task1, 5, TimeUnit.SECONDS);


//        TimerTask task2 = new TimerTask() {
//            public void run(Timeout timeout) throws Exception {
//                System.out.println("task 2: " + LocalDateTime.now().format(formatter));
//                timer.newTimeout(this, 10, TimeUnit.SECONDS);//结束时候再注册
//            }
//        };
//        timer.newTimeout(task2, 10, TimeUnit.SECONDS);


       //该任务仅仅运行一次
//        timer.newTimeout(new TimerTask() {
//            public void run(Timeout timeout) throws Exception {
//                System.out.println("task 3 run only once ! ");
//            }
//        }, 15, TimeUnit.SECONDS);

    }


}