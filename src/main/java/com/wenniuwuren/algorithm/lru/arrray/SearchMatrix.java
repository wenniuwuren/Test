/**
 * @(#)SearchMatrix.java, 2020/5/24.
 * author wenniuwuren
 */
package com.wenniuwuren.algorithm.lru.arrray;

/**
 * 找到矩阵中 target值， 矩阵行递增，列也是递增。
 */
public class SearchMatrix {

    public boolean searchMatrix(int[][] matrix, int target) {

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (matrix[i][j] == target) {
                    return true;
                }
            }

        }

        return false;
    }

    public static void main(String[] args) {
        SearchMatrix searchMatrix = new SearchMatrix();

        int[][] matrix = new int[][] {{1, 4, 7, 11, 15}, {2, 5, 8, 12, 19}, {3, 6, 9, 16, 22}, {10, 13, 14, 17, 24}, {18, 21, 23, 26, 30}};

        System.out.println(searchMatrix.searchMatrix(matrix, 5));

        System.out.println(searchMatrix.searchMatrix(matrix, 20));

        System.out.println(searchMatrix.searchMatrix(new int[][]{}, 1));
    }
}