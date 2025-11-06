package binaryTree;

import java.util.*;
/*

https://leetcode.com/problems/binary-tree-paths/

Given the root of a binary tree, return all root-to-leaf paths in any order.

A leaf is a node with no children.

 
*/
class TreePath{

    public class Node {
        int val;
        Node left;
        Node right;
        Node() {}
        Node(int val) { this.val = val; }
        Node(int val, Node left, Node right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }


static boolean getPath(Node root, ArrayList < Integer > arr, int x) {
        // if root is NULL
        // there is no path
        if (root == null)
            return false;

        // push the node's value in 'arr'
        arr.add(root.val);

        // if it is the required node
        // return true
        if (root.val == x)
            return true;

        // else check whether the required node lies
        // in the left subtree or right subtree of
        // the current node
        if (getPath(root.left, arr, x) ||
            getPath(root.right, arr, x))
            return true;

        // required node does not lie either in the
        // left or right subtree of the current node
        // Thus, remove current node's value from
        // 'arr'and then return false   
        arr.remove(arr.size() - 1);
        return false;
    }

}
