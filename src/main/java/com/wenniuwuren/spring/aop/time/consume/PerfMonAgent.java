package main.java.com.wenniuwuren.spring.aop.time.consume;

import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.Instrumentation;

/**
 * Created by hzzhuyibin on 2016/11/25.
 */
public class PerfMonAgent {

    static private Instrumentation inst = null;

    // 这个方法在 main 方法被调用前调用
    public static void premain(String agentArgs, Instrumentation ins) {

        System.out.println("PerfMonAgent.premain() was called.");
        // 初始化的这个静态变量我们用来跟踪信息
        inst = ins;

        ClassFileTransformer trans = new PerfMonXformer();
        System.out.println("Adding a PerfMonXformer instance to JVM");
        inst.addTransformer(trans);
    }
}

