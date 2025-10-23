package heapsStack;

import java.util.Collections;
import java.util.Iterator;
import java.util.PriorityQueue;

/*
https://leetcode.com/problems/kth-largest-element-in-an-array/

Given an integer array nums and an integer k, return the kth largest element in the array.

Note that it is the kth largest element in the sorted order, not the kth distinct element.

Can you solve it without sorting?



Example 1:

Input: nums = [3,2,1,5,6,4], k = 2
Output: 5
Example 2:

Input: nums = [3,2,3,1,2,4,5,5,6], k = 4
Output: 4
 */
public class KthLargestElement {
    public int findKthLargest(int[] nums, int k) {
        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
        for(int i: nums) {
            pq.add(i);
        }

        Iterator it = pq.iterator();

        while(it.hasNext() && k>0) {
            int i = pq.poll();

            k--;
            if (k==0) {
                return i;
            }
        }
        return -1;

    }
}
