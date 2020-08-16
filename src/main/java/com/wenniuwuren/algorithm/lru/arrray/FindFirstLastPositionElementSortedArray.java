
/**
 * @(#)FindFirstLastPositionElementSortedArray.java, 2020/8/15.
 * author wenniuwuren
 */
package com.wenniuwuren.algorithm.lru.arrray;

public class FindFirstLastPositionElementSortedArray {


    public int[] searchRange(int[] nums, int target) {

        int[] result = new int[] {-1, -1};

        int len = nums.length;

        int left = 0; int right = len -1;

        // 二分法找到目标值，然后左右扩散找起始终止下标
        while (left <= right) {

            int mid = left + (right - left) / 2;

            if (target == nums[mid]) {
                int l = mid - 1; int r = mid + 1;
                result[0] = mid; result[1] = mid;
                while (l >= 0) {
                    if (nums[l] == target) {
                        result[0] = l;
                        l--;
                    } else {
                        break;
                    }

                }

                while (r < len) {
                    if (nums[r] == target) {
                        result[1] = r;
                        r++;
                    } else {
                        break;
                    }
                }

                break;
            } else if (target > nums[mid]) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }


        return result;

    }

    public static void main(String[] args) {
        FindFirstLastPositionElementSortedArray firstLastPositionElementSortedArray = new FindFirstLastPositionElementSortedArray();

        System.out.println(firstLastPositionElementSortedArray.searchRange(new int[]{5,7,7,8,8,10}, 8)); // 3,4
//        System.out.println(firstLastPositionElementSortedArray.searchRange(new int[]{5,7,7,8,8,10}, 6)); // -1.-1

//        System.out.println(firstLastPositionElementSortedArray.searchRange(new int[]{5}, 5)); // 0,0

//        System.out.println(firstLastPositionElementSortedArray.searchRange(new int[]{2, 2}, 2)); // 0,1
    }
}