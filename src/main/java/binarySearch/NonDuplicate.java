package binarySearch;

  /*
https://leetcode.com/problems/single-element-in-a-sorted-array/
You are given a sorted array consisting of only integers where every element appears exactly twice, except for one element
 which appears exactly once.

Return the single element that appears only once.

Your solution must run in O(log n) time and O(1) space.

 

Example 1:

Input: nums = [1,1,2,3,3,4,4,8,8]
Output: 2
Example 2:

Input: nums = [3,3,7,7,10,11,11]
Output: 10


What does mid ^ 1 do?

Bitwise XOR with 1 toggles the last bit:
If mid is even (0bXXXX0), mid ^ 1 gives mid + 1 (next odd index).
If mid is odd (0bXXXX1), mid ^ 1 gives mid - 1 (previous even index).
✅ How does this help?

It allows a clean check of whether mid is part of a correct pair:
If nums[mid] == nums[mid ^ 1], the unique element is after mid, so we move right (low = mid + 1).
Otherwise, the unique element is before or at mid, so we move left (high = mid - 1).
Case A: mid is Evenmid ^ 1 becomes mid + 1.The check becomes: nums[mid] == nums[mid + 1].Meaning: An even index matches the
next odd index (e.g., index 2 matches index 3). This is the normal pattern for pairs before the unique element. Since
 everything is normal up to this point, the unique element must be further to the right.👉 Action: low = mid + 1
 Case B: mid is Oddmid ^ 1 becomes mid - 1.The check becomes: nums[mid] == nums[mid - 1].Meaning: An odd index matches
  the previous even index (e.g., index 3 matches index 2). This is still the exact same normal pair $(2,3)$! It means we
   landed on the second element of a normal pair. Everything is still intact before this pair, so the unique element is
   still to the right.👉 Action: low = mid + 1
  */
class NonDuplicate {
    public int singleNonDuplicate(int[] nums) {
        int low = 0, high = nums.length -2;
        while(low <= high) {
            int mid = low + (high - low) /2;
            if (nums[mid] == nums[mid ^1]) {
                low = mid+1;
            } else {
                high = mid - 1;
            }
        }
        return nums[low];
    }
}
