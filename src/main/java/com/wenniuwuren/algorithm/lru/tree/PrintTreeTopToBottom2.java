/**
 * @(#)PrintTreeTopToBottom2.java, 2020/8/25.
 * author wenniuwuren
 */
package com.wenniuwuren.algorithm.lru.tree;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PrintTreeTopToBottom2 {

    public int[] levelOrder(TreeNode root) {
        List<Integer> tempResult = new ArrayList<>();


        if (root == null) {
            return new int[0];
        }

        Map<Integer, List<Integer>> map = new HashMap<>();

        Deque<TreeNode> nodeQueue = new ArrayDeque<>();


        nodeQueue.add(root);

        while (!nodeQueue.isEmpty()) {
            TreeNode node = nodeQueue.remove();

            tempResult.add(node.val);


            if (node.left != null) {
                nodeQueue.add(node.left);
            }

            if (node.right != null) {
                nodeQueue.add(node.right);

            }

        }

        int[] result = new int[tempResult.size()];
        for (int i = 0; i < result.length; i++) {
            result[i] = tempResult.get(i);
        }

        return result;
    }

    public static void main(String[] args) {
        PrintTreeTopToBottom2 printTreeTopToBottom2 = new PrintTreeTopToBottom2();

        TreeNode treeNode1 = new TreeNode(3);
        TreeNode treeNode2 = new TreeNode(9);
        TreeNode treeNode3 = new TreeNode(20);
        treeNode1.left = treeNode2;
        treeNode1.right = treeNode3;

        TreeNode treeNode4 = new TreeNode(15);
        TreeNode treeNode5 = new TreeNode(7);
        treeNode3.left = treeNode4;
        treeNode3.right = treeNode5;

        System.out.println(printTreeTopToBottom2.levelOrder(treeNode1));
    }
}