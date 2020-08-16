/**
 * @(#)TwoDimensionArray.java, 2020/8/14.
 * author wenniuwuren
 */
package com.wenniuwuren.algorithm.lru.arrray;

public class TwoDimensionArraySearch {

    public boolean findNumberIn2DArray(int[][] matrix, int target) {

        if (matrix == null || matrix.length == 0 || matrix[0] == null || matrix[0].length == 0) {
            return false;
        }

        int row = matrix.length;
        int col = matrix[0].length;


        int i = 0; int j = col - 1;

        while (j >=0 && i < row) {
            int temp = matrix[i][j];
            if (target == temp) {
                return true;
            } else if (target > matrix[i][j]) {
                i++;
            } else {
                j--;
            }
        }


        return false;
    }

    public static void main(String[] args) {
        TwoDimensionArraySearch twoDimensionArraySearch = new TwoDimensionArraySearch();
        System.out.println(twoDimensionArraySearch.findNumberIn2DArray(new int[][]{{1,4,7,11,15},{2, 5, 8, 12, 19}, {3, 6, 9, 16, 22}, {10, 13, 14, 17, 24},
        {18, 21, 23, 26, 30}}, 5) == true);

        System.out.println(twoDimensionArraySearch.findNumberIn2DArray(new int[][]{{1,4,7,11,15},{2, 5, 8, 12, 19}, {3, 6, 9, 16, 22}, {10, 13, 14, 17, 24},
            {18, 21, 23, 26, 30}}, 20) == false);

        System.out.println(twoDimensionArraySearch.findNumberIn2DArray(new int[][]{{1,2,3,4,5},{6,7,8,9,10}, {11,12,13,14,15}, {16,17,18,19,20},
            {21,22,23,24,25}}, 19) == true);

        System.out.println(twoDimensionArraySearch.findNumberIn2DArray(new int[][]{{-5}
            }, -5) == true);

        System.out.println(twoDimensionArraySearch.findNumberIn2DArray(new int[][]{{-5}
        }, -10) == false);
    }
}

