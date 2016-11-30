package com.wenniuwuren.spring.aware;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *  Aware使用：Spring提供一些Aware相关接口，比如 BeanFactoryAware等，实现这些接口的Bean初始化后，
 *  可以取得一些对应的资源，实现 BeanFactoryAware 的 bean 初始后，Spring 容器将会注入 BeanFactory 实例
 *
 * Created by Yibin_Zhu on 2016/11/21.
 */
public class Test implements BeanFactoryAware{

    private BeanFactory beanFactory;

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        this.beanFactory = beanFactory;
    }

    public void testAware() {
        Hello hello = (Hello) beanFactory.getBean("hello");
        hello.say();
    }

    public static void main(String[] args) {
        ApplicationContext bf = new ClassPathXmlApplicationContext("awareTest.xml");
        // 不仅仅可以指定 beanName，也可以有构造函数或者工厂方法的方法参数
        Test test = (Test) bf.getBean("testAware");
        test.testAware();
    }
}
