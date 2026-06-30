package arrays;

import java.util.HashSet;
import java.util.Set;

/*
Given an unsorted array of integers nums, return the length of the longest consecutive elements sequence.

You must write an algorithm that runs in O(n) time.



Example 1:

Input: nums = [100,4,200,1,3,2]
Output: 4
Explanation: The longest consecutive elements sequence is [1, 2, 3, 4]. Therefore its length is 4.
Example 2:

Input: nums = [0,3,7,2,5,8,4,6,0,1]
Output: 9
Example 3:

Input: nums = [1,0,1,2]
Output: 3


https://leetcode.com/problems/longest-consecutive-sequence/description/
 */

public class LongestConsecutiveSubSequence {
    public int longestConsecutive(int[] nums) {
        int n = nums.length;

        Set<Integer> set = new HashSet<>();

        for(int i: nums){
            set.add(i);
        }
        int count =0, res =0;
        for(int i: nums){
            if (!set.contains(i-1)){
                count = 1;
                int x = i;
                while(set.contains(x+1)){
                    count+=1;
                    x++;
                }
                res = Math.max(res, count);
            }
        }
        return res;
    }
}
