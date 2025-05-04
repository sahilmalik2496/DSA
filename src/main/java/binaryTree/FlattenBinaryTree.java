package binaryTree;



/*
Given the root of a binary tree, flatten the tree into a "linked list":

The "linked list" should use the same TreeNode class where the right child pointer points to the next node in the list and the left child pointer is always null.
The "linked list" should be in the same order as a pre-order traversal of the binary tree.


*/


class FlattenBinaryTree {
  
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

  
    public void flatten(TreeNode root) {
        flattenHelper(root, null);
    }

    private TreeNode flattenHelper(TreeNode root, TreeNode next) {
        if (root == null) {
            return next;
        }

        // First, flatten the right subtree and pass in the next node
        TreeNode rightFlattened = flattenHelper(root.right, next);

        // Then, flatten the left subtree with the head of the right-flattened subtree
        TreeNode leftFlattened = flattenHelper(root.left, rightFlattened);

        // Point current node's right to the flattened left
        root.right = leftFlattened;
        root.left = null;

        // Return current root as new head for the previous level
        return root;
    }
}
