package com.wenniuwuren.guava;

import com.google.common.util.concurrent.RateLimiter;

/**
 * RateLimiter 令牌桶的流控算法，会按照一定的频率往桶里扔令牌，线程拿到令牌才能执行
 * RateLimiter 有一个有趣的特性是「前人挖坑后人跳」，也就是说 RateLimiter 允许某次请求拿走超出剩余令牌数的令牌，
 * 但是下一次请求将为此付出代价，一直等到令牌亏空补上，并且桶中有足够本次请求使用的令牌为止。
 * @author zhuyibin
 */
public class RateLimitTest {

    // 当超过请求速率，会释放每秒释放一个令牌（1/permitsPerSecond ）
    static RateLimiter rateLimiter = RateLimiter.create(2);

    public static void main(String[] args) {

        for (int i = 0; i < 100; i++) {
            final int index = i;
            new Thread(new Runnable() {
                @Override
                public void run() {
                    rateLimiter.acquire(); // 返回等待时间
                    System.out.println("hello" + index + ", rate=" + rateLimiter.getRate());
                }
            }).start();

        }

        try {
            Thread.sleep(Long.MAX_VALUE);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}