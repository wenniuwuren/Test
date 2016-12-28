package com.wenniuwuren.java.propertydescriptor;

/**
 * 访问一个Java类的私有属性，并且该类不提供访问该私有属性的共有方法（ public getXXX() ）
 */
public class JavaBeanTest {

    public static void main(String[] args) throws Exception {
        BeanEntity bean = new BeanEntity();
        String x = "x";//BeanEntity的属性名称
        BeanUtils utils = new BeanUtils(x, bean);
        utils.setProperty(12);
        Object value = utils.getProperty();
        System.out.println(value);

    }
}