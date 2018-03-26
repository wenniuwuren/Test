package com.wenniuwuren.java.designpattern.mediator;

/**
 * 中介者接口
 * @author zhuyibin
 */
public interface Mediator {

    void send(String message, Colleague colleague);
}