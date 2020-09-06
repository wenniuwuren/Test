/**
 * @(#)SubStructure.java, 2020/9/3.
 * author wenniuwuren
 */
package com.wenniuwuren.algorithm.lru.tree;

import java.util.ArrayDeque;
import java.util.Deque;

public class SubStructure {

    public boolean isSubStructure(TreeNode A, TreeNode B) {
        if (B == null || A == null) {
            return false;
        }
        boolean result = false;
        // 采用bfs
        Deque<TreeNode> queue = new ArrayDeque<>();
        queue.add(A);

        while (!queue.isEmpty()) {
            TreeNode node = queue.remove();
            result = subStructure(node, B);
            if (result) {
                return result;
            }

            if (node.left != null) {
                queue.add(node.left);
            }

            if (node.right != null) {
                queue.add(node.right);
            }
        }

        return result;
    }


    private boolean subStructure(TreeNode A, TreeNode B) {
        boolean r1 = true;
        boolean r2 = true;

        if (A == null) {
            return false;
        }

        if (A.val == B.val) {
            if (B.left != null) {
                r1 = subStructure(A.left, B.left);
            }

            if (B.right != null) {
                r2 = subStructure(A.right, B.right);
            }

            if (B.left == null && B.right == null) {
                return true;
            }
        } else {
            return false;
        }

        return r1 && r2;
    }

    public static void main(String[] args) {
        TreeNode treeNode1 = new TreeNode(3);
        TreeNode treeNode2 = new TreeNode(4);
        TreeNode treeNode3 = new TreeNode(5);
        TreeNode treeNode4 = new TreeNode(1);
        TreeNode treeNode5 = new TreeNode(2);

        treeNode1.left = treeNode2;
        treeNode1.right = treeNode3;
        treeNode2.left = treeNode4;
        treeNode2.right = treeNode5;

        TreeNode treeNode6 = new TreeNode(4);
        TreeNode treeNode7 = new TreeNode(1);
        treeNode6.left = treeNode7;

        SubStructure structure = new SubStructure();
//        System.out.println(structure.isSubStructure(treeNode1, treeNode6));


        TreeNode treeNode8 = new TreeNode(1);
        TreeNode treeNode9 = new TreeNode(2);
        TreeNode treeNode10 = new TreeNode(3);
        TreeNode treeNode13 = new TreeNode(1);
        treeNode8.left = treeNode9;
        treeNode8.right = treeNode10;
        treeNode10.left = treeNode13;

        TreeNode treeNode11 = new TreeNode(3);
        TreeNode treeNode12 = new TreeNode(1);
        treeNode11.left = treeNode12;

        System.out.println(structure.isSubStructure(treeNode8, treeNode11));
    }
}