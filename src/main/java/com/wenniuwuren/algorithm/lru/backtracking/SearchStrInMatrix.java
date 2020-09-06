/**
 * @(#)SearchStrInMatrix.java, 2020/9/2.
 * author wenniuwuren
 */
package com.wenniuwuren.algorithm.lru.backtracking;

/**
 * 请设计一个函数，用来判断在一个矩阵中是否存在一条包含某字符串所有字符的路径。路径可以从矩阵中的任意一格开始，每一步可以在矩阵中向左、右、上、下移动一格。如果一条路径经过了矩阵的某一格，那么该路径不能再次进入该格子。例如，在下面的3×4的矩阵中包含一条字符串“bfce”的路径（路径中的字母用加粗标出）。
 *
 * [["a","b","c","e"],
 * ["s","f","c","s"],
 * ["a","d","e","e"]]
 *
 * 但矩阵中不包含字符串“abfb”的路径，因为字符串的第一个字符b占据了矩阵中的第一行第二个格子之后，路径不能再次进入这个格子。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：board = [["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]], word = "ABCCED"
 * 输出：true
 * 示例 2：
 *
 * 输入：board = [["a","b"],["c","d"]], word = "abcd"
 * 输出：false
 * 提示：
 *
 * 1 <= board.length <= 200
 * 1 <= board[i].length <= 200
 *
 */
public class SearchStrInMatrix {

    public boolean exist(char[][] board, String word) {
        boolean[] result = new boolean[]{false};
        int row = board.length;
        int col = board[0].length;

        boolean[][] bloom = null;

        int left = 0;
        int right = col - 1;
        int top = 0;
        int bottom = row - 1;

        for (int r = 0; r < row; r++) {
            for (int c = 0; c < col; c++) {
                bloom = new boolean[row][col];
                dfs(board, bloom, r, c, left, right, top, bottom, word, 0, result, row, col);
            }
        }

        return result[0];
    }

    private void dfs(char[][] board, boolean[][] bloom, int r, int c, int left, int right, int top, int bottom, String word, int curr, boolean[] result, int row, int col) {
        if (result[0]) {
            return;
        }

        if (curr > (word.length() - 1)) {
            result[0] =  true;
            return;
        }

        String currStr = word.substring(curr, curr + 1);
        if (r < row && c < col && r >= 0 && c >= 0) {
            if (!bloom[r][c] && currStr.equals(board[r][c] + "")) {
                // 从开头开始进行对矩阵的深度优先遍历
                bloom[r][c] = true;

                // 左移一格
//                if (c - 1 >= left) {
                dfs(board, bloom, r, c - 1, left, right, top, bottom, word, curr + 1, result, row, col);
//                }

                // 右
//                if (c + 1 <= right) {
                dfs(board, bloom, r, c + 1, left, right, top, bottom, word, curr + 1, result, row, col);
//                }

                // 上
//                if (r - 1 >= top) {
                dfs(board, bloom, r - 1, c, left, right, top, bottom, word, curr + 1, result, row, col);
//                }

                // 下
//                if (r + 1 <= bottom) {
                dfs(board, bloom, r + 1, c, left, right, top, bottom, word, curr + 1, result, row, col);
//                }

                // 以这个为起点找一圈没找到就，回溯
                bloom[r][c] = false;
            }
        }
    }

    public static void main(String[] args) {
        SearchStrInMatrix strInMatrix = new SearchStrInMatrix();
        System.out.println(strInMatrix.exist(new char[][]{{'A', 'B', 'C', 'E'},{'S', 'F', 'E', 'S'},
            {'A', 'D', 'E', 'E'}}, "ABCESEEEFS") == true);

        System.out.println(strInMatrix.exist(new char[][]{{'A'},}, "A") == true);

        System.out.println(strInMatrix.exist(new char[][]{{'A'},}, "A") == true);

        System.out.println(strInMatrix.exist(new char[][]{{'A', 'B', 'C', 'E'},{'S', 'F', 'C', 'S'},
            {'A', 'D', 'E', 'E'}}, "ABCCED") == true);

        System.out.println(strInMatrix.exist(new char[][]{{'a', 'b'},{'c', 'd'}}, "abcd") == false);

    }
}