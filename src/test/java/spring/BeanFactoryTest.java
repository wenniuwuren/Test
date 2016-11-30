package test.java.spring;

import com.wenniuwuren.spring.MyTestBean;
import org.junit.Test;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.ClassPathResource;
import test.java.BaseTest;

/**
 * Created by hzzhuyibin on 2016/10/31.
 */
public class BeanFactoryTest extends BaseTest {

    @Test
    public void test() {
        BeanFactory bf = new XmlBeanFactory(new ClassPathResource("test.xml"));

        MyTestBean myTestBean = (MyTestBean) bf.getBean("myTestBean");
        myTestBean.setTestStr("foo");
        System.out.println(myTestBean.getTestStr());
    }
}

