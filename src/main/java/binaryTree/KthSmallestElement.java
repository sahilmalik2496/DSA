package binaryTree;

public class KthSmallestElement {

    /*

    https://leetcode.com/problems/kth-smallest-element-in-a-bst/

    Given the root of a binary search tree, and an integer k, return the kth smallest
    value (1-indexed) of all the values of the nodes in the tree.

     */
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


    TreeNode inorder(TreeNode root, int[] res) {
        if (root == null) {
            return null;
        }
        TreeNode left = inorder(root.left, res);
        if (left != null) {
            return left;
        }
        res[0]--;
        if (res[0]==0){
            return root;
        }

        return  inorder(root.right, res);

    }

    public int kthSmallest(TreeNode root, int k) {
        int[] res = new int[]{k};
        TreeNode r = inorder(root, res);
        return r.val;
    }
}
