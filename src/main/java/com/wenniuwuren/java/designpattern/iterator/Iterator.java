
package com.wenniuwuren.java.designpattern.iterator;

/**
 * 迭代器接口得到下一个对象、是否有下一个对象
 * @author zhuyibin
 */
public interface Iterator<E> {


    /**
     * 下一个对象
     * @return
     */
    E next();

    /**
     * 是否有下一个对象
     * @return
     */
    boolean hasNext();

}