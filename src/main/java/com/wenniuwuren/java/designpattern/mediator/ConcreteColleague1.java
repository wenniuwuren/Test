
package com.wenniuwuren.java.designpattern.mediator;

/**
 * @author zhuyibin
 */
public class ConcreteColleague1 extends Colleague{
    public ConcreteColleague1(Mediator mediator) {
        super(mediator);
    }

    public void send(String message) {
        mediator.send(message, this);
    }

    public void notify(String message) {
        System.out.println("ConcreteColleague1 get message: " + message);
    }
}