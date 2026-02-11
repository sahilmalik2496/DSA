package binaryTree;

/*

https://leetcode.com/problems/maximum-sum-bst-in-binary-tree/

Given a binary tree root, return the maximum sum of all keys of any sub-tree which is also a Binary Search Tree (BST).

Assume a BST is defined as follows:

The left subtree of a node contains only nodes with keys less than the node's key.
The right subtree of a node contains only nodes with keys greater than the node's key.
Both the left and right subtrees must also be binary search trees.

This code solves the Maximum Sum BST in Binary Tree problem. Since a tree can have millions of nodes,
checking every subtree individually for BST properties would be too slow ($O(n^2)$). This algorithm uses
 Post-order Traversal (Left -> Right -> Root) to solve it in $O(n)$ time by passing information up from the
 bottom.The StrategyEach node receives a "report card" (NodeValue) from its left and right children. To be a
  valid BST, the current node's value must be greater than the largest value in the left subtree and smaller
   than the smallest value in the right subtree.Key Components1. The NodeValue ObjectThis acts as a data
   packet containing:maxValue: The largest value found in this subtree.minValue: The smallest value found
   in this subtree.maxsum: The total sum of all nodes in this subtree (if it's a BST).2. The Base Case
   (The "Ghost" Nodes)Javaif(root == null){

    return new NodeValue(Integer.MIN_VALUE, Integer.MAX_VALUE, 0);
}
Why MIN_VALUE for max? So that any leaf node's value will be greater than its "left child," satisfying
the left.maxValue < root.val condition.Why MAX_VALUE for min? So that any leaf node's value will be smaller
 than its "right child," satisfying the root.val < right.minValue condition.The BST Logic (The "Report Card"
  Check)The code checks: if(left.maxValue < root.val && root.val < right.minValue)Case A: It IS a BSTCalculate
  Sum: Adds current node value to the left and right sums.Update Global Max: If this new sum is the biggest we've seen,
   update the max variable.Pass Upwards: Returns a new NodeValue containing the actual min/max of this combined subtree.
   Case B: It is NOT a BSTJavareturn new NodeValue(Integer.MAX_VALUE, Integer.MIN_VALUE, Math.max(left.maxsum, right.maxsum));
It intentionally sends back "garbage" boundaries: a maxValue of Infinity and a minValue of Negative Infinity.
Why? This ensures that any parent node above this one will fail the BST check, because no number can be
 greater than Infinity or less than Negative Infinity.Dry Run ExampleImagine a small tree: 2 (Root), 1 (Left),
  3 (Right).Left Child (1): Receives (MIN, MAX, 0) from its null children. It is a BST. Returns (1, 1, 1).
  Right Child (3): Receives (MIN, MAX, 0). It is a BST. Returns (3, 3, 3).Root (2):left.maxValue (1) < 2 is
  true.2 < right.minValue (3) is true.Result: It is a BST! Sum = $1 + 2 + 3 = 6$. Update max = 6.

 */


public class MaxSumBST {
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

    class NodeValue{
        // maxValue maxValue,minValue for checking Validation of BST and maxsum of that valid BST.
        public int maxValue,minValue,maxsum;
        NodeValue(int max, int min,int maxsum){
            this.maxValue = max;
            this.minValue = min;
            this.maxsum = maxsum;
        }
    }

    class Solution {
        int max = 0;
        public NodeValue maxSumBSTHelper(TreeNode root){
            if(root == null){
                return new NodeValue(Integer.MIN_VALUE,Integer.MAX_VALUE,0);
            }
            NodeValue left = maxSumBSTHelper(root.left);
            NodeValue right = maxSumBSTHelper(root.right);

            if(left.maxValue < root.val && root.val < right.minValue){
                max = Math.max(max,root.val + left.maxsum + right.maxsum);
                return new NodeValue(Math.max(root.val,right.maxValue), Math.min(root.val,left.minValue),root.val+left.maxsum + right.maxsum);

            }
            return new NodeValue(Integer.MAX_VALUE,Integer.MIN_VALUE,Math.max(left.maxsum,right.maxsum));
        }
        public int maxSumBST(TreeNode root) {
            maxSumBSTHelper(root);
            return max;
        }
    }
}
