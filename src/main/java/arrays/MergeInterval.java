package arrays;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/*

https://leetcode.com/problems/merge-intervals/description/


Given an array of intervals where intervals[i] = [starti, endi], merge all overlapping intervals, and return an array of the non-overlapping intervals that cover all the intervals in the input.

Input: intervals = [[1,3],[2,6],[8,10],[15,18]]
Output: [[1,6],[8,10],[15,18]]
Explanation: Since intervals [1,3] and [2,6] overlap, merge them into [1,6].


The key insight is that overlapping intervals can be combined into a single interval if their ranges intersect.
1. Sorting Helps:
    * By sorting intervals based on their start times, we can simplify the problem. Once sorted, overlapping intervals will always be adjacent.
2. Merging Adjacent Intervals:
    * As we iterate through the sorted intervals, we compare the current interval with the last merged interval. If they overlap, we extend the merged interval. If not, we add the previous merged interval to the result list and start a new merge with the current interval.
This greedy approach ensures all intervals are merged efficiently while maintaining the correct order.

 */
public class MergeInterval {
    public int[][] merge(int[][] intervals) {
        int n = intervals.length;
        if (n == 0){
            return new int[0][];
        }
        List<int[]> res = new ArrayList<>();
        Arrays.sort(intervals, Comparator.comparingInt(a -> a[0]));
        int start = intervals[0][0], end = intervals[0][1];
        for(int[] curr: intervals) {
            if (curr[0] <= end) {
                end = Math.max(end, curr[1]);
            } else {
                res.add(new int[]{start, end});
                start = curr[0]; end = curr[1];
            }
        }
        res.add(new int[]{start, end});
        return res.toArray(new int[0][]);
    }
}
