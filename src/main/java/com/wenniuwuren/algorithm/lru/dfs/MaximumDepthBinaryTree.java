/**
 * @(#)MaximumDepthBinaryTree.java, 2020/7/29.
 * author wenniuwuren
 */
package com.wenniuwuren.algorithm.lru.dfs;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Stack;

public class MaximumDepthBinaryTree {

    public int maxDepth(TreeNode root) {

        if (root == null) {
            return 0;
        }

        Stack<TreeNode> stack = new Stack<>();
        Stack<Integer> depthStack = new Stack<>();

        int currDepth = 1;
        stack.push(root);
        depthStack.push(currDepth);

        int maxDepth = 0;
        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();
            currDepth = depthStack.pop();
            maxDepth = Math.max(currDepth, maxDepth);

            if (node.right != null) {
                stack.push(node.right);
                depthStack.push(currDepth + 1);
            }

            if (node.left != null) {
                stack.push(node.left);
                depthStack.push(currDepth + 1);
            }

        }

        return maxDepth;
    }

    public static void main(String[] args) {
        TreeNode treeNode1 = new TreeNode(3);
        TreeNode treeNode2 = new TreeNode(9);
        TreeNode treeNode3 = new TreeNode(20);
        treeNode1.left = treeNode2;
        treeNode1.right = treeNode3;

        TreeNode treeNode4 = new TreeNode(15);
        TreeNode treeNode5 = new TreeNode(17);
        treeNode3.left = treeNode4;
        treeNode3.right = treeNode5;

        MaximumDepthBinaryTree maximumDepthBinaryTree = new MaximumDepthBinaryTree();
        System.out.println(maximumDepthBinaryTree.maxDepth(treeNode1) == 3);

    }
}