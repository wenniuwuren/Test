/**
 * @(#)ValidPalindrome.java, 2020/6/19.
 * author wenniuwuren
 */
package com.wenniuwuren.algorithm.lru.string;

public class ValidPalindrome {

    public boolean isPalindrome(String s) {
        if (s == null || "".equals(s)) {
            return true;
        }

        int len = s.length();

        for (int i = 0, j = len - 1; i <= j; i++, j--) {
            Character start = null;

            while (i + 1 <= j && !(Character.isAlphabetic(s.charAt(i)) || Character.isDigit(s.charAt(i)))) {
                i++;
            }
            start = s.charAt(i);

            Character end = null;
            while (i <= j - 1 && !(Character.isAlphabetic(s.charAt(j)) || Character.isDigit(s.charAt(j)))) {
                j--;
            }
            end = s.charAt(j);

            if (!String.valueOf(start).toLowerCase().equals(String.valueOf(end).toLowerCase())) {
                return false;
            }
        }

        return true;

    }

    public static void main(String[] args) {
        ValidPalindrome validPalindrome = new ValidPalindrome();
        System.out.println(validPalindrome.isPalindrome("A man, a plan, a canal: Panama") == true);

        System.out.println(validPalindrome.isPalindrome("race a car") == false);

        System.out.println(validPalindrome.isPalindrome("aaa") == true);

        System.out.println(validPalindrome.isPalindrome(" ") == true);

        System.out.println(validPalindrome.isPalindrome("0P") == false);
    }
}