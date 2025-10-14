package heapsStack;

import java.util.ArrayDeque;
import java.util.Deque;

/*

https://leetcode.com/problems/sliding-window-maximum/description/

You are given an array of integers nums, there is a sliding window of size k which is moving from the very left of the
 array to the very right. You can only see the k numbers in the window. Each time the sliding window moves right by one position.

Return the max sliding window.

Example 1:

Input: nums = [1,3,-1,-3,5,3,6,7], k = 3
Output: [3,3,5,5,6,7]
Explanation:
Window position                Max
---------------               -----
[1  3  -1] -3  5  3  6  7       3
 1 [3  -1  -3] 5  3  6  7       3
 1  3 [-1  -3  5] 3  6  7       5
 1  3  -1 [-3  5  3] 6  7       5
 1  3  -1  -3 [5  3  6] 7       6
 1  3  -1  -3  5 [3  6  7]      7
Example 2:

Input: nums = [1], k = 1
Output: [1]

 Intuition
The brute force approach would involve iterating over each window and finding the maximum, but that would take O(n * k) time,
 which is inefficient.
Instead, we can use a deque (double-ended queue) to efficiently maintain the maximum of the current window.
The deque stores indices, and we ensure that the maximum element is always at the front of the deque.
2️⃣ Algorithm
Use a deque (dq) to store indices of array elements.
Process each element in nums:
Remove indices from the front of dq if they are out of the current window range (i - k).
Remove elements from the back if they are smaller than the current element (since they won’t be useful in the future).
Insert the current index at the back of the deque.
Store the maximum (nums[dq.peek()]) once we have processed k elements.
Return the result array containing maximums for all windows.

 */
public class MaximumSlidingWindow {
    public int[] maxSlidingWindow(int[] nums, int k) {
        int n = nums.length;
        Deque<Integer> dq = new ArrayDeque<>();
        int[] res = new int[n - k + 1];

        int idx = 0;
        for (int i = 0; i < n; i++) {
            // Remove elements that are out of the window
            if (!dq.isEmpty() && dq.peek() == i - k) {
                dq.poll();
            }

            // Remove elements smaller than nums[i]
            while (!dq.isEmpty() && nums[dq.peekLast()] <= nums[i]) {
                dq.pollLast();
            }

            // Add the current element index
            dq.offer(i);

            // Store the max in result array
            if (i >= k - 1) {
                res[idx++] = nums[dq.peek()];
            }
        }

        return res;
    }
}
