package binaryTree;

import java.utils.*;
/*
Given the root of a binary tree, return the zigzag level order traversal of its nodes' values. (i.e., from left to right, then right to left for the next level and alternate between).

*/

class ZigZagTravel {
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {

        List<List<Integer>> res = new ArrayList<>();
        if (root == null) {
            return res;
        }
        int idx =0;
        Queue<TreeNode> q = new LinkedList<>();
        q.add(root);
        while(!q.isEmpty()) {
            List<Integer> list = new ArrayList<>();
            int n = q.size();
            for(int i=0; i< n; i++) {
                TreeNode curr =q.poll();
                if (idx % 2 == 0) {
                    list.add(curr.val);
                } else {
                    list.add(0, curr.val);
                }
                    if (curr.left != null) {
                        q.add(curr.left);
                    }
                    if (curr.right != null) {
                        q.add(curr.right);
                    }
            }
            idx +=1;
            res.add(list);
        }
        return res;
    }
}
