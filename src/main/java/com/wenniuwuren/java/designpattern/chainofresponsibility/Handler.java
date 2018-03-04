
package com.wenniuwuren.java.designpattern.chainofresponsibility;

/**
 * 定义一个处理请示的接口
 * @author wenniuwuren
 */
abstract class Handler {

    protected Handler successor;

    /**
     * 设置继任者
     * @param successor
     */
    public void setSuccessor(Handler successor) {
        this.successor = successor;
    }

    /**
     * 处理请求的抽象方法
     * @param request
     */
    public abstract void handlerRequest(int request);
}