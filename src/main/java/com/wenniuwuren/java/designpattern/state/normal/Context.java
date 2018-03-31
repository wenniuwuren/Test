
package com.wenniuwuren.java.designpattern.state.normal;

/**
 * @author zhuyibin
 */
public class Context {

    private State state;

    /**
     * 定义 Context 初始状态
     * @param state
     */
    public Context(State state) {
        this.state = state;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public void show() {
        System.out.println("当前状态：" + getState());
    }

    /**
     * 对请求做处理，并设置下一个状态
     */
    public void request() {
        state.handle(this);
        show();
    }


}