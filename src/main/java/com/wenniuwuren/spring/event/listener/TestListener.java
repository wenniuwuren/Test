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

