/**
 * @(#)SearchBSTKthNum.java, 2020/8/25.
 * author wenniuwuren
 */
package com.wenniuwuren.algorithm.lru.tree;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class SearchBSTKthNum {

    public int kthLargest(TreeNode root, int k) {

        List<Integer> list = new ArrayList<>();

        Deque<TreeNode> stack = new ArrayDeque<>();

        stack.push(root);

        while (!stack.isEmpty()) {
            TreeNode node = stack.peek();


            if (node.left != null) {
                stack.push(node.left);
                node.left = null; // 左子树入栈后，防止后续重复遍历左子树
                continue;
            }

            TreeNode popNode = stack.pop();
            list.add(popNode.val);

            if (node.right != null) {
                stack.push(node.right);
            }


        }


        int len = list.size();

        return list.get(len - k);
    }

    public static void main(String[] args) {
        SearchBSTKthNum searchBSTKthNum = new SearchBSTKthNum();

//        TreeNode treeNode1 = new TreeNode(3);
//        TreeNode treeNode2 = new TreeNode(1);
//        TreeNode treeNode3 = new TreeNode(4);
//        treeNode1.left = treeNode2;
//        treeNode1.right = treeNode3;
//
//        TreeNode treeNode4 = new TreeNode(2);
//        treeNode2.right = treeNode4;
//
//        System.out.println(searchBSTKthNum.kthLargest(treeNode1, 1) == 4);

        TreeNode treeNode1 = new TreeNode(5);
        TreeNode treeNode2 = new TreeNode(3);
        TreeNode treeNode3 = new TreeNode(6);
        treeNode1.left = treeNode2;
        treeNode1.right = treeNode3;

        TreeNode treeNode4 = new TreeNode(2);
        TreeNode treeNode5 = new TreeNode(4);
        treeNode2.left = treeNode4;
        treeNode2.right = treeNode5;

        TreeNode treeNode6 = new TreeNode(1);
        treeNode4.left = treeNode6;
        System.out.println(searchBSTKthNum.kthLargest(treeNode1, 3) == 4);

    }
}