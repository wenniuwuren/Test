/**
 * @(#)InorderTraversal.java, 2020/9/14.
 * author wenniuwuren
 */
package com.wenniuwuren.algorithm.lru.tree;

import java.util.ArrayList;
import java.util.List;

/**
 * 给定一个二叉树，返回它的中序 遍历。
 *
 * 示例:
 *
 * 输入: [1,null,2,3]
 *    1
 *     \
 *      2
 *     /
 *    3
 *
 * 输出: [1,3,2]
 * 进阶: 递归算法很简单，你可以通过迭代算法完成吗？
 *
 */
public class InorderTraversal {

    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> list = new ArrayList<>();

        if (root == null) return list;

        inorder(root, list);

        return list;
    }

    private void inorder(TreeNode root, List<Integer> list) {
        if (root == null) return;

        if (root.left != null) {
            inorder(root.left, list);
        }

        list.add(root.val);

        if (root.right != null) {
            inorder(root.right, list);
        }
    }
}