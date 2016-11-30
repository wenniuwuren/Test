package com.wenniuwuren.spring.aop.time.consume;

/**
 * VM 参数： -javaagent:target/PerfMonAgent.jar
 * Created by hzzhuyibin on 2016/11/25.
 */
public class MethodExeTimeConsume {

    public static void main(String[] args) {
        new MethodExeTimeConsume().test();
    }

    public void test() {
        System.out.println("hello world");
    }
}

