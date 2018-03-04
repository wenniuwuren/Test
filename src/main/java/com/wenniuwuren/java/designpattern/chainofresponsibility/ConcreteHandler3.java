package com.wenniuwuren.java.designpattern.chainofresponsibility;

/**
 * 处理者具体类，处理它所负责的请求，可访问它的后继者，如果可以处理则处理，
 * 否则将请求转发给后继者
 * @author wenniuwuren
 */
public class ConcreteHandler3 extends Handler{
    @Override
    public void handlerRequest(int request) {
        // 请求数在 20~29 有权处理
        if (request >= 20 && request < 29) {
            System.out.println("ConcreteHandler3 处理请求：" + request);

        } else if (successor != null) { // 转到下一个
            successor.handlerRequest(request);
        }
    }
}