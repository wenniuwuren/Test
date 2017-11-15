package com.wenniuwuren.spring.customize.annotation.test3;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;  
import java.lang.reflect.Method;  
  
public class TestAnnotation {  
    public static void main(String[] args){  
        MyTest myTest = new MyTest();  
          
        Annotation[] annotations = myTest.getClass().getAnnotations();  //获取类的所有注解  
        for(Annotation anno:annotations){  
            if(anno instanceof MyAnnotation){  
                MyAnnotation myAnnotation = (MyAnnotation)anno;  
                System.out.println("className:"+myAnnotation.name());  
            }else if(anno instanceof MyAnnotation1){  
                MyAnnotation1 myAnnotation1 = (MyAnnotation1)anno;  
                System.out.println("FiledName:"+myAnnotation1.name());  
                System.out.println("setFieldValue"+myAnnotation1.setFieldValue());  
                System.out.println("getFieldValue"+myAnnotation1.getFieldValue());  
                System.out.println("realValue"+myAnnotation1.realValue());  
            }  
        }  
          
        Field[] fields = myTest.getClass().getDeclaredFields();//获取所有注解字段  
        for(Field field:fields){  
            if(field.isAnnotationPresent(MyAnnotation1.class)){  
                MyAnnotation1 myAnno = (MyAnnotation1)field.getAnnotation(MyAnnotation1.class);  
                System.out.println(field.getName()+"-name:"+myAnno.name());  
                System.out.println(field.getName()+"-getFieldValue:"+myAnno.getFieldValue());  
                System.out.println(field.getName()+"-setFieldValue:"+myAnno.setFieldValue());  
                System.out.println(field.getName()+"-realValue:"+myAnno.realValue());  
            }  
        }  
          
        Method[] methods = myTest.getClass().getMethods();//获取所有方法  
        for(Method method:methods){  
            if(method.isAnnotationPresent(MyAnnotation1.class)){  
                MyAnnotation1 myAnno1 = (MyAnnotation1)method.getAnnotation(MyAnnotation1.class);  
                System.out.println(myAnno1.getClass());  
            }  
        }  
    }  
}  
