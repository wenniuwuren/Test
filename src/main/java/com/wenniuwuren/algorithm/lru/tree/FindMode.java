/**
 * @(#)FindMode.java, 2020/9/24.
 * author wenniuwuren
 */
package com.wenniuwuren.algorithm.lru.tree;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 501. 二叉搜索树中的众数
 *
 * 给定一个有相同值的二叉搜索树（BST），找出 BST 中的所有众数（出现频率最高的元素）。
 *
 * 假定 BST 有如下定义：
 *
 * 结点左子树中所含结点的值小于等于当前结点的值
 * 结点右子树中所含结点的值大于等于当前结点的值
 * 左子树和右子树都是二叉搜索树
 * 例如：
 * 给定 BST [1,null,2,2],
 *
 *    1
 *     \
 *      2
 *     /
 *    2
 * 返回[2].
 *
 * 提示：如果众数超过1个，不需考虑输出顺序
 *
 * 进阶：你可以不使用额外的空间吗？（假设由递归产生的隐式调用栈的开销不被计算在内）
 *
 */
public class FindMode {


    public int[] findMode(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        inorder(root, list);

        Map<Integer, Integer> map = new HashMap<>();
        int max = 0;
        for (int i = 0; i < list.size(); i++) {
            if (map.get(list.get(i)) == null) {
                map.put(list.get(i), 0);
            } else {
                map.put(list.get(i), map.get(list.get(i)) + 1);
            }
            max = Math.max(max, map.get(list.get(i)));
        }

        List<Integer> result = new ArrayList<>();
        for (Integer key : map.keySet()) {
            if (Integer.valueOf(max).equals(map.get(key))) {
                result.add(key);
            }
        }

        int[] r = new int[result.size()];

        for (int i = 0; i < r.length; i++) {
            r[i] = result.get(i);
        }
        return r;
    }


    private void inorder(TreeNode root, List<Integer> list) {
        if (root != null) {
           inorder(root.left, list);

           list.add(root.val);

           inorder(root.right, list);
        }
    }

    public static void main(String[] args) {
        FindMode findMode = new FindMode();
        TreeNode treeNode1 = new TreeNode(1);
        TreeNode treeNode2 = new TreeNode(2);
        TreeNode treeNode3 = new TreeNode(2);
        treeNode1.right = treeNode2;
        treeNode2.left = treeNode3;

        System.out.println(findMode.findMode(treeNode1));
    }
}