/**
 * @(#)RomanToInteger.java, 2020/5/31.
 * author wenniuwuren
 */
package com.wenniuwuren.algorithm.lru.string;

/**
 * Symbol       Value
 * I             1
 * V             5
 * X             10
 * L             50
 * C             100
 * D             500
 * M             1000
 *
 * 0:44
 *
 */
public class RomanToInteger {

    public int romanToInt(String s) {

        if (s == null) {
            return 0;
        }

        int result = 0;

        for (int i = 0; i < s.length(); i++) {
            String temp = s.substring(i, i + 1);

            if (temp.equals("I")) {
                if(i + 1 < s.length()) {
                    if (s.substring(i + 1, i + 2).equals("V")) {
                        result += 4;
                        i++;
                    } else if(s.substring(i + 1, i + 2).equals("X")) {
                        result += 9;
                        i++;
                    } else {
                        result += 1;
                    }

                } else {
                    result += 1;
                }
            } else if (temp.equals("X")) {
                if(i + 1 < s.length()) {
                    if (s.substring(i + 1, i + 2).equals("L")) {
                        result += 40;
                        i++;
                    } else if(s.substring(i + 1, i + 2).equals("C")) {
                        result += 90;
                        i++;
                    } else {
                        result += 10;
                    }

                } else {
                    result += 10;
                }
            } else if (temp.equals("C")) {
                if(i + 1 < s.length()) {
                    if (s.substring(i + 1, i + 2).equals("D")) {
                        result += 400;
                        i++;
                    } else if(s.substring(i + 1, i + 2).equals("M")) {
                        result += 900;
                        i++;
                    } else {
                        result += 100;
                    }

                } else {
                    result += 100;
                }
            } else if (temp.equals("V")) {
                result += 5;
            } else if (temp.equals("L")) {
                result += 50;
            } else if (temp.equals("D")) {
                result += 500;
            } else if (temp.equals("M")) {
                result += 1000;
            }

        }

        return result;

    }

    public static void main(String[] args) {
        RomanToInteger romanToInteger = new RomanToInteger();

        System.out.println(romanToInteger.romanToInt("III") == 3);
        System.out.println(romanToInteger.romanToInt("IV") == 4);
        System.out.println(romanToInteger.romanToInt("IX") == 9);
        System.out.println(romanToInteger.romanToInt("LVIII") == 58);
        System.out.println(romanToInteger.romanToInt("MCMXCIV") == 1994);
    }
}