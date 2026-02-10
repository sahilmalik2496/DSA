package binaryTree;



/*

https://leetcode.com/problems/flatten-binary-tree-to-linked-list/

Given the root of a binary tree, flatten the tree into a "linked list":

The "linked list" should use the same TreeNode class where the right child pointer points to the next node in the list and the left child pointer is always null.
The "linked list" should be in the same order as a pre-order traversal of the binary tree.


The logic relies on a global variable prev to keep track of the node that was "just processed."

Right-to-Left Traversal: The code calls flat(root.right) then flat(root.left). This ensures that the
 recursion reaches the rightmost, bottom-most node of the tree first.

The "Relinking" Step:

root.right = prev;: The current node's right pointer is set to the node we processed in the previous step.

root.left = null;: The left pointer is cleared (required for a "flattened" linked list).

Updating prev:

prev = root;: The current node now becomes the "previous" node for the next step up the recursive stack.

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
