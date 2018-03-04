
package com.wenniuwuren.java.designpattern.chainofresponsibility;

/**
 * @author wenniuwuren
 */
public class ConcreteHandler2 extends Handler {
    @Override
    public void handlerRequest(int request) {
        // 请求数在 10~19 有权处理
        if (request >= 10 && request < 20) {
            System.out.println("ConcreteHandler2 处理请求：" + request);

        } else if (successor != null) { // 转到下一个
            successor.handlerRequest(request);
        }
    }
}