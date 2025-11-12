package dp;

import java.util.HashMap;
import java.util.Map;

/*

https://leetcode.com/problems/subarray-sum-equals-k/

Given an array of integers nums and an integer k, return the total number of subarrays whose sum equals to k.
A subarray is a contiguous non-empty sequence of elements within an array.

Example 1:
Input: nums = [1,1,1], k = 2
Output: 2
 */
public class SubArraySumEqualsK {
    public int subarraySum(int[] nums, int k) {
        int count =0, sum =0;

        Map<Integer, Integer> map = new HashMap<>();

        map.put(0, 1);

        for(int i: nums) {
            sum += i;
            if(map.containsKey(sum - k)){
                count += map.get(sum - k);
            }


            map.put(sum, map.getOrDefault(sum, 0) + 1);
        }

        return count;
    }
}
