package main.java.com.wenniuwuren.spring.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;

/**
 * 切面
 * Created by hzzhuyibin on 2016/11/22.
 */
@Aspect
public class AspectJTest {

    @Pointcut("execution(* *.test(..))")
    public void test() {

    }

    @Before("test()")
    public void beforeTest() {
        System.out.println("before test");
    }

    @After("test()")
    public void afterTest() {
        System.out.println("after test");
    }

    @Around("test()")
    public Object arountTest(ProceedingJoinPoint proceedingJoinPoint) {
        System.out.println("before1");
        Object o = null;
        try {
            o = proceedingJoinPoint.proceed();
        } catch (Throwable e) {
            e.printStackTrace();
        }
        System.out.println("after1");
        return o;
    }
}

