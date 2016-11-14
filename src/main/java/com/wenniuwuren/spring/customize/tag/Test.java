package main.java.com.wenniuwuren.spring.customize.tag;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by hzzhuyibin on 2016/11/14.
 */
public class Test {

    public static void main(String[] args) {
        ApplicationContext bf = new ClassPathXmlApplicationContext("main/resources/testCustomizeBean.xml");
        User user = (User) bf.getBean("testCustomizeBean");
        System.out.println(user.getUserName() + ", " + user.getEmail());
    }
}

