/**
 * @(#)SymmetricBinaryTree.java, 2020/5/24.
 * author wenniuwuren
 */
package com.wenniuwuren.algorithm.lru.bfs;

/**
 *
 * 给定一个二叉树，检查它是否是镜像对称的。
 * 例如，二叉树 [1,2,2,3,4,4,3] 是对称的。
 *
 *     1
 *    / \
 *   2   2
 *  / \ / \
 * 3  4 4  3
 *
 * 但是下面这个 [1,2,2,null,3,null,3] 则不是镜像对称的:
 *
 *     1
 *    / \
 *   2   2
 *    \   \
 *    3    3
 *
 */
public class SymmetricBinaryTree {

    public boolean isSymmetric(TreeNode root) {

        if (root == null) {
            return true;
        }

        return symmetric(root.left, root.right);

    }

    boolean symmetric(TreeNode left, TreeNode right) {
        boolean result1 = false;
        boolean result2 = false;


        if (left == null && right == null) {
            return true;
        }

        if (left != null && right == null) {
            return false;
        }

        if (left == null && right != null) {
            return false;
        }

        if (left != null && right != null) {
            if (left.val == right.val) {
                result1 = symmetric(left.left, right.right);

                result2 = symmetric(left.right, right.left);
            } else {
                return false;
            }


        }

        return result1 & result2;
    }

    public static void main(String[] args) {
        SymmetricBinaryTree symmetricBinaryTree = new SymmetricBinaryTree();

        TreeNode treeNode1 = new TreeNode(1);
        TreeNode treeNode2 = new TreeNode(2);
        TreeNode treeNode3 = new TreeNode(2);

        treeNode1.left = treeNode2;
        treeNode1.right = treeNode3;

        TreeNode treeNode4 = new TreeNode(3);
        TreeNode treeNode5 = new TreeNode(4);
        treeNode2.left = treeNode4;
        treeNode2.right = treeNode5;

        TreeNode treeNode6 = new TreeNode(4);
        TreeNode treeNode7 = new TreeNode(3);
        treeNode3.left = treeNode6;
        treeNode3.right = treeNode7;

        System.out.println(symmetricBinaryTree.isSymmetric(treeNode1));


        System.out.println(symmetricBinaryTree.isSymmetric(null));

        System.out.println(symmetricBinaryTree.isSymmetric(new TreeNode(0)));

        TreeNode treeNode8 = new TreeNode(1);
        TreeNode treeNode9 = new TreeNode(2);
        treeNode8.left = treeNode9;
        System.out.println(symmetricBinaryTree.isSymmetric(treeNode8));
    }
}