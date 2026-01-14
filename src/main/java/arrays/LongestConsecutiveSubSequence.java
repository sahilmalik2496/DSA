package arrays;

import java.util.HashSet;
import java.util.Set;

/*
Given two strings text1 and text2, return the length of their longest common subsequence. If there is no common subsequence, return 0.

A subsequence of a string is a new string generated from the original string with some characters (can be none) deleted without changing the relative order of the remaining characters.

For example, "ace" is a subsequence of "abcde".
A common subsequence of two strings is a subsequence that is common to both strings.

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
