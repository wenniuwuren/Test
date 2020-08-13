/**
 * @(#)ReplaceBlank.java, 2020/8/12.
 * author wenniuwuren
 */
package com.wenniuwuren.algorithm.lru.string;

public class ReplaceBlank {

    public String replaceSpace(String s) {
        if (s == null) {
            return null;
        }

        return s.replace(" ", "%20");
    }

    public static void main(String[] args) {
        ReplaceBlank replaceBlank = new ReplaceBlank();

        System.out.println(replaceBlank.replaceSpace("We are happy."));
    }
}