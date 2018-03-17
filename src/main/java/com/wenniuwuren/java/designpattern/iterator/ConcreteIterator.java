
package com.wenniuwuren.java.designpattern.iterator;

/**
 * @author zhuyibin
 */
public class ConcreteIterator<E> implements Iterator{
    private int current = 0;

    private ConcreteAggregate<E> concreteAggregate;

    public ConcreteIterator(ConcreteAggregate concreteAggregate) {
        this.concreteAggregate = concreteAggregate;
    }

    @Override
    public E next() {
        E e = null;
        if (current < concreteAggregate.total()) {
            e = concreteAggregate.getItem(current);
            current++;
        }
        return e;
    }

    @Override
    public boolean hasNext() {
        if (current < concreteAggregate.total()) {
            return true;
        }
        else {
            return false;
        }
    }
}