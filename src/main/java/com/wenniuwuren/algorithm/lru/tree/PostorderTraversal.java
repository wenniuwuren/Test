/**
 * @(#)PostorderTraversal.java, 2020/9/29.
 * author wenniuwuren
 */
package com.wenniuwuren.algorithm.lru.tree;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/**
 * 145. 二叉树的后序遍历
 * 给定一个二叉树，返回它的 后序 遍历。
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
 * 输出: [3,2,1]
 * 进阶: 递归算法很简单，你可以通过迭代算法完成吗？
 *
 */
public class PostorderTraversal {

//    List<Integer> result = new ArrayList<>();
//    public List<Integer> postorderTraversal(TreeNode root) {
//
//
//        if (root == null) return result;
//
//
//        if (root.left != null) {
//            postorderTraversal(root.left);
//        }
//
//        if (root.right != null) {
//            postorderTraversal(root.right);
//        }
//
//        result.add(root.val);
//
//        return result;
//    }

    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (root == null) return result;

        Deque<TreeNode> stack = new ArrayDeque<>();
        stack.push(root);

        while (!stack.isEmpty()) {
            TreeNode treeNode = stack.peek();

            if (treeNode.left != null) {
                stack.push(treeNode.left);
            }

            if (treeNode.right != null) {
                stack.push(treeNode.right);
            }

            if (treeNode.left == null && treeNode.right == null) {
                TreeNode temp = stack.pop();
                result.add(temp.val);
                temp = null;
            }
        }

        return result;
    }

    public static void main(String[] args) {
        PostorderTraversal postorderTraversal = new PostorderTraversal();
        TreeNode treeNode1 = new TreeNode(1);
        TreeNode treeNode2 = new TreeNode(2);
        TreeNode treeNode3 = new TreeNode(3);
        treeNode1.right = treeNode2;
        treeNode2.left = treeNode3;

        System.out.println(postorderTraversal.postorderTraversal(treeNode1));
    }
}