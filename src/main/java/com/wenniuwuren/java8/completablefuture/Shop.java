
package com.wenniuwuren.java8.completablefuture;

import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;
import java.util.concurrent.atomic.DoubleAccumulator;

/**
 * 根据指定产品名称返回价格
 * Created by hzzhuyibin on 2017/5/22.
 */
public class Shop {

    private String shopName;

    public Shop(String name) {
        this.shopName = name;
    }

    /**
     * 同步计算
     * @param product
     * @return
     */
    public double getPrice(String product) {
        return calculatePrice(product);
    }


    /**
     * 异步计算
     * @param product
     * @return
     */
    public Future<Double> getPriceAsync(String product) {
        CompletableFuture<Double> futurePrice = new CompletableFuture<>();
        new Thread(() -> {
            try {
                double price = calculatePrice(product);
                futurePrice.complete(price);
            } catch (Exception e) {
                // 抛出 CompletableFuture 的异常
                futurePrice.completeExceptionally(e);
            }
        }).start();

        return futurePrice;

        // 工厂方法
//        return CompletableFuture.supplyAsync(() -> calculatePrice(product));
    }

    private double calculatePrice(String product) {
        Util.delay();
        return new Random().nextDouble() * product.charAt(0) + product.charAt(1);
    }

    public String getShopName() {
        return shopName;
    }
}

