/**
 * @(#)NumIslands.java, 2020/9/25.
 * author wenniuwuren
 */
package com.wenniuwuren.algorithm.lru.dfs;

/**
 * 200. 岛屿数量
 * 给你一个由 '1'（陆地）和 '0'（水）组成的的二维网格，请你计算网格中岛屿的数量。
 *
 * 岛屿总是被水包围，并且每座岛屿只能由水平方向或竖直方向上相邻的陆地连接形成。
 *
 * 此外，你可以假设该网格的四条边均被水包围。
 *
 *  
 * 示例 1:
 *
 * 输入:
 * [
 * ['1','1','1','1','0'],
 * ['1','1','0','1','0'],
 * ['1','1','0','0','0'],
 * ['0','0','0','0','0']
 * ]
 * 输出: 1
 * 示例 2:
 *
 * 输入:
 * [
 * ['1','1','0','0','0'],
 * ['1','1','0','0','0'],
 * ['0','0','1','0','0'],
 * ['0','0','0','1','1']
 * ]
 * 输出: 3
 * 解释: 每座岛屿只能由水平和/或竖直方向上相邻的陆地连接而成。
 *
 */
public class NumIslands {

    // 染色+dfs
    public int numIslands(char[][] grid) {
        if (grid == null || grid.length == 0 || grid[0] == null) return 0;

        int row = grid.length;
        int col = grid[0].length;

        int left = 0;
        int right = col - 1;
        int top = 0;
        int bottom = row - 1;

        int result = 0;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (grid[i][j] == '1') {
                    result += 1;
                    backtracking(grid, i, j, left, right, top, bottom);
                }

            }

        }

        return result;
    }

    private void  backtracking(char[][] grid, int currRow, int currCol, int left, int right, int top, int bottom) {
        if (currRow < top || currRow > bottom || currCol < left || currCol > right) {
            return;
        }

        if (grid[currRow][currCol] == '1') {
            grid[currRow][currCol] = '0'; // 染色

            // 左走一步
            backtracking(grid, currRow, currCol - 1, left, right, top, bottom);

            backtracking(grid, currRow, currCol + 1, left, right, top, bottom);

            backtracking(grid, currRow - 1, currCol, left, right, top, bottom);

            backtracking(grid, currRow + 1, currCol, left, right, top, bottom);
        }

    }

    public static void main(String[] args) {
        NumIslands numIslands = new NumIslands();
        System.out.println(numIslands.numIslands(new char[][]{{'1','1','1','1','0'}, {'1','1','0','1','0'},
            {'1','1','0','0','0'}, {'0','0','0','0','0'}}) == 1);

        System.out.println(numIslands.numIslands(new char[][]{{'1','1','0','0','0'}, {'1','1','0','0','0'},
            {'0','0','1','0','0'}, {'0','0','0','1','1'}}) == 3);
    }
}