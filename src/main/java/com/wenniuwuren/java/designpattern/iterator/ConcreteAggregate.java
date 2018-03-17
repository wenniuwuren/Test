
package com.wenniuwuren.java.designpattern.iterator;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zhuyibin
 */
public class ConcreteAggregate<E> implements Aggregate{
    private List<E> items = new ArrayList<E>();


    @Override
    public Iterator createIterator() {
        return new ConcreteIterator(this);
    }

    public int total() {
        return items.size();
    }

    public E getItem(int index) {
        return items.get(index);
    }

    public void set(E e) {
        items.add(e);
    }

}