/**
 * @(#)ReverseWordsInString.java, 2020/8/1.
 * author wenniuwuren
 */
package com.wenniuwuren.algorithm.lru.string;

public class ReverseWordsInString {

    public String reverseWords(String s) {
        if (s == null) {
            return null;
        }

        String[] strSplit = s.split(" ");

        int len = strSplit.length;


        for (int i = 0; i < len; i++) {
            String str = strSplit[i];

            if (str == null || str.length() == 0) {
                strSplit[i] = str;
                continue;
            }

            int strLen = str.length();
            char[] chars = str.toCharArray();
            for (int j = 0, k = strLen - 1; j < strLen && k > -1 && j <= k; j++, k--) {
                char temp = chars[j];
                chars[j] = chars[k];
                chars[k] = temp;
            }

            String charToStr = "";
            for (int m = 0; m < chars.length; m++) {
                charToStr += chars[m];
            }

            strSplit[i] = charToStr;
        }

        String result = "";
        for (int i = 0; i < len; i++) {
            if (i != len - 1) {
                result += strSplit[i] + " ";
            } else {
                result += strSplit[i];
            }
        }

        return result;
    }

    public static void main(String[] args) {
        ReverseWordsInString reverseWordsInString = new ReverseWordsInString();
//        System.out.println(reverseWordsInString.reverseWords("Let's take LeetCode contest").equals("s'teL ekat edoCteeL tsetnoc"));
//        System.out.println(reverseWordsInString.reverseWords("cool").equals("looc"));
        System.out.println(reverseWordsInString.reverseWords(" cool ").equals(" looc "));
    }
}