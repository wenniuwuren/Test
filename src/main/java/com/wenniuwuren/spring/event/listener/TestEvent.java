/**
 * @(#)TestEvent.java, 2016年11月22日.
 * Copyright 2016 Netease, Inc. All rights reserved.
 * NETEASE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package main.java.com.wenniuwuren.spring.event.listener;


import org.springframework.context.ApplicationEvent;

/**
 * 定义监听事件
 * Created by hzzhuyibin on 2016/11/22.
 */
public class TestEvent extends ApplicationEvent {

    public String msg;

    /**
     * Create a new ApplicationEvent.
     *
     * @param source the object on which the event initially occurred (never {@code null})
     */
    public TestEvent(Object source) {
        super(source);
    }

    public TestEvent(Object source, String msg) {
        super(source);
        this.msg = msg;
    }

    public void print() {
        System.out.println(msg);
    }

}

