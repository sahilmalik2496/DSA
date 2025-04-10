package binaryTree;

import java.utils.*;

/*
Given the root of a binary tree, return the length of the diameter of the tree.

The diameter of a binary tree is the length of the longest path between any two nodes in a tree. This path may or may not pass through the root.

The length of a path between two nodes is represented by the number of edges between them.

  */


class DiameterBinaryTree {
    public int diameterOfBinaryTree(TreeNode root) {
        int[] max = new int[1]; // mutable container for the max diameter
        maxDepth(root, max);
        return max[0];
    }

    private int maxDepth(TreeNode node, int[] max) {
        if (node == null) return 0;

        int left = maxDepth(node.left, max);
        int right = maxDepth(node.right, max);

        max[0] = Math.max(max[0], left + right);

        return Math.max(left, right) + 1;
    }
}
