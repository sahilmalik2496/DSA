package binaryTree;

/*

https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-tree/

Given a binary tree, find the lowest common ancestor (LCA) of two given nodes in the tree.

According to the definition of LCA on Wikipedia: “The lowest common ancestor is defined between two nodes p
 and q as the lowest node in
T that has both p and q as descendants (where we allow a node to be a descendant of itself).”

Time complexity: O(n) — visits each node at most once in the worst case.
Space complexity: O(h) for recursion stack, where h is tree height (O(n) worst-case skewed, O(log n) for a
balanced tree).
*/

class LCABinaryTree {

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
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) {
            return null;
        }
        if (root == p){
            return p;
        }
        if (root == q){
            return q;
        }

        TreeNode left = lowestCommonAncestor(root.left, p,  q); 
        TreeNode right = lowestCommonAncestor(root.right, p,  q); 
        if (left != null && right != null){
            return root;
        }
        if (left == null){
            return right;
        }
        return left;
    }
}
