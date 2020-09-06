/**
 * @(#)SearchInRotatedSortedArray.java, 2020/9/5.
 * author wenniuwuren
 */
package com.wenniuwuren.algorithm.lru.arrray;

public class SearchInRotatedSortedArray {

    public int search(int[] nums, int target) {
        if (nums == null) {
            return -1;
        }

        int left = 0; int right = nums.length - 1;

        while (left <= right){
            int mid = left + (right - left)/2;

            if (nums[mid] == target) {
                return mid;
            }
            // 说明mid左边有序。
            // 注意： left + (right - left)/2是把小数部分都省去了的结果，因此当l=0,r=1时会出现
            // l==left + (right - left)/2
            if (nums[left] <= nums[mid]) {
                if (nums[mid] > target && nums[left] <= target) {
                    // target 在区间内  mid = 号在上面判断了.
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }

            } else { // 右边有序
                if (nums[mid] < target && target <= nums[right]) {// 在区间内
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }
        }

        return -1;
    }

    public static void main(String[] args) {
        SearchInRotatedSortedArray sortedArray = new SearchInRotatedSortedArray();
//        System.out.println(sortedArray.search(new int[]{4,5,6,7,0,1,2}, 0));
        System.out.println(sortedArray.search(new int[]{3, 1}, 1) == 1);
    }
}