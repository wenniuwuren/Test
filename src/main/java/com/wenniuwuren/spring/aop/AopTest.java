package com.wenniuwuren.spring.aop;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 测试静态代理加上vm 参数：  -javaagent:D:\MavenRepository\org\springframework\spring-instrument\3.0.4.RELEASE\spring-instrument-3.0.4.RELEASE.jar
 * 书中的输出结果：
 * before test
 * before1
 * test aop
 * after test
 * after1
 * Created by Yibin_Zhu on 2016/11/21.
 */
public class AopTest {

    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("aopTest.xml");
        // 不仅仅可以指定 beanName，也可以有构造函数或者工厂方法的方法参数
        TestBean testBean = (TestBean) context.getBean("test");
        testBean.test();

    }
}
