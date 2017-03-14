package com.wenniuwuren.jvm;

import java.util.ArrayList;
import java.util.List;

/**
 * 常量池溢出异常
 * Created by zhuyb on 15/7/19.
 */
public class RuntimeConstantPoolOOM {

    public static void main(String[] args) {
        // JDK1.7 返回 true, 第一次出现的字符串,只在常量池中记录下
        String str1 = new StringBuilder("计算机").append("软件").toString();
        System.out.println(str1.intern() == str1);

        //JDK1.7 返回 false, 因为常量池存在,直接从常量池中取
        String str2 = new StringBuilder("ja").append("va").toString();
        System.out.println(str2.intern() == str2);
    }


    // -XX:PermSize=10m -XX:MaxPermSize=10m   JDK1.6 会 OOM PermGen space
    // JDK1.7 会一直运行下去,不会溢出.因为 JDK1.7开始废除"永久代"
//    public static void main(String[] args) {
//
//        List<String> list = new ArrayList<>(1024);
//        int i = 0;
//        while (true) {
//            list.add(String.valueOf(i++).intern());
//        }
//
//    }
}