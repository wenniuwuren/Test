
package com.wenniuwuren.java.designpattern.state.normal;

/**
 * @author zhuyibin
 */
public class ConcreteState2 implements State{
    @Override
    public void handle(Context context) {
        // 设置 ConcreteState2 下一状态是 ConcreteState1
        context.setState(new ConcreteState());
    }
}