
package com.wenniuwuren.java8.designpattern.obeserver;

/**
 * Created by hzzhuyibin on 2017/5/16.
 */
public interface Subject {

    /**
     * 注册新观察者
     * @param o
     */
    void registerObserver(Observer o);

    /**
     * 通知观察者行为变更
     * @param tweet
     */
    void notifyObservers(String tweet);
}

