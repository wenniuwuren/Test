package com.wenniuwuren.jvm;

import java.util.ArrayList;
import java.util.List;

/**
 * VM Args: -Xms20m -Xmx20m -XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=C:\HeapDump
 * Created by Yibin_Zhu on 2017/3/5.
 */
public class HeapOOM {

    public static void main(String[] args) {

        // -XX:+HeapDumpOnOutOfMemoryError 让虚拟机在出现内存溢出异常时Dump出当前内存堆转储快照
        List<Object> list = new ArrayList<Object>();

        while (true) {
            list.add(new Object());
        }
    }
}
