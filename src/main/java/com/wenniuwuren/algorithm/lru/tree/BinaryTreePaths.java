/**
 * @(#)BinaryTreePaths.java, 2020/9/4.
 * author wenniuwuren
 */
package com.wenniuwuren.algorithm.lru.tree;

import java.util.ArrayList;
import java.util.List;

public class BinaryTreePaths {

    public List<String> binaryTreePaths(TreeNode root) {

        // 可以用广度优先遍历， 一个queue存节点，一个queue存路径

        // 这里使用递归实现
        List<String> result = new ArrayList<>();
        if (root == null) {
            return result;
        }

        buidPath(result, root, "");
        return result;
    }

    private void buidPath(List<String> result, TreeNode root, String path) {

        if (root != null) {
            StringBuilder sb = new StringBuilder(path);
            sb.append(root.val);

            if (root.left == null && root.right == null) {
                result.add(sb.toString());
            } else {
                sb.append("->");
                buidPath(result, root.left, sb.toString());// 注意要新对象
                buidPath(result, root.right, sb.toString());

            }

        }


    }

    public static void main(String[] args) {
        BinaryTreePaths binaryTreePaths = new BinaryTreePaths();
        TreeNode treeNode1 = new TreeNode(1);
        TreeNode treeNode2 = new TreeNode(2);
        TreeNode treeNode3 = new TreeNode(3);
        TreeNode treeNode4 = new TreeNode(5);

        treeNode1.left = treeNode2;
        treeNode1.right = treeNode3;
        treeNode2.right = treeNode4;

        binaryTreePaths.binaryTreePaths(treeNode1);
    }


}