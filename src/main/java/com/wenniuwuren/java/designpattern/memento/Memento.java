
package com.wenniuwuren.java.designpattern.memento;

/**
 * 备忘录  记录游戏进度
 * @author zhuyibin
 */
public class Memento {

    /**
     * 需要保存的属性，1-n 个
     */
    private String state;

    /**
     * 构造方法  相关数据导入
     * @param state
     */
    public Memento(String state) {
        this.state = state;
    }



    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}