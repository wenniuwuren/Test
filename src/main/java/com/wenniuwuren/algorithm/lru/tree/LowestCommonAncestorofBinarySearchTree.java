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

        TreeNode result = null;

        if (root == null || p == null || q == null) {
            return null;
        }

        int rootValue = root.val;
        int pValue = p.val;
        int qValue = q.val;

        if (rootValue >= pValue && rootValue <= qValue) {
            result = root;
        }

        if (rootValue >= qValue && rootValue <= pValue) {
            result = root;
        }

        if (rootValue > pValue && rootValue > qValue) {
            result = lowestCommonAncestor(root.left, p, q);
        }

        if (rootValue < pValue && rootValue < qValue) {
            result = lowestCommonAncestor(root.right, p, q);
        }

        return result;
    }
}