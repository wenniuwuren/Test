
package com.wenniuwuren.java.designpattern.state.normal;

/**
 * @author zhuyibin
 */
public class Client {
    public static void main(String[] args) {
        Context c = new Context(new ConcreteState()); // 设置初始状态

        c.request(); // 不断变换状态
        c.request();
    }
}