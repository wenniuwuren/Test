/**
 * @(#)TestListener.java, 2016年11月22日.
 * Copyright 2016 Netease, Inc. All rights reserved.
 * NETEASE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package main.java.com.wenniuwuren.spring.event.listener;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;

/**
 * Created by hzzhuyibin on 2016/11/22.
 */
public class TestListener implements ApplicationListener{
    @Override
    public void onApplicationEvent(ApplicationEvent event) {

        if (event instanceof TestEvent) {
            TestEvent testEvent = (TestEvent)event;
            testEvent.print();
        }
    }
}

