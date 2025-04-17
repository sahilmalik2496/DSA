package binaryTree;

/*
 Given a Binary Search Tree and a key, return the floor of the given key in the Binary Search Tree.

Floor of a value refers to the value of the largest node in the Binary Search Tree that is smaller than or equal to the given key.
If the floor node does not exist, return nullptr.

*/

class FloorinBST {

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
   public static int floorInBST(TreeNode<Integer> root, int X) {
        //    Write your code here.
        int res = -1;
        while(root != null) {
            if (root.data == X) {
                return X;
            }
            if (X > root.data) {
                res = root.data;
                root = root.right;
            }
                else {
                    root = root.left;
                }
            }

        return res;
    }
}
