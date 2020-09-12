/**
 * @(#)LowestCommonAncestorofBinarySearchTree.java, 2020/8/2.
 * author wenniuwuren
 */
package com.wenniuwuren.algorithm.lru.tree;

/**
 * 给定一个二叉搜索树, 找到该树中两个指定节点的最近公共祖先。
 * 从根节点开始遍历树
 * 如果节点 pp 和节点 qq 都在右子树上，那么以右孩子为根节点继续 1 的操作
 * 如果节点 pp 和节点 qq 都在左子树上，那么以左孩子为根节点继续 1 的操作
 * 如果条件 2 和条件 3 都不成立，这就意味着我们已经找到节 pp 和节点 qq 的 LCA 了
 *
 */
public class LowestCommonAncestorofBinarySearchTree {

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {

        if (root == null) return root;

//        if (root.val >= p.val && root.val <= q.val) return root;
//        if (root.val <= p.val && root.val >= q.val) return root;

        if (root.val > p.val && root.val > q.val) { //都在左子树
            return lowestCommonAncestor(root.left, p, q);
        }

        if (root.val < p.val && root.val < q.val) {
            return lowestCommonAncestor(root.right, p, q);
        }

        return root;
    }

    public static void main(String[] args) {
        TreeNode treeNode1 = new TreeNode(6);
        TreeNode treeNode2 = new TreeNode(2);
        TreeNode treeNode3 = new TreeNode(8);

        treeNode1.left = treeNode2;
        treeNode1.right = treeNode3;

        TreeNode treeNode4 = new TreeNode(0);
        TreeNode treeNode5 = new TreeNode(4);
        TreeNode treeNode6 = new TreeNode(7);
        TreeNode treeNode7 = new TreeNode(9);

        treeNode2.left = treeNode4;
        treeNode2.right = treeNode5;
        treeNode3.left = treeNode6;
        treeNode3.right = treeNode7;

        TreeNode treeNode8 = new TreeNode(3);
        TreeNode treeNode9 = new TreeNode(5);
        treeNode5.left = treeNode8;
        treeNode5.right = treeNode9;

        LowestCommonAncestorofBinarySearchTree lowestCommonAncestorofBinarySearchTree = new LowestCommonAncestorofBinarySearchTree();
//        System.out.println(lowestCommonAncestorofBinarySearchTree.lowestCommonAncestor(treeNode1, new TreeNode(2), new TreeNode(8)));
        System.out.println(lowestCommonAncestorofBinarySearchTree.lowestCommonAncestor(treeNode1, new TreeNode(2), new TreeNode(4)));
    }
}