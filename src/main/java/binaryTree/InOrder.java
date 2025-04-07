package binaryTree;

/*
Given the root of a binary tree, return the inorder traversal of its nodes' values.
*/

class InOrder {
   
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
