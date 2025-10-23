package heapsStack;
/*
You are given an integer array heights representing the heights of buildings, some bricks, and some ladders.
You start your journey from building 0 and move to the next building by possibly using bricks or ladders.
While moving from building i to building i+1 (0-indexed),
* If the current building's height is greater than or equal to the next building's height, you do not need a ladder or bricks.
* If the current building's height is less than the next building's height, you can either use one ladder or (h[i+1] - h[i]) bricks.
Return the furthest building index (0-indexed) you can reach if you use the given ladders and bricks optimally.

Input: heights = [4,2,7,6,9,14,12], bricks = 5, ladders = 1
Output: 4
Explanation: Starting at building 0, you can follow these steps:
- Go to building 1 without using ladders nor bricks since 4 >= 2.
- Go to building 2 using 5 bricks. You must use either bricks or ladders because 2 < 7.
- Go to building 3 without using ladders nor bricks since 7 >= 6.
- Go to building 4 using your only ladder. You must use either bricks or ladders because 6 < 9.
It is impossible to go beyond building 4 because you do not have any more bricks or ladders.

 Intuition
The goal of the problem is to determine the farthest building you can reach by either:

Using bricks to cover smaller height differences.
Using ladders to handle larger height differences.
Since ladders can cover larger jumps more flexibly, an optimal strategy is to prioritize ladders for the largest height
differences and use bricks for smaller ones. The PriorityQueue (max-heap) is used to keep track of the height differences
 we've used bricks for, allowing us to replace the largest one with a ladder when necessary.

Algorithm Explanation
Initialization:
Use a max-heap (PriorityQueue<Integer> pq) to track the largest height differences covered by bricks.
Iterate through the heights array to calculate height differences between consecutive buildings.
Iterating Through Buildings:
For each building pair (i, i+1), calculate the height difference diff = heights[i+1] - heights[i].
If diff <= 0, no resources are needed, and you can move to the next building.
Using Bricks:
If diff > 0 and you have enough bricks (bricks >= diff), use the bricks to cover the difference and record diff in the max-heap.
Using Ladders:
If bricks are not enough (bricks < diff) but ladders are still available:
Check the largest height difference (pq.peek()) that has been covered by bricks. If it’s greater than the current diff, replace it with a ladder. This gives back bricks equal to the difference (top - diff), freeing up resources for smaller jumps.
If no larger height differences exist in the heap, simply use a ladder for the current jump.
Terminate If Neither Is Available:
If you can’t cover the diff with bricks or ladders, stop. The current building index i will be the farthest you can reach.
Return the Farthest Building Index:
Return i, which represents the farthest building you could reach.
Key Points
Greedy Resource Allocation:
Prioritize using bricks for smaller height differences and ladders for larger height differences.
Efficient Adjustment:
The max-heap ensures we can replace the largest height difference (covered by bricks) with a ladder, allowing better resource allocation dynamically.
Time Complexity
Heap Operations:
Each pq.offer() or pq.poll() operation costs
O(logk), where
k
k is the size of the heap. Since we may perform at most
n heap operations, this contributes
O(nlogk).
Iteration Through Heights:
We iterate through the heights array once, contributing
O(n).
Overall complexity:
O(nlogk), where
k
k is the number of elements in the heap (bounded by the number of height differences).

Space Complexity
The PriorityQueue stores at most
n−1 elements (height differences), so the space complexity is
O(n).
 */

import java.util.PriorityQueue;

public class FurthestBuilding {
    public int furthestBuilding(int[] heights, int bricks, int ladders) {
        PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> b-a); // max heap

        int n = heights.length;

        int i;
        for(i=0; i< n-1; i++) {
            int diff = heights[i+1] - heights[i];

            if (diff > 0) {
                if (bricks >= diff) {
                    bricks-= diff;
                    pq.offer(diff);
                } else if(ladders > 0) {
                    if (!pq.isEmpty() && pq.peek() > diff) {
                        int top = pq.poll();
                        bricks += top -diff;
                        pq.offer(diff);
                        ladders--;
                    } else {
                        ladders--;
                    }
                } else {
                    break;
                }
            }
        }

        return i;

    }
}
