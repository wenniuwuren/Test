/**
 * @(#)SpiralMatrix.java, 2020/6/5.
 * author wenniuwuren
 */
package com.wenniuwuren.algorithm.lru.arrray;

import java.util.ArrayList;
import java.util.List;

public class SpiralMatrix {
    // leetcode 54
    public List<Integer> spiralOrder(int[][] matrix) {

        List<Integer> result = new ArrayList<>();

        if (matrix == null || matrix.length == 0
            || matrix[0] == null || matrix[0].length == 0) {
            return result;
        }

        int row = matrix.length;
        int col = matrix[0].length;

        // 上下左右边界
        int top = 0;
        int bottom = row - 1;
        int left = 0;
        int right = col - 1;

        int total = row * col;

        while (total > 0) {

            for (int i = left; i <= right && total > 0; i++) {
                result.add(matrix[top][i]);
                total--;

            }
            top++; // 下移一行

            for (int i = top; i <= bottom && total > 0; i++) {
                result.add(matrix[i][right]);
                total--;
            }
            right--; // 左移一列

            for (int i = right; i >= left && total > 0; i--) {
                result.add(matrix[bottom][i]);
                total--;
            }
            bottom--; // 上移一行

            for (int i = bottom; i >= top && total > 0; i--) {
                result.add(matrix[i][left]);
                total--;
            }
            left++; // 右移一列

        }

        return result;

    }

    public static void main(String[] args) {
        SpiralMatrix spiralMatrix = new SpiralMatrix();
//        System.out.println(spiralMatrix.spiralOrder(new int[][]{{ 1, 2, 3}, {4, 5, 6}, {7, 8, 9}}));
        System.out.println(spiralMatrix.spiralOrder(new int[0][0]));
    }

}