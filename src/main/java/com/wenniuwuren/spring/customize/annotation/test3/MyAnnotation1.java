package com.wenniuwuren.spring.customize.annotation.test3;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;  
import java.lang.annotation.Inherited;  
import java.lang.annotation.Retention;  
import java.lang.annotation.RetentionPolicy;  
import java.lang.annotation.Target;  
  
  
@Target(ElementType.FIELD) //字段注解  
@Retention(RetentionPolicy.RUNTIME) //在运行期保留注解信息  
@Documented     //在生成javac时显示该注解的信息  
@Inherited      //标明MyAnnotation1注解可以被使用它的子类继承  
public @interface MyAnnotation1 {  
    String name() default "fieldName";            
    String getFieldValue() default "getField";    
    String setFieldValue() default "setField";   
    public enum FieldValue{MYTEST,MYFIELD,MYVALUE};  
    FieldValue  realValue() default FieldValue.MYFIELD;  
}  