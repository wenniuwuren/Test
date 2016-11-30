package com.wenniuwuren.spring.event.listener;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * Created by Yibin_Zhu on 2016/11/21.
 */
public class Test{

    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("listenerTest.xml");
        // 不仅仅可以指定 beanName，也可以有构造函数或者工厂方法的方法参数

        TestEvent testEvent = new TestEvent("hello", "wenniuwuren");

        context.publishEvent(testEvent);

    }
}
