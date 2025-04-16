package binaryTree;

import java.util.*;
/*
Given the root of a binary tree, return the level order traversal of its nodes' values. (i.e., from left to right, level by level).
*/


class LevelOrder {
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
    public List<List<Integer>> levelOrder(TreeNode root) {
        Queue<TreeNode> q = new LinkedList<>();
        List<List<Integer>> res = new ArrayList<>();
        if(root==null){
            return res;
        }
        q.add(root);
        while(!q.isEmpty()) {
            int n = q.size();
            System.out.println(n);
            List<Integer> list = new ArrayList<>();
            for(int i=0; i< n; i++){
                TreeNode curr = q.poll();
                list.add(curr.val);
                if(curr.left != null) {
                    q.add(curr.left);
                } 
                if (curr.right != null) {
                    q.add(curr.right);
                }
            }
            res.add(list);
        }
        return res;
    }
}
