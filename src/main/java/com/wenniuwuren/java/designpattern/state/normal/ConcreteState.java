
package com.wenniuwuren.java.designpattern.state.normal;

/**
 * @author zhuyibin
 */
public class ConcreteState implements State{
    @Override
    public void handle(Context context) {
        // 设置 ConcreteState 下一状态是 ConcreteState2
        context.setState(new ConcreteState2());
    }
}