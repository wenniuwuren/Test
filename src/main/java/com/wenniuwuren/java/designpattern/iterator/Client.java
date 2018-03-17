
package com.wenniuwuren.java.designpattern.iterator;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zhuyibin
 */
public class Client {
    public static void main(String[] args) {
        ConcreteAggregate<String> concreteAggregate = new ConcreteAggregate();

        concreteAggregate.set("leonard");
        concreteAggregate.set("jobs");
        concreteAggregate.set("zack");

        Iterator iterator = concreteAggregate.createIterator();

        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }

        // java 迭代器
        List a = new ArrayList();
        a.add("a");
        a.iterator().hasNext();
    }
}