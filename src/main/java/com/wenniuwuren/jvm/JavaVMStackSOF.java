package com.wenniuwuren.jvm;

/**
 * -Xss200k 固定每条线程虚拟机栈的大小,造成 StackOverflowError
 * 注意：Window下运行会死机，而 OS X 平台下运行不会死机
 * Created by zhuyb on 16/4/2.
 */
public class JavaVMStackSOF {

    private static Integer stackLength = 0;

    public void stackLeak() {
        ++stackLength;
        stackLeak();
    }

    public static void main(String[] args) {
        try {
            JavaVMStackSOF javaVMStackSOF = new JavaVMStackSOF();
            javaVMStackSOF.stackLeak();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}