/**
 * @(#)ReverseString.java, 2020/7/30.
 * author wenniuwuren
 */
package com.wenniuwuren.algorithm.lru.arrray;

public class ReverseString {

    public void reverseString(char[] s) {

        if (s == null) {
            return;
        }

        int len = s.length;

        for (int i = 0,j = len-1; i < len && j > -1 && i <= j; i++, j--) {
            char temp = s[i];
            s[i] = s[j];
            s[j] = temp;

        }

        System.out.println();
    }

    public static void main(String[] args) {
        ReverseString reverseString = new ReverseString();

//        reverseString.reverseString(new char[]{'h','e','l','l','o'});

        reverseString.reverseString(new char[]{'H','a','n','n','a','h'});
    }
}