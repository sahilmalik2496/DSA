package greedyAndRecursion;

/*
Given an array of distinct integers candidates and a target integer target, return a list of all unique combinations of candidates where the chosen numbers sum to target. You may return the combinations in any order.

The same number may be chosen from candidates an unlimited number of times. Two combinations are unique if the frequency of at least one of the chosen numbers is different.

The test cases are generated such that the number of unique combinations that sum up to target is less than 150 combinations for the given input.

 

Example 1:

Input: candidates = [2,3,6,7], target = 7
Output: [[2,2,3],[7]]
Explanation:
2 and 3 are candidates, and 2 + 2 + 3 = 7. Note that 2 can be used multiple times.
7 is a candidate, and 7 = 7.
These are the only two combinations.
Example 2:

Input: candidates = [2,3,5], target = 8
Output: [[2,2,2,2],[2,3,3],[3,5]]
Example 3:

Input: candidates = [2], target = 1
Output: []
*/

import java.util.ArrayList;
import java.util.List;

class CombinationSum {
    private void backtrack(int[] candidates, int target, int index, List<Integer> curr, List<List<Integer>> ans) {
        if (target < 0) return; // Stop if target becomes negative
        if (target == 0) { // Target reached
            ans.add(new ArrayList<>(curr));
            return;
        }
        if (index == candidates.length) return; // Base case
        
        // USE IT: Stay at same index to reuse the element
        curr.add(candidates[index]);
        backtrack(candidates, target - candidates[index], index, curr, ans);
        curr.remove(curr.size() - 1); // Backtrack
        
        // SKIP IT: Move to next index
        backtrack(candidates, target, index + 1, curr, ans);
    }

    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> ans = new ArrayList<>();
        backtrack(candidates, target, 0, new ArrayList<>(), ans);
        return ans;
    }
}
