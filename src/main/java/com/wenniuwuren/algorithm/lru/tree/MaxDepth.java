/**
 * @(#)MaxDepth.java, 2020/9/12.
 * author wenniuwuren
 */
package com.wenniuwuren.algorithm.lru.tree;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 给定一个二叉树，找出其最大深度。
 *
 * 二叉树的深度为根节点到最远叶子节点的最长路径上的节点数。
 *
 * 说明: 叶子节点是指没有子节点的节点。
 *
 * 示例：
 * 给定二叉树 [3,9,20,null,null,15,7]，
 *
 *     3
 *    / \
 *   9  20
 *     /  \
 *    15   7
 * 返回它的最大深度 3 。
 *
 */
public class MaxDepth {

    public int maxDepth(TreeNode root) {
        if (root == null) return 0;

        // bfs
        Deque<TreeNode> queue = new ArrayDeque<>();

        int maxDepth = 0;
        queue.add(root);
        int currDepth = 0;
        while (!queue.isEmpty()) {

            currDepth++;
            int count = queue.size();
            for (int i = 0; i < count; i++) {
                TreeNode treeNode = queue.poll();

                if (treeNode.left == null && treeNode.right == null) {
                    maxDepth = Math.max(maxDepth, currDepth);
                }

                if (treeNode.left != null) {
                    queue.add(treeNode.left);
                }

                if (treeNode.right != null) {
                    queue.add(treeNode.right);
                }
            }


        }

        return maxDepth;
    }

    public static void main(String[] args) {
        MaxDepth maxDepth = new MaxDepth();

        TreeNode treeNode1 = new TreeNode(3);
        TreeNode treeNode2 = new TreeNode(9);
        TreeNode treeNode3 = new TreeNode(20);
        treeNode1.left = treeNode2;
        treeNode1.right = treeNode3;

        TreeNode treeNode4 = new TreeNode(15);
        TreeNode treeNode5 = new TreeNode(17);
        treeNode3.left = treeNode4;
        treeNode3.right = treeNode5;

        System.out.println(maxDepth.maxDepth(treeNode1) == 3);
    }
}