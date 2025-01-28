package dp;

import javax.swing.tree.TreeNode;

/*
The thief has found himself a new place for his thievery again. There is only one entrance to this area, called root.
Besides the root, each house has one and only one parent house. After a tour, the smart thief realized that all houses in this place form a binary tree. It will automatically contact the police if two directly-linked houses were broken into on the same night.
Given the root of the binary tree, return the maximum amount of money the thief can rob without alerting the police.
 
Example 1:
￼
Input: root = [3,2,3,null,3,null,1]
Output: 7
Explanation: Maximum amount of money the thief can rob = 3 + 3 + 1 = 7.


Intuition
The problem can be broken into subproblems using Dynamic Programming (DP) with recursion. The idea is to calculate, for every node, the maximum sum we can get:
1. When the node is robbed: The sum includes the node's value but excludes its children's values.
2. When the node is not robbed: The sum is the maximum possible sum from its children (regardless of whether they are robbed or not).
The recursion traverses the tree in a post-order fashion (process left child, right child, then the current node), allowing us to compute the result bottom-up.


Complexity Analysis
1. Time Complexity:  O(n)
    * Each node is visited once during the recursive traversal.
2. Space Complexity:  O(h)
    * The recursion stack depth is proportional to the height of the tree
3. When the node is robbed: The sum includes the node's value but excludes its children's values.
4. Space Complexity:
 */
public class HouseRobber3 {
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
    public int[] recur(TreeNode root) {
        if (root == null) {
            return new int[2]; // No values to rob, both cases are 0
        }

        // Recursively calculate for left and right subtrees
        int[] left = recur(root.left);
        int[] right = recur(root.right);
        int[] res = new int[2];

        // Case 1: Current node is not robbed
        res[0] = Math.max(left[0], left[1]) + Math.max(right[0], right[1]);

        // Case 2: Current node is robbed
        res[1] = root.val + left[0] + right[0];

        return res; // Return the results for this node
    }

    public int rob(TreeNode root) {
        int[] res = recur(root);
        return Math.max(res[0], res[1]); // Take the best option at the root
    }
}
