/**
 * @(#)MergeArray.java, 2020/5/24.
 * author wenniuwuren
 */
package com.wenniuwuren.algorithm.lru.arrray;

/**
 * 给你两个有序整数数组 nums1 和 nums2，请你将 nums2 合并到 nums1 中，使 nums1 成为一个有序数组。
 *
 *  
 *
 * 说明:
 *
 * 初始化 nums1 和 nums2 的元素数量分别为 m 和 n 。
 * 你可以假设 nums1 有足够的空间（空间大小大于或等于 m + n）来保存 nums2 中的元素。
 *  
 *
 * 示例:
 *
 * 输入:
 * nums1 = [1,2,3,0,0,0], m = 3
 * nums2 = [2,5,6],       n = 3
 *
 * 输出: [1,2,2,3,5,6]
 *
 */
public class MergeArray {

    public void merge(int[] nums1, int m, int[] nums2, int n) {

        if (m == 0 && n == 0) {
            nums1 = new int[]{};
            return;
        }

        if (m == 0 && n != 0) {
            for (int i = 0; i < n; i++) {
                nums1[i] = nums2[i];
            }
            return;
        }

        if (m != 0 && n == 0) {
            return;
        }


        int[] nums1Temp = new int[m];

        for (int i = 0; i < m; i++) {
            nums1Temp[i] = nums1[i];
        }

        int index1Temp = 0;
        int j = 0;
        for (int i = 0; i < m + n;) {

            if (nums1Temp[index1Temp] < nums2[j]) {
                nums1[i] = nums1Temp[index1Temp];
                index1Temp++;
                i++;
            } else if (nums1Temp[index1Temp] > nums2[j]) {
                nums1[i] = nums2[j];
                j++;
                i++;
            } else {
                nums1[i] = nums1Temp[index1Temp];
                i++;
                index1Temp++;

                nums1[i] = nums2[j];
                i++;
                j++;
            }

            if (j == n) {
                for (int p = index1Temp; p < m; p++, i++) {
                    nums1[i] = nums1Temp[p];
                }
                return;
            }

            if (index1Temp == m) {
                for (int p = j; p < n; p++, i++) {
                    nums1[i] = nums2[p];
                }
                return;
            }

        }

    }

    public static void main(String[] args) {
        MergeArray mergeArray = new MergeArray();

        int num1[] = new int[]{1,2,3,0,0,0};
        mergeArray.merge(num1, 3, new int[]{2,5,6}, 3);

        System.out.println();
    }


}