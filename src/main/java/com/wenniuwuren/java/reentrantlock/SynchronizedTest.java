package com.wenniuwuren.java.reentrantlock;

public class SynchronizedTest implements Runnable {

    public synchronized void get() {
        System.out.println(Thread.currentThread().getId());
        set();
    }

    public synchronized void set() {
        System.out.println(Thread.currentThread().getId());
    }

    @Override
    public void run() {
        get();
    }

    public static void main(String[] args) {
        SynchronizedTest ss = new SynchronizedTest();
        new Thread(ss).start();
        new Thread(ss).start();
        new Thread(ss).start();
    }
}
