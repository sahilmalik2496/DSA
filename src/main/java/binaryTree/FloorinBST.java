package binaryTree;

/*
https://practice.geeksforgeeks.org/problems/floor-in-bst/1
 Given a Binary Search Tree and a key, return the floor of the given key in the Binary Search Tree.

Floor of a value refers to the value of the largest node in the Binary Search Tree that is smaller than or equal to the given key.
If the floor node does not exist, return nullptr.

*/

class FloorinBST {

   public static class TreeNode {
   int data;
      TreeNode left;
      TreeNode right;
      TreeNode() {}
      TreeNode(int val) { this.data = val; }
      TreeNode(int val, TreeNode left, TreeNode right) {
          this.data = val;
          this.left = left;
          this.right = right;
      }
  }
   public static int floorInBST(TreeNode root, int X) {
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

    public static void main(String[] args) {
        // --- 1. Build the Test BST ---
        // Values: {10, 5, 15, 3, 7, 12, 18}

        // 1. Create leaf nodes and work upwards
        TreeNode newRoot = getTreeNode();

        System.out.println("BST Structure: {2, 81, 87, 42, 66, 90, 45}");
        System.out.println("------------------------------------------");

        // --- 4. New Test Case: X = 70 (Expected Floor is 66) ---
        int X_new = 70;
        int floor_new = floorInBST(newRoot, X_new);
        System.out.println("Floor of " + X_new + " is: " + floor_new + " (Expected: 66)");
    }

    private static TreeNode getTreeNode() {
        TreeNode node45_new = new TreeNode(45);
        TreeNode node90_new = new TreeNode(90);

        // 2. Create mid-level nodes
        // 66 (L: 45)
        TreeNode node66_new = new TreeNode(66, node45_new, null);
        // 87 (R: 90)
        TreeNode node87_new = new TreeNode(87, null, node90_new);

        // 3. Create level 1 nodes
        // 42 (R: 66)
        TreeNode node42_new = new TreeNode(42, null, node66_new);
        // 81 (L: 42, R: 87)
        TreeNode node81_new = new TreeNode(81, node42_new, node87_new);

        // 4. Create root node
        // 2 (R: 81)
        TreeNode newRoot = new TreeNode(2, null, node81_new);
        return newRoot;
    }
}
