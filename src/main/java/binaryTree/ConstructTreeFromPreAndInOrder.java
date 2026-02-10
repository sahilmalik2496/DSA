package binaryTree;

import java.util.HashMap;
import java.util.Map;

/*
Both functions reconstruct the same binary tree from `inorder` plus one other traversal, but they pick
 the root and compute subtree ranges differently.

- Root selection
  - `build` (preorder + inorder): root is `preorder[preStart]` (first element of current preorder range).
  - `buildPostOrder` (postorder + inorder): root is `postorder[postEnd]` (last element of current
  postorder range).

- Using `inorder` to split
  - Both use the map to get `rootIndex` in `inorder`, then compute `leftSize = rootIndex - inStart`
  (number of nodes in left subtree).

- How subtree ranges are computed
  - Preorder:
    - Left subtree preorder range: `preStart+1` .. `preStart+leftSize`
    - Right subtree preorder range: `preStart+leftSize+1` .. `preEnd`
    - Inorder ranges: left `inStart`..`rootIndex-1`, right `rootIndex+1`..`inEnd`
    - Reason: preorder lists root first, then left subtree, then right subtree.
  - Postorder:
    - Left subtree postorder range: `postStart` .. `postStart+leftSize-1`
    - Right subtree postorder range: `postStart+leftSize` .. `postEnd-1`
    - Inorder ranges: same split as above
    - Reason: postorder lists left subtree, then right subtree, then root at the end.

- Other differences
  - `build` prints `root.val` (side effect).
  - Parameter ordering / boundary indices differ to reflect where the root sits in the traversal array.

- Complexity
  - Both run in O(n) time with O(n) extra space for the map and recursion stack.
 */

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
    private TreeNode buildPostOrder(int[] inorder, int[] postorder, int inStart, int inEnd, int postStart, int postEnd,
                                    HashMap<Integer, Integer> map){
        if (inStart > inEnd || postStart > postEnd){
            return null;
        }
        int rootVal = postorder[postEnd];
        TreeNode root = new TreeNode(rootVal);
        int rootIndex = map.get(rootVal);
        int leftSize = rootIndex - inStart;
        root.left = buildPostOrder(inorder, postorder, inStart, rootIndex - 1, postStart,
                postStart + leftSize - 1, map);
        root.right = buildPostOrder(inorder, postorder, inStart + leftSize + 1, inEnd,
                postStart + leftSize, postEnd - 1, map);
        return root;
    }

}
