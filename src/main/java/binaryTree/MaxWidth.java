package binaryTree;

/*
Given the root of a binary tree, return the maximum width of the given tree.

The maximum width of a tree is the maximum width among all levels.

The width of one level is defined as the length between the end-nodes (the leftmost and rightmost non-null nodes), where the null nodes between the end-nodes that would be present in a complete binary tree extending down to that level are also counted into the length calculation.

It is guaranteed that the answer will in the range of a 32-bit signed integer.
*/

import java.util.LinkedList;
import java.util.Queue;

class MaxWidth {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode() {}
        TreeNode(int val) { this.val = val; }
        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }
    public int widthOfBinaryTree(TreeNode root) {
        if (root == null) return 0;

        int maxWidth = 0;
        Queue<Pair> queue = new LinkedList<>();
        queue.offer(new Pair(root, 0));

        while (!queue.isEmpty()) {
            int levelSize = queue.size();
            int first = 0, last = 0;

            for (int i = 0; i < levelSize; i++) {
                Pair current = queue.poll();
                int currIndex = current.index;
                TreeNode node = current.node;

                if (i == 0) first = currIndex;
                if (i == levelSize - 1) last = currIndex;

                if (node.left != null) {
                    queue.offer(new Pair(node.left, currIndex * 2 + 1));
                }
                if (node.right != null) {
                    queue.offer(new Pair(node.right, currIndex * 2 + 2));
                }
            }

            maxWidth = Math.max(maxWidth, last - first + 1);
        }

        return maxWidth;
    }

    // Helper class to store node and its index
    private static class Pair {
        TreeNode node;
        int index;

        Pair(TreeNode node, int index) {
            this.node = node;
            this.index = index;
        }
    }
}
