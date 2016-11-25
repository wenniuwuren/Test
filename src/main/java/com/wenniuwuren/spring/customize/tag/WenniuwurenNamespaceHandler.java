package main.java.com.wenniuwuren.spring.customize.tag;

import org.springframework.beans.factory.xml.NamespaceHandlerSupport;

/**
 * 4. 创建一个 Hanlder 文件，继承自 NamespaceHandlerSupport，目的是将组件注册到 Spring 容器
 *
 * 本类的命名规范是：自定义标签 namespace + NamespaceHandler
 *
 * 遇到自定义标签 <user:aaa 这样类似于 user 开头的元素，就会把这个元素扔给对应的UserBeanDefinitionParse 去接信息
 * Created by hzzhuyibin on 2016/11/14.
 */
public class WenniuwurenNamespaceHandler extends NamespaceHandlerSupport{
    @Override
    public void init() {
        registerBeanDefinitionParser("user", new UserBeanDefinitionParse());
    }
}

