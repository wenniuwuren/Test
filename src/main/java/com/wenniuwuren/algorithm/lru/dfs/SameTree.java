/**
 * @(#)SameTree.java, 2020/5/24.
 * author wenniuwuren
 */
package com.wenniuwuren.algorithm.lru.dfs;

public class SameTree {


    public boolean isSameTree(TreeNode p, TreeNode q) {

        boolean result1 = false;
        boolean result2 = false;

        if (p == null && q == null) {
            return true;
        } else if (p == null && q != null) {
            return false;
        } else if (p != null && q == null) {
            return false;
        } else {
            if (p.val == q.val) {
                if (p.left != null && q.left != null) {
                    if (p.left.val == q.left.val) {
                        result1 = isSameTree(p.left, q.left);
                    } else {
                        result1 = false;
                    }
                } else if (p.left == null && q.left == null) {
                    result1 = true;
                } else {
                    result1 = false;
                }


                if (p.right != null && q.right != null) {
                    if (p.right.val == q.right.val) {
                        result2 = isSameTree(p.right, q.right);
                    } else {
                        result2 = false;
                    }
                } else if (p.right == null && q.right == null) {
                    result2 = true;
                } else {
                    result2 = false;
                }
            } else {
                return false;
            }


        }




        return result1 && result2;

    }

    public static void main(String[] args) {
        SameTree sameTree = new SameTree();

        TreeNode treeNode1 = new TreeNode(1);
        treeNode1.left = new TreeNode(2);
        treeNode1.right = new TreeNode(3);

        TreeNode treeNode2 = new TreeNode(1);
        treeNode2.left = new TreeNode(2);
        treeNode2.right = new TreeNode(3);

        System.out.println(sameTree.isSameTree(treeNode1, treeNode2));


        TreeNode treeNode3 = new TreeNode(0);
        System.out.println(sameTree.isSameTree(null, treeNode3));

        TreeNode treeNode4 = new TreeNode(0);
        TreeNode treeNode5 = new TreeNode(1);
        System.out.println(sameTree.isSameTree(treeNode4, treeNode5));

        TreeNode treeNode6 = new TreeNode(0);
        treeNode6.left = new TreeNode(-5);

        TreeNode treeNode7 = new TreeNode(0);
        treeNode7.left = new TreeNode(-8);

        System.out.println(sameTree.isSameTree(treeNode6, treeNode7));


    }

/*    public boolean isSameTree(TreeNode p, TreeNode q) {
        // p and q are both null
        if (p == null && q == null) return true;
        // one of p and q is null
        if (q == null || p == null) return false;
        if (p.val != q.val) return false;
        return isSameTree(p.right, q.right) &&
            isSameTree(p.left, q.left);
    }*/


}