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

Normally, binary search is used to find a specific index or element in an array. Here, we are binary searching for the value
of the distance itself.low = 0: The smallest possible absolute difference between any two numbers is 0 (if there are duplicates)
.high = nums[n-1] - nums[0]: After sorting, the largest possible difference is the last element minus the first element.
The algorithm guesses a distance (mid) and asks a core question:👉 "How many pairs in the array have a distance less than
or equal to mid?"If that count is less than $k$, our guessed distance mid is too small. We must look in the upper half
(low = mid + 1).If that count is greater than or equal to $k$, mid could be our answer, but we want to see if a smaller
valid distance exists. So, we look in the lower half (high = mid - 1).
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
