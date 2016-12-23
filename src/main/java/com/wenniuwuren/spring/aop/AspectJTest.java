package com.wenniuwuren.spring.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;

/**
 * 封装 Pointcut 和 advice 的 Advisor
 * Created by hzzhuyibin on 2016/11/22.
 */
@Aspect
public class AspectJTest {// Advisor

    @Pointcut("execution(* *.test(..))")
    public void test() {

    }
    // before advice
    @Before("test()")
    public void beforeTest() {
        System.out.println("before test");
    }

    // after advice
    @After("test()")
    public void afterTest() {
        System.out.println("after test");
    }

    // around advice
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

