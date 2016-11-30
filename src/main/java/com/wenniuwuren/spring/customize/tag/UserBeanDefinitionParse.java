package com.wenniuwuren.spring.customize.tag;


import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.xml.AbstractSingleBeanDefinitionParser;
import org.springframework.util.StringUtils;
import org.w3c.dom.Element;


/**
 * 3. 创建一个文件， 实现 AbstractSingleBeanDefinitionParser 接口，用来解析 XSD 文件中的定义和组件定义
 * Created by hzzhuyibin on 2016/11/14.
 */
public class UserBeanDefinitionParse extends AbstractSingleBeanDefinitionParser {

    // Element 对应的类
    protected Class getBeanClass(Element element) {
        return User.class;
    }

    // 从 element 中解析并提取对应的元素
    protected void doParse(Element element, BeanDefinitionBuilder bean) {
        String userName = element.getAttribute("userName");
        String email = element.getAttribute("email");

        // 将提取的数据放入到 BeanDefinitionBuilder 中，待到完成所有 bean 的解析后统一注册到 beanFactory 中
        if (StringUtils.hasText(userName)) {
            bean.addPropertyValue("userName", userName);

        }

        if (StringUtils.hasText(email))
            bean.addPropertyValue("email", email);



    }

}

