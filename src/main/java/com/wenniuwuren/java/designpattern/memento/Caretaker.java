package com.wenniuwuren.java.designpattern.memento;

/**
 * 管理者
 * @author zhuyibin
 */
public class Caretaker {

    private Memento memento;

    public Memento getMemento() {
        return memento;
    }

    public void setMemento(Memento memento) {
        this.memento = memento;
    }
}