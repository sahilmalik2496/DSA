package binaryTree;

import java.util.*;
/*
Problem Statement: Given a Binary Tree, return its Bottom View. The Bottom View of a Binary Tree is the set of nodes visible when we see the tree from the bottom.
*/


class BottomView
{
//    public class Node {
//        int val;
//        InOrder.TreeNode left;
//        InOrder.TreeNode right;
//
//        Node() {}
//        Node(int val) { this.val = val; }
//        Node(int val, InOrder.TreeNode left, InOrder.TreeNode right) {
//            this.val = val;
//            this.left = left;
//            this.right = right;
//        }
//    }
//    //Function to return a list containing the bottom view of the given tree.
//    public ArrayList <Integer> bottomView(Node root)
//    {
//        ArrayList<Integer> ans = new ArrayList<>();
//        if(root == null) return ans;
//        Map<Integer, Integer> map = new Tree Map<>();
//        Queue<Node> q = new LinkedList<Node>();
//        root.hd = 0;
//        q.add(root);
//        while(!q.isEmpty()) {
//            Node temp = q.remove();
//            int hd = temp.hd;
//            map.put(hd, temp.data);
//            if(temp.left != null) {
//                temp.left.hd = hd - 1;
//                q.add(temp.left);
//            }
//            if(temp.right != null) {
//                temp.right.hd = hd + 1;
//                q.add(temp.right);
//            }
//        }
//
//        for (Map.Entry<Integer,Integer> entry : map.entrySet()) {
//            ans.add(entry.getValue());
//        }
//        return ans;
//
//    }
}
