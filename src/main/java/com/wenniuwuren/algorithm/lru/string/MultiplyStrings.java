/**
 * @(#)MultiplyStrings.java, 2020/8/13.
 * author wenniuwuren
 */
package com.wenniuwuren.algorithm.lru.string;

public class MultiplyStrings {


    // 如果用 转换为 int 或者 long 的方法都会溢出.
    public String multiply(String num1, String num2) {

        if (num1 == null || num2 == null) {
            return "";
        }

        int len1 = num1.length();
        int len2 = num2.length();

        if (len1 == 0 || len2 == 0) {
            return "";
        }

        if (num1.equals("0") || num2.equals("0")) {
            return "0";
        }

        String result = "";


        for (int i = len2 - 1; i > -1; i--) {
            StringBuilder currStr = new StringBuilder();
            int add = 0;
            for (int j = len2 - 1; j > i; j--) {
                currStr.append("0");
            }

            int y = num2.charAt(i) - '0';

            for (int k = len1 - 1; k > -1; k--) {
                int x = num1.charAt(k) - '0';
                int re = x * y + add;

                add = 0;

                int mode = re % 10;
                currStr.append(mode);
                add = re / 10;
            }

            if (add != 0) { // 最后一位的进位
                currStr.append(add);
            }

            result = addStrings(result, currStr.reverse().toString());

        }


        return result;
    }

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
        MultiplyStrings multiplyStrings = new MultiplyStrings();
        System.out.println(multiplyStrings.multiply("2", "3").equals("6"));
        System.out.println(multiplyStrings.multiply("123", "456").equals("56088"));
        System.out.println(multiplyStrings.multiply("123456789", "987654321").equals("121932631112635269"));
        System.out.println(multiplyStrings.multiply("6913259244", "71103343").equals("491555843274052692"));

    }
}