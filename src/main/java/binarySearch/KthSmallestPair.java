package binarySearch;

import java.util.Arrays;

public class KthSmallestPair {
    /*
    The distance of a pair of integers a and b is defined as the absolute difference between a and b.

Given an integer array nums and an integer k, return the kth smallest distance among all the pairs nums[i] and nums[j]
 where 0 <= i < j < nums.length.



Example 1:

Input: nums = [1,3,1], k = 1
Output: 0
Explanation: Here are all the pairs:
(1,3) -> 2
(1,1) -> 0
(3,1) -> 2
Then the 1st smallest distance pair is (1,1), and its distance is 0.
Example 2:

Input: nums = [1,1,1], k = 2
Output: 0
Example 3:

Input: nums = [1,6,1], k = 3
Output: 5
     */

    int count(int[] nums, int mid){
        int count =0;
        int low =0;
        for(int i=1; i<nums.length; i++){
            while(nums[i] - nums[low] > mid){
                low++;
            }
            count += i - low;
        }

        return count;
    }

    public int smallestDistancePair(int[] nums, int k) {
        int n = nums.length;
        Arrays.sort(nums);
        int low = 0;
        int high = nums[n-1] - nums[0];
        while(low<=high){
            int mid = low + (high - low) / 2;
            int check = count(nums, mid);
            if (check < k) {
                low = mid + 1;
            } else {
                high = mid-1;
            }
        }

        return low;
    }
}
