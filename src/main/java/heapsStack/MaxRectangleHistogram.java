package heapsStack;

/*
https://leetcode.com/problems/largest-rectangle-in-histogram/
Given an array of integers heights representing the histogram's bar height where the width of each bar is 1, return the
area of the largest rectangle in the histogram.



Example 1:


Input: heights = [2,1,5,6,2,3]
Output: 10
Explanation: The above is a histogram where width of each bar is 1.
The largest rectangle is shown in the red area, which has an area = 10 units.

Intuition
Given an array heights[], where each element represents the height of a histogram bar, we need to find the largest
rectangular area possible in the histogram.

The key observation is that:

For each bar, the maximum rectangle with that bar as the shortest height extends as far left and right as possible.
To efficiently find these left and right limits, we use a monotonic stack.
Algorithm
Find left boundaries:

Use a monotonic increasing stack.
For each bar heights[i], find the first smaller element on the left.
Store this index in left[i].
Find right boundaries:

Use the same monotonic increasing stack, but traverse from right to left.
Find the first smaller element on the right.
Store this index in right[i].
Compute the maximum area:

For each bar heights[i], calculate the width of the largest rectangle it can contribute to:
width=right[i]−left[i]+1
Compute area = heights[i] * width and keep track of the maximum.
 */
import java.util.*;

class MaxRectangleHistogram {
    public int largestRectangleArea(int[] heights) {
        int n = heights.length;
        int[] left = new int[n], right = new int[n];
        Stack<Integer> stack = new Stack<>();

        // Compute left boundaries
        for (int i = 0; i < n; i++) {
            while (!stack.isEmpty() && heights[stack.peek()] >= heights[i]) {
                stack.pop();
            }
            left[i] = stack.isEmpty() ? 0 : stack.peek() + 1;
            stack.push(i);
        }

        stack.clear();

        // Compute right boundaries
        for (int i = n - 1; i >= 0; i--) {
            while (!stack.isEmpty() && heights[stack.peek()] >= heights[i]) {
                stack.pop();
            }
            right[i] = stack.isEmpty() ? n - 1 : stack.peek() - 1;
            stack.push(i);
        }

        // Compute max area
        int maxArea = 0;
        for (int i = 0; i < n; i++) {
            maxArea = Math.max(maxArea, heights[i] * (right[i] - left[i] + 1));
        }

        return maxArea;
    }

    public static void main(String[] args) {
        MaxRectangleHistogram sol = new MaxRectangleHistogram();
        int[] heights = {2, 1, 5, 6, 2, 3};
        System.out.println("Largest Rectangle Area: " + sol.largestRectangleArea(heights)); // Output: 10
    }
}
