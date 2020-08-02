/**
 * @(#)SetMatrixZeroes.java, 2020/8/2.
 * author wenniuwuren
 */
package com.wenniuwuren.algorithm.lru.arrray;

import java.util.ArrayDeque;
import java.util.Deque;

public class SetMatrixZeroes {

    public void setZeroes(int[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0] == null || matrix[0].length == 0) {
            return;
        }

        Deque<Integer> row = new ArrayDeque<>();
        Deque<Integer> col = new ArrayDeque<>();

        int r = matrix.length;
        int c = matrix[0].length;

        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                if (matrix[i][j] == 0) {
                    row.push(i);
                    col.push(j);
                }

            }
        }

        while (!row.isEmpty()) {
            int rowIndex = row.pop();
            int colIndex = col.pop();

            // 整行赋值0
            for (int i = 0; i < c; i++) {
                matrix[rowIndex][i] = 0;
            }

            // 整列赋值0
            for (int i = 0; i < r; i++) {
                matrix[i][colIndex] = 0;
            }


        }

    }

    public static void main(String[] args) {
        SetMatrixZeroes setMatrixZeroes = new SetMatrixZeroes();
//        setMatrixZeroes.setZeroes(new int[][]{{1,1,1},{1,0,1},{1,1,1}});

        setMatrixZeroes.setZeroes(new int[][]{{0,1,2,0},{3,4,5,2},{1,3,1,5}});
    }
}