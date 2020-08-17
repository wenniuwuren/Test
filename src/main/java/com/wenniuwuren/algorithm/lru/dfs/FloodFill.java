/**
 * @(#)FloodFill.java, 2020/8/16.
 * author wenniuwuren
 */
package com.wenniuwuren.algorithm.lru.dfs;

import java.util.ArrayDeque;
import java.util.Deque;

public class FloodFill {

    // 如果相同颜色不处理，就不用标记数组了.
    public int[][] floodFill(int[][] image, int sr, int sc, int newColor) {

        int row = image.length;
        int col = image[0].length;

        int left = 0;
        int right = col - 1;
        int top = 0;
        int bottom = row - 1;

        Deque<Integer> rStack = new ArrayDeque<>();
        Deque<Integer> cStack = new ArrayDeque<>();

        // 标记数组 访问过标记为true
        boolean[][] flag = new boolean[row][col];

        rStack.push(sr);
        cStack.push(sc);

        int oldColor = image[sr][sc];

        dfs(rStack, cStack, left, right, top, bottom, oldColor, newColor, image, flag);

        return image;
    }

    private void dfs(Deque<Integer> rStack,  Deque<Integer> cStack, int left, int right, int top, int bottom,
        int oldColor, int newColor, int[][] image, boolean[][] flag) {
        while (!rStack.isEmpty()) {
            int tempR = rStack.pop();
            int tempC = cStack.pop();


            // 这个节点匹配后续周边节点才有继续遍历的意义
            if (!flag[tempR][tempC] && image[tempR][tempC] == oldColor) {
                image[tempR][tempC] = newColor;
                flag[tempR][tempC] = true;

                // 左移一位
                if (tempC - 1 >= left) {
                    rStack.push(tempR);
                    cStack.push(tempC - 1);
                    dfs(rStack, cStack, left, right, top, bottom, oldColor, newColor, image, flag);
                }

                // 右移一位
                if (tempC + 1 <= right) {
                    rStack.push(tempR);
                    cStack.push(tempC + 1);
                    dfs(rStack, cStack, left, right, top, bottom, oldColor, newColor, image, flag);
                }

                // 上
                if (tempR - 1 >= top) {
                    rStack.push(tempR - 1);
                    cStack.push(tempC);
                    dfs(rStack, cStack, left, right, top, bottom, oldColor, newColor, image, flag);
                }

                // 下
                if (tempR + 1 <= bottom) {
                    rStack.push(tempR + 1);
                    cStack.push(tempC);
                    dfs(rStack, cStack, left, right, top, bottom, oldColor, newColor, image, flag);
                }


            }


        }


    }

    public static void main(String[] args) {
        FloodFill fill = new FloodFill();
        fill.floodFill(new int[][]{{1,1,1},{1,1,0},{1,0,1}}, 1,1, 2);
//        fill.floodFill(new int[][]{{1}}, 0,0, 2);
//        fill.floodFill(new int[][]{{0,0,0},{0,1,1}}, 1,1, 1);
    }
}