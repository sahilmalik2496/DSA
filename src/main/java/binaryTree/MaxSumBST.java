package binaryTree;

/*
Given a binary tree root, return the maximum sum of all keys of any sub-tree which is also a Binary Search Tree (BST).

Assume a BST is defined as follows:

The left subtree of a node contains only nodes with keys less than the node's key.
The right subtree of a node contains only nodes with keys greater than the node's key.
Both the left and right subtrees must also be binary search trees.
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
