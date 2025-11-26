package greedyAndRecursion;

import java.util.*;

/*
https://leetcode.com/problems/permutations/description/

Given an array nums of distinct integers, return all the possible permutations. You can return the answer in any order.

 

Example 1:

Input: nums = [1,2,3]
Output: [[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]
Example 2:

Input: nums = [0,1]
Output: [[0,1],[1,0]]
Example 3:

Input: nums = [1]
Output: [[1]]
*/

class Permutations {

    public List<List<Integer>> permute(int[] nums) {
    List<List<Integer>> list = new ArrayList<>();
    if(nums == null || nums.length == 0 ) return list;
    backtrack(list, new ArrayList<>(), nums);
    return list;
}

private void backtrack(List<List<Integer>> list, List<Integer> tempList, int [] nums){
    if(tempList.size() == nums.length){
        list.add(new ArrayList<>(tempList));
    } else{
        for(int i = 0; i <= tempList.size(); i++){
            tempList.add(i, nums[tempList.size()]);
            backtrack(list, tempList, nums);
            tempList.remove(i);
        } 
    }
}
}
