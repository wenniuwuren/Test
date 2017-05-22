
package com.wenniuwuren.java8.completablefuture;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;
import java.util.stream.Collectors;

/**
 * Created by hzzhuyibin on 2017/5/22.
 */
public class Test {

    final static List<Shop> shops = Arrays.asList(new Shop("BestPrice"), new Shop("LetsSaveBig"), new Shop("MyFavoriteShop"),
            new Shop("BuyItAll"), new Shop("5"));

    /**
     * thread pool
     */
    private static final Executor executor = Executors.newFixedThreadPool(Math.min(shops.size(), 100),
            new ThreadFactory() {
                @Override
                public Thread newThread(Runnable r) {
                    Thread t = new Thread(r);
                    t.setDaemon(true); // 因为守护线程与Java进程共存亡，避免了Java退出，而线程还在等待某些行为
                    return t;
                }
            });

    public static void main(String[] args) {
        // -------- 能改 Shop 代码方法同步/异步情况 ---------------
        Shop shop = new Shop("BestShop");
        long start = System.nanoTime();
        Future<Double> futurePrice = shop.getPriceAsync("my product");
        long invocationTime = ((System.nanoTime() - start) / 1000_000);
        System.out.println("invocation return after " + invocationTime + "ms");

        // 执行其他任务
        System.out.println("执行其他任务");

        try {
            // 阻塞获取
            double price = futurePrice.get();
            System.out.println("price is " + price);
        } catch (Exception e) {
            e.printStackTrace();
        }

        long retrievalTime = ((System.nanoTime() - start) / 1000_000);
        System.out.println("Price return after " + retrievalTime + "ms");



        // -------- 不能改 Shop 代码方法同步/异步情况 ---------------
        long startTime = System.nanoTime();
        // 同步执行
//        System.out.println(findPrices("my product"));

        // 在 shops 为4，耗时1000+，shops 为 5 耗时 2000+，因为达到了内部线程池限制
//        System.out.println(findPricesByParallel("my product"));

//        System.out.println(findPricesByParallelWithAsyn("my product"));

        // 使用自定义线程池，突破内部线程池限制，shops 5，耗时 1000+
        System.out.println(findPricesByParallelWithAsynUseCustomizeThreadPoll("my product"));

        System.out.println("cost: " + ((System.nanoTime() - startTime) / 1000_000) + "ms");


    }

    // 顺序执行
    public static List<String> findPrices(String product) {
        return shops.stream().map(shop -> shop.getShopName() + " price is " + shop.getPrice(product))
                .collect(Collectors.toList());
    }

    // 并行执行(使用内部默认线程池)  适合计算密集型
    public static List<String> findPricesByParallel(String product) {
        return shops.parallelStream().map(shop -> shop.getShopName() + " price is " + shop.getPrice(product))
                .collect(Collectors.toList());
    }

    // 异步,并行执行(使用内部默认线程池)  适合IO密集型
    public static List<String> findPricesByParallelWithAsyn(String product) {
        // 下面代码分成2个stream执行，是考虑到操作的延迟性，如果只用单个stream则每个Shop之间是顺序执行的

        List<CompletableFuture<String>> priceFutures = shops.parallelStream().map(shop
                -> CompletableFuture.supplyAsync(() -> shop.getShopName() + " price is " + shop.getPrice(product)))
                .collect(Collectors.toList());
        // join 等待所有异步操作结果结束
        return priceFutures.parallelStream().map(CompletableFuture::join).collect(Collectors.toList());

    }

    // 异步,并行执行(使用自定义线程池)
    public static List<String> findPricesByParallelWithAsynUseCustomizeThreadPoll(String product) {
        // 下面代码分成2个stream执行，是考虑到操作的延迟性，如果只用单个stream则每个Shop之间是顺序执行的

        List<CompletableFuture<String>> priceFutures = shops.parallelStream().map(shop
                -> CompletableFuture.supplyAsync(() -> shop.getShopName() + " price is " + shop.getPrice(product), executor))
                .collect(Collectors.toList());
        // join 等待所有异步操作结果结束
        return priceFutures.parallelStream().map(CompletableFuture::join).collect(Collectors.toList());

    }
}

