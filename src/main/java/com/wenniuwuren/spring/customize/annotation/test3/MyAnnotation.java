package com.wenniuwuren.spring.customize.annotation.test3;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;  
import java.lang.annotation.RetentionPolicy;  
import java.lang.annotation.Target;  
  
@Target(ElementType.TYPE)  
@Retention(RetentionPolicy.RUNTIME) //在运行期保留注解信息  
public @interface MyAnnotation {  
    //类名注解，默认即为当前类名  
    String name() default "className";  
}  