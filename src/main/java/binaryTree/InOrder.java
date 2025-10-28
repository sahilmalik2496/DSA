package binaryTree;

import java.util.*;
/*

https://leetcode.com/problems/binary-tree-inorder-traversal/

Given the root of a binary tree, return the inorder traversal of its nodes' values.
*/


class InOrder {
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

    public List<Integer> inorderTraversal(TreeNode root) {
         ArrayList<Integer> arr = new ArrayList<>();
        if(root == null){
            return arr;
        }
        Stack<TreeNode> stack = new Stack<>();
        while(true){
            if(root != null){
                stack.push(root);
                root = root.left;
            }else{
                if(stack.isEmpty())
                    break;
                root= stack.pop();
                arr.add(root.val);
                root= root.right;
            }
        }
        
        return arr;
    }
}
