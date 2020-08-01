/**
 * @(#)SpiralMatrix.java, 2020/6/5.
 * author wenniuwuren
 */
package com.wenniuwuren.algorithm.lru.arrray;

public class SpiralMatrix {

    // 没写完
    public int[] spiralOrder(int[][] matrix) {

        if (matrix == null || matrix.length == 0 ||  matrix[0].length == 0) {
            return new int[0];
        }

        int row = matrix[0].length;
        int col = matrix.length;
        int[] result = new int[row * col];

        boolean right = true;
        boolean down = false;
        boolean left = false;
        boolean up = false;
        int index = 0;

        boolean[][] visited = new boolean[row][col];

        int i = 0; int j = 0;
        while (true) {
            if (right) {
                while (j < col) {
                    result[index] = matrix[i][j];
                    visited[i][j] = true;
                    j++;
                    index++;
                }
                down = true;
                right = false;
                j = j - 1;
                i = i + 1;
            } else if (down){
                while (i < row) {
                    result[index] = matrix[i][j];
                    visited[i][j] = true;
                    i++;
                    index++;
                }

                left = true;
                down = false;
                j = j - 1;
            } else if (left) {
                while (j > -1) {
                    result[index] = matrix[i][j];
                    visited[i][j] = true;
                    j--;
                    index++;
                }
                up = true;
                left = false;
                i = i - 1;
                j = j + 1;
            } else if (up) {
                while (row > -1) {
                    result[index] = matrix[i][j];
                    visited[i][j] = true;
                    i--;
                    index++;
                }
                right = true;
                up = false;


            }
        }




    }



}