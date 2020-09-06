/**
 * @(#)MovingCount.java, 2020/9/3.
 * author wenniuwuren
 */
package com.wenniuwuren.algorithm.lru.arrray;

/**
 * 地上有一个m行n列的方格，从坐标 [0,0] 到坐标 [m-1,n-1] 。一个机器人从坐标 [0, 0] 的格子开始移动，它每次可以向左、右、上、下移动一格（不能移动到方格外），也不能进入行坐标和列坐标的数位之和大于k的格子。例如，当k为18时，机器人能够进入方格 [35, 37] ，因为3+5+3+7=18。但它不能进入方格 [35, 38]，因为3+5+3+8=19。请问该机器人能够到达多少个格子？
 *
 *  
 *
 * 示例 1：
 *
 * 输入：m = 2, n = 3, k = 1
 * 输出：3
 * 示例 2：
 *
 * 输入：m = 3, n = 1, k = 0
 * 输出：1
 * 提示：
 *
 * 1 <= n,m <= 100
 * 0 <= k <= 20
 *
 */
public class MovingCount {


    public int movingCount(int m, int n, int k) {

        boolean[][] bloom = new boolean[m][n];
        int[] result = new int[]{0};

        int left = 0;
        int right = n - 1;
        int top = 0;
        int bottom = m - 1;

        dfs(left, right, top, bottom, k, 0, 0, bloom, result);

        return result[0];
    }
    // 走过的不能再走，从各个方向走总能遍历完全部能遍历到的.
    private void dfs(int left, int right, int top, int bottom, int k, int row, int col, boolean[][] bloom, int[] result) {
        if (row >= top && row <= bottom && col >= left && col <= right && !bloom[row][col]) {

            int total1 = 0;
            String stri = row + "";
            for (int s1 = 0; s1 < stri.length(); s1++) {
                total1 = total1 + Integer.valueOf(stri.substring(s1, s1 + 1));
            }

            int total2 = 0;
            String strj = col + "";
            for (int s2 = 0; s2 < strj.length(); s2++) {
                total2 = total2 + Integer.valueOf(strj.substring(s2, s2 + 1));
            }

            if (total1 + total2 > k) {
                return;
            }

            if (!bloom[row][col]) {
                result[0]++;
                bloom[row][col] = true;
            }

            dfs(left, right, top, bottom, k, row - 1, col, bloom, result);
            dfs(left, right, top, bottom, k, row + 1, col, bloom, result);
            dfs(left, right, top, bottom, k, row, col - 1, bloom, result);
            dfs(left, right, top, bottom, k, row, col + 1, bloom, result);
        }


    }

    public static void main(String[] args) {
        MovingCount movingCount = new MovingCount();
        System.out.println(movingCount.movingCount(2, 3, 1) == 3);

        System.out.println(movingCount.movingCount(3, 1, 0) == 1);

        System.out.println(movingCount.movingCount(16, 18, 4) == 15);
    }
}