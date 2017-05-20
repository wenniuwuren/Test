
package com.wenniuwuren.java8.designpattern.obeserver;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hzzhuyibin on 2017/5/16.
 */
public class Feed implements Subject{

    private final List<Observer> observerList = new ArrayList<>();

    @Override
    public void registerObserver(Observer o) {
        observerList.add(o);
    }

    @Override
    public void notifyObservers(String tweet) {
        for (Observer o : observerList) {
            o.notify(tweet);
        }
    }

    public static void main(String[] args) {
        Feed f = new Feed();
        f.registerObserver(new NYTimesObserver());
        f.notifyObservers("what is money");

        // 观察者处理比较简单，也是个函数式接口，可以 Lambda 化
        f.registerObserver((tweet)
                ->
        {
            if (tweet != null && tweet.contains("what"))
                System.out.println("the tweets contains what :" + tweet);

        });
        f.notifyObservers("what is money");
    }
}


