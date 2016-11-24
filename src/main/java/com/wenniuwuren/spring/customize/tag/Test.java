package main.java.com.wenniuwuren.spring.customize.tag;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;

/**
 * Created by hzzhuyibin on 2016/11/14.
 */
public class Test {

    public static void main(String[] args) {
//        BeanFactory bf = new XmlBeanFactory(new ClassPathResource("main/resources/testCustomizeBean.xml"));
        ApplicationContext bf = new ClassPathXmlApplicationContext("main/resources/testCustomizeBean.xml");
        // 不仅仅可以指定 beanName，也可以有构造函数或者工厂方法的方法参数
        User user = (User) bf.getBean("testCustomizeBean");
        System.out.println(user.getUserName() + ", " + user.getEmail());
    }
}

