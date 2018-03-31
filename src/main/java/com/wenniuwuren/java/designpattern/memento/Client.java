
package com.wenniuwuren.java.designpattern.memento;

/**
 * @author zhuyibin
 */
public class Client {

    public static void main(String[] args) {
        Originator originator = new Originator();
        originator.setState("before fight boss");
        originator.show();

        // 备忘状态
        Caretaker caretaker = new Caretaker();
        caretaker.setMemento(originator.createMemento());

        originator.setState("finish fight boss");
        originator.show();

        // 恢复之前备忘状态
        originator.setMemento(caretaker.getMemento());
        originator.show();

    }
}