
package com.wenniuwuren.java8.designpattern.obeserver;

/**
 * Created by hzzhuyibin on 2017/5/16.
 */
public class Guardian implements Observer{
    @Override
    public void notify(String tweet) {
        if (tweet != null && tweet.contains("queen")) {
            System.out.println("Breaking new in London " + tweet);
        }
    }
}

