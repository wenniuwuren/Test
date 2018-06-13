package com.wenniuwuren.java;

import java.util.TreeMap;

/**
 * @author zhuyibin
 */
public class TreeMapTest {


    public static void main(String[] args) {
        TreeMap<String, Integer> treeMap = new TreeMap<>();
        treeMap.put("aa", 1);
        treeMap.put("ac", 2);
        treeMap.put("bc", 3);
        treeMap.put("ab", 3);

        treeMap.put("aa", 3); // 相同 key

        System.out.println(treeMap);
    }
}