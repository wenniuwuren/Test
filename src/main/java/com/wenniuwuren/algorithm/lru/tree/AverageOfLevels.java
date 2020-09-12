/**
 * @(#)AverageOfLevels.java, 2020/9/12.
 * author wenniuwuren
 */
package com.wenniuwuren.algorithm.lru.tree;

import java.text.DecimalFormat;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AverageOfLevels {

    public List<Double> averageOfLevels(TreeNode root) {

        // 二叉树广度遍历

        // 深度，相应深度的节点
        Map<Integer, List<Integer>> map = new HashMap<>();

        Deque<TreeNode> queue = new ArrayDeque<>();
        Deque<Integer> depthQueue = new ArrayDeque<>();
        queue.add(root);
        depthQueue.add(0);

        int maxDepth = 0;

        while (!queue.isEmpty()) {
            TreeNode treeNode = queue.poll();
            Integer depth = depthQueue.poll();

            maxDepth = Math.max(depth, maxDepth);

            List<Integer> list = map.get(depth);
            if (list == null) {
                list = new ArrayList<>();
            }

            list.add(treeNode.val);
            map.put(depth, list);

            if (treeNode.left != null) {
                queue.add(treeNode.left);
                depthQueue.add(depth + 1);
            }

            if (treeNode.right != null) {
                queue.add(treeNode.right);
                depthQueue.add(depth + 1);
            }


        }

        List<Double> result = new ArrayList<>();
        DecimalFormat df =new DecimalFormat("0.0");
        for (int i = 0; i <= maxDepth; i++) {
            double sum = 0;
            int count = 0;

            List<Integer> list = map.get(i);

            for (int j = 0; j < list.size(); j++) {
                sum += list.get(j);
                count++;
            }

            result.add(sum / count);
        }

        return result;
    }

    public static void main(String[] args) {
        AverageOfLevels averageOfLevels = new AverageOfLevels();
        TreeNode treeNode1 = new TreeNode(3);
        TreeNode treeNode2 = new TreeNode(9);
        TreeNode treeNode3 = new TreeNode(20);
        treeNode1.left = treeNode2;
        treeNode1.right = treeNode3;

        TreeNode treeNode4 = new TreeNode(15);
        TreeNode treeNode5 = new TreeNode(7);
        treeNode3.left = treeNode4;
        treeNode3.right = treeNode5;

        System.out.println(averageOfLevels.averageOfLevels(treeNode1));
    }
}