package binaryTree;

import java.util.HashMap;
import java.util.Map;

public class ConstructTreeFromPreAndInOrder {

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

    public TreeNode buildTree(int[] preOrder, int[] inOrder) {
        Map<Integer, Integer> mp = new HashMap<>();
        for(int i=0; i< inOrder.length; i++) {
            mp.put(inOrder[i], i);
        }
        return build(preOrder, inOrder, 0, preOrder.length-1, 0, inOrder.length-1, mp);
    }

    public TreeNode build(int[] preOrder, int[] inOrder, int preStart, int preEnd,
                          int inStart, int inEnd, Map<Integer, Integer> mp) {
        if (preStart > preEnd || inStart > inEnd) return null;
        TreeNode root = new TreeNode(preOrder[preStart]);

        System.out.print(root.val);
        int inRoot = mp.get(root.val);
        int numsLeft = inRoot - inStart;

        root.left = build(preOrder, inOrder, preStart +1, preStart + numsLeft, inStart,
                inRoot-1, mp);

        root.right = build(preOrder, inOrder, preStart +numsLeft + 1, preEnd, inRoot+1,
                inEnd, mp);
        return root;
    }
//    private TreeNode buildPostOrder(int[] inorder, int[] postorder, int inStart, int inEnd, int postStart, int postEnd, HashMap<Integer, Integer> map){
//        if (inStart > inEnd || postStart > postEnd){
//            return null;
//        }
//        int rootVal = postorder[postEnd];
//        TreeNode root = new TreeNode(rootVal);
//        int rootIndex = map.get(rootVal);
//        int leftSize = rootIndex - inStart;
//        root.left = buildTree(inorder, postorder, inStart, rootIndex - 1, postStart, postStart + leftSize - 1, map);
//        root.right = buildTree(inorder, postorder, inStart + leftSize + 1, inEnd, postStart + leftSize, postEnd - 1, map);
//        return root;
//    }

}
