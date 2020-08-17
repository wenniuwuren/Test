/**
 * @(#)BalanceTree.java, 2020/8/16.
 * author wenniuwuren
 */
package com.wenniuwuren.algorithm.lru.tree;


/**
 * 输入一棵二叉树的根节点，判断该树是不是平衡二叉树。如果某二叉树中任意节点的左右子树的深度相差不超过1，那么它就是一棵平衡二叉树。
 *
 * 限制：
 * 1 <= 树的结点个数 <= 10000
 */
public class BalanceTree {

    public boolean isBalanced(TreeNode root) {
        if (root == null) {
            return true;
        }

        // 左子树最大深度和右子树最大深度差 <=1 。  左子树、右子树也要平衡
        return (Math.abs(maxDepth(root.left) - maxDepth(root.right)) <= 1) && isBalanced(root.left)
            && isBalanced(root.right);

    }

    private int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }

        return Math.max(maxDepth(root.left), maxDepth(root.right)) + 1;
    }

    public static void main(String[] args) {
        BalanceTree balanceTree = new BalanceTree();

        TreeNode treeNode1 = new TreeNode(3);
        TreeNode treeNode2 = new TreeNode(9);
        TreeNode treeNode3 = new TreeNode(20);
        treeNode1.left = treeNode2;
        treeNode1.right = treeNode3;

        TreeNode treeNode4 = new TreeNode(15);
        TreeNode treeNode5 = new TreeNode(17);
        treeNode3.left = treeNode4;
        treeNode3.right = treeNode5;

        TreeNode treeNode6 = new TreeNode(1);
        TreeNode treeNode7 = new TreeNode(2);
        TreeNode treeNode8 = new TreeNode(3);
        treeNode6.left = treeNode7;
        treeNode7.left = treeNode8;

        System.out.println(balanceTree.isBalanced(treeNode1) == true);

        System.out.println(balanceTree.isBalanced(null) == true);

        System.out.println(balanceTree.isBalanced(treeNode6) == false);
    }
}