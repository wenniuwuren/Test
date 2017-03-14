package com.wenniuwuren.java.threadlocal;

/**
 * 执行结果：
 * Thread[Thread-0,5,main]---------count=0
 * Thread[Thread-0,5,main]---------count=1
 * Thread[Thread-0,5,main]---------count=2
 * Thread[Thread-0,5,main]---------count=3
 * Thread[Thread-0,5,main]---------count=4
 * Thread[Thread-2,5,main]---------count=0
 * Thread[Thread-2,5,main]---------count=1
 * Thread[Thread-2,5,main]---------count=2
 * Thread[Thread-1,5,main]---------count=0
 * Thread[Thread-2,5,main]---------count=3
 * Thread[Thread-2,5,main]---------count=4
 * Thread[Thread-1,5,main]---------count=1
 * Thread[Thread-1,5,main]---------count=2
 * Thread[Thread-1,5,main]---------count=3
 * Thread[Thread-1,5,main]---------count=4
 * 可以看出线程的变量更新没有相互影响
 * Created by hzzhuyibin on 2017/3/14.
 */
public class ThreadLocalTest {

    private static ThreadLocal<Integer> count = new ThreadLocal<Integer>() {
        public Integer initialValue() {
            return 0;
        }
    };

    public static void main(String[] args) {

        NewThread thread1 = new NewThread(count);
        NewThread thread2 = new NewThread(count);
        NewThread thread3 = new NewThread(count);

        thread1.start();
        thread3.start();
        thread2.start();

    }

    public static class NewThread extends Thread {
        ThreadLocal<Integer> threadLocal = null;

        public NewThread(ThreadLocal<Integer> threadLocal) {
            this.threadLocal = threadLocal;
        }

        @Override
        public void run() {
            for (int i = 0; i < 5; i++) {
                System.out.println(Thread.currentThread() + "---------count=" + threadLocal.get());
                threadLocal.set(threadLocal.get() + 1);
            }
        }
    }

}

