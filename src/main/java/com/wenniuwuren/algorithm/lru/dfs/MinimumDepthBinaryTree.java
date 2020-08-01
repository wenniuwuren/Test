/**
 * @(#)MinimumDepthBinaryTree.java, 2020/7/29.
 * author wenniuwuren
 */
package com.wenniuwuren.algorithm.lru.dfs;

import java.util.Stack;

public class MinimumDepthBinaryTree {

    public int minDepth(TreeNode root) {

        if (root == null) {
            return 0;
        }

        Stack<TreeNode> stack = new Stack<>();
        Stack<Integer> depthStack = new Stack<>();

        int currDepth = 1;
        stack.push(root);
        depthStack.push(currDepth);

        int minDepth = Integer.MAX_VALUE;
        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();
            currDepth = depthStack.pop();

            if (node.left == null && node.right == null) {
                minDepth = Math.min(currDepth, minDepth);
            }


            if (node.right != null) {
                stack.push(node.right);
                depthStack.push(currDepth + 1);
            }

            if (node.left != null) {
                stack.push(node.left);
                depthStack.push(currDepth + 1);
            }

        }

        return minDepth;

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

        MinimumDepthBinaryTree minimumDepthBinaryTree = new MinimumDepthBinaryTree();
        System.out.println(minimumDepthBinaryTree.minDepth(treeNode1) == 2);

    }
}