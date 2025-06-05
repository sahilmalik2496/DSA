package binarySearch;

  /*
Given two sorted arrays nums1 and nums2 of size m and n respectively, return the median of the two sorted arrays.

The overall run time complexity should be O(log (m+n)).

 

Example 1:

Input: nums1 = [1,3], nums2 = [2]
Output: 2.00000
Explanation: merged array = [1,2,3] and median is 2.
Example 2:

Input: nums1 = [1,2], nums2 = [3,4]
Output: 2.50000
Explanation: merged array = [1,2,3,4] and median is (2 + 3) / 2 = 2.5.
  */

  
class MedianArrays {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int n1 = nums1.length, n2 = nums2.length;
        if (n2< n1) return findMedianSortedArrays(nums2, nums1);
        int low = 0, high = nums1.length;
        int count = nums1.length - nums2.length;
        while(low <= high) {
            int c1 = (low + high) /2;
            int c2 = ((n1 + n2 + 1) /2) - c1;
            int l1 = c1 == 0 ? Integer.MIN_VALUE: nums1[c1-1];
            int l2 = c2 == 0 ? Integer.MIN_VALUE: nums2[c2-1];
            int r1 = c1 == n1 ? Integer.MAX_VALUE: nums1[c1];
            int r2 = c2 == n2 ? Integer.MAX_VALUE: nums2[c2];
            if (l1 <= r2 && l2 <= r1) {
                if ((n1 + n2) % 2 == 0) {
                    return (Math.max(l1, l2) + Math.min(r1, r2)) / 2.0; 
                } else {
                    return Math.max(l1, l2);
                }
            } else if (l1 > r2) {
                high = c1 -1;
            } else {
                low = c1 +1;
            }
        }
        return 0.0;
    }
}
