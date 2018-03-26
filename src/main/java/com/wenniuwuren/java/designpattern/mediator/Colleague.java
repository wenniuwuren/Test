
package com.wenniuwuren.java.designpattern.mediator;

/**
 * 同事接口
 * @author zhuyibin
 */
public abstract class Colleague {

    Mediator mediator;

    public Colleague(Mediator mediator) {
        this.mediator = mediator;
    }
}