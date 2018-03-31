
package com.wenniuwuren.java.designpattern.memento;

/**
 * 发起人 类似游戏
 * @author zhuyibin
 */
public class Originator {

    private String state;


    /**
     * 创建备忘录
     * @return
     */
    public Memento createMemento() {
        return new Memento(state);
    }


    /**
     * 恢复备忘录，将 Memento 导入并将相关数据恢复
     * @param memento
     */
    public void setMemento(Memento memento) {
        state = memento.getState();
    }

    public void show() {
        System.out.println("state=" + state);
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}