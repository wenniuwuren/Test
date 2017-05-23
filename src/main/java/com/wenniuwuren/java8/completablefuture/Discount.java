
package com.wenniuwuren.java8.completablefuture;

/**
 * Created by hzzhuyibin on 2017/5/23.
 */
public class Discount {

    public enum Code {
        NONE(0), SILVER(5), GOLD(10), PLATINUM(15), DIAMOND(20);

        private final int percentage;

        Code(int percentage) {
            this.percentage = percentage;
        }


    }

    /**
     * 返回打折后的价格
     * @param quote
     * @return
     */
    public static String applyDiscount(Quote quote) {
        return quote.getShopName() + " price is " + Discount.apply(quote.getPrice(), quote.getDiscountCode());
    }

    /**
     * 模拟延迟，计算折扣价
     * @param price
     * @param code
     * @return
     */
    private static double apply(double price, Code code) {
        Util.delay();
        return price * (100 - code.percentage) / 100;

    }
}

