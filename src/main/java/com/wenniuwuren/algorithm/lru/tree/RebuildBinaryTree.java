/**
 * @(#)RebuildBinaryTree.java, 2020/8/18.
 * author wenniuwuren
 */
package com.wenniuwuren.algorithm.lru.tree;

import java.util.HashMap;
import java.util.Map;

/**
 * 输入某二叉树的前序遍历和中序遍历的结果，请重建该二叉树。假设输入的前序遍历和中序遍历的结果中都不含重复的数字。
 *
 * 例如，给出
 *
 * 前序遍历 preorder = [3,9,20,15,7]
 * 中序遍历 inorder = [9,3,15,20,7]
 * 返回如下的二叉树：
 *
 *     3
 *    / \
 *   9  20
 *     /  \
 *    15   7
 *  
 *
 * 限制：
 *
 * 0 <= 节点个数 <= 5000
 *
 *
 */
public class RebuildBinaryTree {

    public TreeNode buildTree(int[] preorder, int[] inorder) {

        // 前序遍历第一个节点是root， root又可以在中序遍历中划分出左右子树. 迭代重复这一过程就得到原来的树.

        if (preorder == null || inorder == null) {
            return null;
        }

        int len = inorder.length;

        // 缓存 inorder 的下标
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < len; i++) {
            map.put(inorder[i], i);
        }

        return getOriginalTree(preorder, inorder, 0, len - 1, 0, len - 1, map);
    }

    private TreeNode getOriginalTree(int[] preorder, int[] inorder, int preorderLeft, int preorderRight, int inorderLeft,
        int inorderRight, Map<Integer, Integer> map) {
        if (preorderLeft > preorderRight) {
            return null;
        }

        TreeNode root = new TreeNode(preorder[preorderLeft]);
        int leftSize = map.get(preorder[preorderLeft]) - inorderLeft;

        root.left = getOriginalTree(preorder, inorder, preorderLeft + 1, preorderLeft + leftSize,
            inorderLeft, map.get(preorder[preorderLeft]) - 1, map);

        root.right = getOriginalTree(preorder, inorder, preorderLeft + leftSize + 1, preorderRight,
            map.get(preorder[preorderLeft]) + 1, inorderRight, map);
        return root;

    }
}