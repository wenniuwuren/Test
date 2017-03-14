package com.wenniuwuren.jvm;

import sun.misc.Unsafe;

import java.lang.reflect.Field;

/**
 * Windows平台下，直接内存溢出，Heap Dump 完全没有信息可以看到。OS X 直接死机
 * 使用unsafe分配本机内存
 * -Xmx20m -XX:MaxDirectMemorySize=10m -XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=C:\HeapDump
 * Created by zhuyb on 15/7/19.
 */
public class DirectMemoryOOM {

    public static void main(String[] args) {

        try {
            Field unsafeField = Unsafe.class.getDeclaredFields()[0];
            unsafeField.setAccessible(true);
            Unsafe unsafe = (Unsafe) unsafeField.get(null);
            while (true) {
                unsafe.allocateMemory(1024 * 1024);
            }
        } catch (Exception e) {

            System.out.println(e);
        }
    }
}