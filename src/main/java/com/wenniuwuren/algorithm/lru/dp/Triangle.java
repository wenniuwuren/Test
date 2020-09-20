/**
 * @(#)Triangle.java, 2020/9/17.
 * author wenniuwuren
 */
package com.wenniuwuren.algorithm.lru.dp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 给定一个三角形，找出自顶向下的最小路径和。每一步只能移动到下一行中相邻的结点上。
 *
 * 相邻的结点 在这里指的是 下标 与 上一层结点下标 相同或者等于 上一层结点下标 + 1 的两个结点。
 *
 *  
 *
 * 例如，给定三角形：
 *
 * [
 *      [2],
 *     [3,4],
 *    [6,5,7],
 *   [4,1,8,3]
 * ]
 * 自顶向下的最小路径和为 11（即，2 + 3 + 5 + 1 = 11）。
 *
 *  
 *
 * 说明：
 * 如果你可以只使用 O(n) 的额外空间（n 为三角形的总行数）来解决这个问题，那么你的算法会很加分。
 *
 */
public class Triangle {

    public int minimumTotal(List<List<Integer>> triangle) {
        // 可以把三角形映射到一个 二维数组，那就和路径数有点像了，只是这里求路径最小值.
        // dp[i][j] 表示从某个节点走到 dp[i][j]的最小路径

        if (triangle == null || triangle.size() == 0) {
            return 0;
        }

        int row = triangle.size();

        if (row == 1) {
            return triangle.get(0).get(0);
        }

        int maxCol = triangle.get(row - 1).size();
        int[][] dp = new int[row][maxCol];

        // 从最后一行出发走到自己就是二维数组的坐标值.
        for (int i = 0; i < maxCol; i++) {
            dp[row - 1][i] = triangle.get(row - 1).get(i);
        }

        // 状态转移方程. dp[i][j] = min(dp[i + 1][j], dp[i + 1][j+1]) + list[i][j]
        for (int i = row - 2; i >= 0; i--) {
            for (int j = triangle.get(i).size() - 1; j >= 0; j--) {
                dp[i][j] = Math.min(dp[i + 1][j], dp[i + 1][j+1]) + triangle.get(i).get(j);
            }
        }

        return dp[0][0];
    }

    public static void main(String[] args) {
        Triangle triangle = new Triangle();
        List<List<Integer>> list = new ArrayList<>();
        list.add(new ArrayList<Integer>((Arrays.asList(2))));
        list.add(new ArrayList<Integer>((Arrays.asList(3,4))));
        list.add(new ArrayList<Integer>((Arrays.asList(6,5,7))));
        list.add(new ArrayList<Integer>((Arrays.asList(4,1,8,3))));

        System.out.println(triangle.minimumTotal(list));
    }
}