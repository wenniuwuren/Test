/**
 * @(#)BinarySearchTree.java, 2020/5/17.
 * author wenniuwuren
 */
package com.wenniuwuren.algorithm.lru.tree;

/**
 * Given n, how many structurally unique BST's (binary search trees) that store values 1 ... n?
 *
 * 输入n，得到 1..n 组成的不同二叉搜索树数量
 */
public class BinarySearchTree {


    public int numTrees(int n) {

        int[] nums = new int[n+1];
        nums[0] = 1;
        nums[1] = 1;

        for (int i = 2; i <= n; i++) {
            for (int j = 1; j <= i; j++) {
                nums[i] += nums[j - 1] * nums[i - j];
            }

        }

        return nums[n];
    }

    public static void main(String[] args) {
        BinarySearchTree binarySearchTree = new BinarySearchTree();
        System.out.println(binarySearchTree.numTrees(3));
    }
}