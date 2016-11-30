package com.wenniuwuren.spring.aop;

/**
 * 用于拦截的 bean
 * Created by hzzhuyibin on 2016/11/22.
 */
public class TestBean {

    private String testStr = "testAOP";


    public String getTestStr() {
        return testStr;
    }

    public void setTestStr(String testStr) {
        this.testStr = testStr;
    }

    public void test() {
        System.out.println("test aop");
    }
}

