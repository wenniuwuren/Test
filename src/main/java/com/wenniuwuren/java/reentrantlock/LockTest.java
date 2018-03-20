package com.wenniuwuren.java.reentrantlock;

import java.util.concurrent.locks.ReentrantLock;

public class LockTest implements Runnable {
    ReentrantLock lock = new ReentrantLock();

	public void get() {
		lock.lock();
		System.out.println(Thread.currentThread().getId());
		set();
		lock.unlock();
	}

	public void set() {
		lock.lock();
		System.out.println(Thread.currentThread().getId());
		lock.unlock();
	}

	@Override
	public void run() {
		get();
	}

	public static void main(String[] args) {
		LockTest ss = new LockTest();
		new Thread(ss).start();
		new Thread(ss).start();
		new Thread(ss).start();
	}
}