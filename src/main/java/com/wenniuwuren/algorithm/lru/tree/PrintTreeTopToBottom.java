/**
 * @(#)PrintTreeTopToBottom.java, 2020/8/25.
 * author wenniuwuren
 */
package com.wenniuwuren.algorithm.lru.tree;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PrintTreeTopToBottom {

    public List<List<Integer>> levelOrder(TreeNode root) {

        List<List<Integer>> result = new ArrayList<>();

        if (root == null) {
            return result;
        }

        Map<Integer, List<Integer>> map = new HashMap<>();

        Deque<TreeNode> nodeQueue = new ArrayDeque<>();
        Deque<Integer> depthQueue = new ArrayDeque<>();

        nodeQueue.add(root);
        depthQueue.add(1);

        int maxDepth = 1;

        while (!nodeQueue.isEmpty()) {
            TreeNode node = nodeQueue.remove();
            Integer depth = depthQueue.remove();

            List<Integer> list = map.get(depth);
            if (list == null) {
                list = new ArrayList<>();
            }
            list.add(node.val);
            map.put(depth, list);

            if (node.left != null) {
                nodeQueue.add(node.left);
                depthQueue.add(depth + 1);
                maxDepth = Math.max(depth, depth + 1);
            }

            if (node.right != null) {
                nodeQueue.add(node.right);
                depthQueue.add(depth + 1);
                maxDepth = Math.max(depth, depth + 1);
            }


        }

        for (int i = 1; i <= maxDepth; i++) {
            if (i % 2 != 0) {
                result.add(map.get(i));

            } else {
                List<Integer> list = map.get(i);
                List<Integer> finalList = new ArrayList<>();
                for (int j = list.size() - 1; j >= 0; j--) {
                    finalList.add(list.get(j));
                }

                result.add(finalList);
            }

        }

        return result;
    }

    public static void main(String[] args) {
        PrintTreeTopToBottom printTreeTopToBottom = new PrintTreeTopToBottom();

        TreeNode treeNode1 = new TreeNode(3);
        TreeNode treeNode2 = new TreeNode(9);
        TreeNode treeNode3 = new TreeNode(20);
        treeNode1.left = treeNode2;
        treeNode1.right = treeNode3;

        TreeNode treeNode4 = new TreeNode(15);
        TreeNode treeNode5 = new TreeNode(7);
        treeNode3.left = treeNode4;
        treeNode3.right = treeNode5;

        System.out.println(printTreeTopToBottom.levelOrder(treeNode1));
    }

}