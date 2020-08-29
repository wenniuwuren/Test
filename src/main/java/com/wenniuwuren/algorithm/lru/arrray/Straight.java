/**
 * @(#)Straight.java, 2020/8/27.
 * author wenniuwuren
 */
package com.wenniuwuren.algorithm.lru.arrray;

import java.util.Arrays;

public class Straight {

    public boolean isStraight(int[] nums) {

        Arrays.sort(nums);

        int len = nums.length;

        int omnipotent = 0;

        int pre = -1;
        for (int i = 0; i < len; i++) {
            if (nums[i] == 0) {
                omnipotent++;
            } else {
                if (pre == -1) {
                    pre = nums[i];
                } else {
                    if (nums[i] - pre != 1) {
                        if (omnipotent > 0) {
                            omnipotent--; // 万能牌用一张
                            pre = pre + 1;

                            i--; // 不符合指针不动
                        } else {
                            return false;
                        }

                    } else {
                        pre = nums[i];
                    }

                }

            }


        }

        return true;
    }

    public static void main(String[] args) {
        Straight straight = new Straight();
        System.out.println(straight.isStraight(new int[]{1,2,3,4,5}));
        System.out.println(straight.isStraight(new int[]{0,0,1,2,5}));
        System.out.println(straight.isStraight(new int[]{0,1,2,3,6}));
        System.out.println(straight.isStraight(new int[]{1,3,4,5,6}));
    }
}