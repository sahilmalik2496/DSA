package binaryTree;

import java.util.*;
/*
Given the root of a binary tree, calculate the vertical order traversal of the binary tree.

For each node at position (row, col), its left and right children will be at positions (row + 1, col - 1) and (row + 1, col + 1) respectively. The root of the tree is at (0, 0).

The vertical order traversal of a binary tree is a list of top-to-bottom orderings for each column index starting from the leftmost column and ending on the rightmost column. There may be multiple nodes in the same row and same column. In such a case, sort these nodes by their values.

Return the vertical order traversal of the binary tree.
*/


class VerticalOrder {

    public List<List<Integer>> verticalTraversal(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) return result;

        // Queue for BFS: stores node and its vertical column index
        Queue<Pair> queue = new LinkedList<>();
        queue.offer(new Pair(root, 0));

        // Map to store final column-wise node values
        HashMap<Integer, List<Integer>> columnMap = new HashMap<>();

        // Track the range of column indices
        int minCol = 0, maxCol = 0;

        while (!queue.isEmpty()) {
            int size = queue.size();
            // Temporary map to store multiple nodes on same level per column
            HashMap<Integer, List<Integer>> levelMap = new HashMap<>();

            for (int i = 0; i < size; i++) {
                Pair pair = queue.poll();
                TreeNode node = pair.node;
                int col = pair.col;

                levelMap.putIfAbsent(col, new ArrayList<>());
                levelMap.get(col).add(node.val);

                if (node.left != null) queue.offer(new Pair(node.left, col - 1));
                if (node.right != null) queue.offer(new Pair(node.right, col + 1));

                minCol = Math.min(minCol, col);
                maxCol = Math.max(maxCol, col);
            }

            // Sort values at the same level before putting them in columnMap
            for (Map.Entry<Integer, List<Integer>> entry : levelMap.entrySet()) {
                int col = entry.getKey();
                List<Integer> vals = entry.getValue();
                Collections.sort(vals); // sort only if multiple nodes share same row and column
                columnMap.putIfAbsent(col, new ArrayList<>());
                columnMap.get(col).addAll(vals);
            }
        }

        // Build result list from columnMap, from minCol to maxCol
        for (int col = minCol; col <= maxCol; col++) {
            if (columnMap.containsKey(col)) {
                result.add(columnMap.get(col));
            }
        }

        return result;
    }

    // Helper class to store node and its column index
    class Pair {
        TreeNode node;
        int col;

        Pair(TreeNode n, int c) {
            node = n;
            col = c;
        }
    }

    // Definition for a binary tree node.
    static class TreeNode {
        int val;
        TreeNode left, right;
        TreeNode(int x) { val = x; }
    }
}
