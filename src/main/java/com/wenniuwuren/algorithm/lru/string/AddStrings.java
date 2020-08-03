/**
 * @(#)AddStrings.java, 2020/8/3.
 * author wenniuwuren
 */
package com.wenniuwuren.algorithm.lru.string;

public class AddStrings {

    public String addStrings(String num1, String num2) {
        if (num1 == null || num2 == null) {
            return null;
        }

        int len1 = num1.length();
        int len2 = num2.length();

        StringBuilder result = new StringBuilder("");
        int add = 0; // 是否需要进位
        for (int i = len1 - 1, j = len2 - 1; i > -1 || j > -1 || add != 0; i--, j--) {
            int sum = 0;
            if (i > -1 && j > -1) {
                sum = (num1.charAt(i) - '0') + (num2.charAt(j) - '0');
            }

            if (i <= -1 && j > -1) {
                sum = 0 + (num2.charAt(j) - '0');
            }

            if (i > -1 && j <= -1) {
                sum = (num1.charAt(i) - '0') + 0;
            }
            sum = sum + add; // 注意 5 + 5 情况
            add = 0;
            if (sum >= 10) {
                add = 1;
                sum = sum - 10;
            }

            result.append(sum);
        }

        return result.reverse().toString();
    }

    public static void main(String[] args) {
        AddStrings addStrings = new AddStrings();
        System.out.println(addStrings.addStrings("123", "87"));
        System.out.println(addStrings.addStrings("5", "5"));
    }
}