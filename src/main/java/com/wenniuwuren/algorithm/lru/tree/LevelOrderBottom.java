/**
 * @(#)LevelOrderBottom.java, 2020/9/6.
 * author wenniuwuren
 */
package com.wenniuwuren.algorithm.lru.tree;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Deque;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class LevelOrderBottom {

    public List<List<Integer>> levelOrderBottom(TreeNode root) {

        List<List<Integer>> result = new ArrayList<>();
        Map<Integer, List<Integer>> map = new HashMap<>();

        if (root == null) {
            return result;
        }

        Deque<TreeNode> queue = new ArrayDeque<>();
        Deque<Integer> depth = new ArrayDeque<>();
        queue.add(root);
        depth.add(0);
        int maxDepth = 0;

        while (!queue.isEmpty()) {
            TreeNode treeNode = queue.remove();
            Integer currDepth = depth.remove();
            maxDepth = Math.max(currDepth, maxDepth);

            List<Integer> list = map.get(currDepth);
            if (list == null) {
                list = new ArrayList<>();
            }
            list.add(treeNode.val);
            map.put(currDepth, list);

            if (treeNode.left != null) {
                queue.add(treeNode.left);
                depth.add(currDepth + 1);
            }

            if (treeNode.right != null) {
                queue.add(treeNode.right);
                depth.add(currDepth + 1);
            }

        }

        for (int i = maxDepth; i >= 0; i--) {
            result.add(map.get(i));
        }

        return result;
    }
}