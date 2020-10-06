/**
 * @(#)SearchMatrix.java, 2020/10/6.
 * author wenniuwuren
 */
package com.wenniuwuren.algorithm.lru.binarysearch;

/**
 * 74. 搜索二维矩阵
 *
 * 编写一个高效的算法来判断 m x n 矩阵中，是否存在一个目标值。该矩阵具有如下特性：
 *
 * 每行中的整数从左到右按升序排列。
 * 每行的第一个整数大于前一行的最后一个整数。
 * 示例 1:
 *
 * 输入:
 * matrix = [
 *   [1,   3,  5,  7],
 *   [10, 11, 16, 20],
 *   [23, 30, 34, 50]
 * ]
 * target = 3
 * 输出: true
 * 示例 2:
 *
 * 输入:
 * matrix = [
 *   [1,   3,  5,  7],
 *   [10, 11, 16, 20],
 *   [23, 30, 34, 50]
 * ]
 * target = 13
 * 输出: false
 *

 */
public class SearchMatrix {

    public boolean searchMatrix(int[][] matrix, int target) {

        if (matrix == null || matrix.length == 0 || matrix[0] == null || matrix[0].length == 0)
            return false;

        int row = matrix.length;
        int col = matrix[0].length;

        for (int i = 0; i < row; i++) {
            int left = 0; int right = col - 1;

            if (target <= matrix[i][col - 1]) {
                while (left <= right) {
                    int mid = left + (right - left)/2;

                    if (matrix[i][mid] == target) {
                        return true;
                    } else if (matrix[i][mid] < target) {
                        left = mid + 1;
                    } else {
                        right = mid - 1;
                    }

                }
            }
            // for 循环直接进入下一行

        }

        return false;

    }

    public static void main(String[] args) {
        SearchMatrix searchMatrix = new SearchMatrix();
        System.out.println(searchMatrix.searchMatrix(new int[][]{{1, 3, 5, 7}, {10, 11, 16, 20}, {23, 30, 34, 50}}, 3) == true);
        System.out.println(searchMatrix.searchMatrix(new int[][]{{1, 3, 5, 7}, {10, 11, 16, 20}, {23, 30, 34, 50}}, 13) == false);
        System.out.println(searchMatrix.searchMatrix(new int[][]{{1}, {3}}, 3) == true);
    }
}