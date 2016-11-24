package main.java.com.wenniuwuren.spring.aop;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 测试静态代理加上vm 参数：  -javaagent:D:\MavenRepository\org\springframework\spring-instrument\3.0.4.RELEASE\spring-instrument-3.0.4.RELEASE.jar
 * Created by Yibin_Zhu on 2016/11/21.
 */
public class AopTest {

    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("main/resources/aopTest.xml");
        // 不仅仅可以指定 beanName，也可以有构造函数或者工厂方法的方法参数
        TestBean testBean = (TestBean) context.getBean("test");
        testBean.test();

    }
}
