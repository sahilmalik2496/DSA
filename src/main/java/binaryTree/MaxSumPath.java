package binaryTree;

import java.util.*;
/*
A path in a binary tree is a sequence of nodes where each pair of adjacent nodes in the sequence has an edge connecting them. A node can only appear in the sequence at most once. Note that the path does not need to pass through the root.

The path sum of a path is the sum of the node's values in the path.

Given the root of a binary tree, return the maximum path sum of any non-empty path.

*/

class MaxSumPath {

    public int maxPathSum(TreeNode root) {
        int[] maxSum = new int[] { Integer.MIN_VALUE };
        maxPathDown(root, maxSum);
        return maxSum[0];
    }

    private int maxPathDown(TreeNode node, int[] maxSum) {
        if (node == null) return 0;

        int left = Math.max(0, maxPathDown(node.left, maxSum));
        int right = Math.max(0, maxPathDown(node.right, maxSum));

        maxSum[0] = Math.max(maxSum[0], left + right + node.val);

        return Math.max(left, right) + node.val;
    }
}
