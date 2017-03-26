package com.wenniuwuren.zookeeper;

import org.apache.zookeeper.AsyncCallback;

class MyStringCallack implements AsyncCallback.StringCallback {

        @Override
        public void processResult(int i, String s, Object o, String s1) {
            System.out.println("create path result:[" + i + ", " + s + ", " + o + ", real path name" + s1);
        }
    }