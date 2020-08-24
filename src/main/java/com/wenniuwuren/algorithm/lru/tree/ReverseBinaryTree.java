/**
 * @(#)ReverseBinaryTree.java, 2020/8/21.
 * author wenniuwuren
 */
package com.wenniuwuren.algorithm.lru.tree;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;

public class ReverseBinaryTree {

    public TreeNode mirrorTree(TreeNode root) {
        if(root == null) {
            return null;
        }
        Deque<TreeNode> queue = new ArrayDeque<>();
        queue.add(root);

        while(!queue.isEmpty()) {
            TreeNode node = queue.remove();

            TreeNode tmp = node.left;
            node.left = node.right;
            node.right = tmp;

            if(node.left != null) {
                queue.add(node.left);
            }
            if(node.right != null) {
                queue.add(node.right);
            }

        }
        return root;
    }
}