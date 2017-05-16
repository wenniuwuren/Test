
package com.wenniuwuren.java8.designpattern.obeserver;

/**
 * 观察者接口，将不同观察者聚合
 * Created by hzzhuyibin on 2017/5/16.
 */
public interface Observer {

    void notify(String tweet);
}

