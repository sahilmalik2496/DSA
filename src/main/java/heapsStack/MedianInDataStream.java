package heapsStack;

import java.util.Collections;
import java.util.PriorityQueue;

/*

 https://leetcode.com/problems/find-median-from-data-stream/

The median is the middle value in an ordered integer list. If the size of the list is even, there is no middle value,
 and the median is the mean of the two middle values.

For example, for arr = [2,3,4], the median is 3.
For example, for arr = [2,3], the median is (2 + 3) / 2 = 2.5.
Implement the MedianFinder class:

MedianFinder() initializes the MedianFinder object.
void addNum(int num) adds the integer num from the data stream to the data structure.
double findMedian() returns the median of all elements so far. Answers within 10-5 of the actual answer will be accepted.


Example 1:

Input
["MedianFinder", "addNum", "addNum", "findMedian", "addNum", "findMedian"]
[[], [1], [2], [], [3], []]
Output
[null, null, null, 1.5, null, 2.0]

Explanation
MedianFinder medianFinder = new MedianFinder();
medianFinder.addNum(1);    // arr = [1]
medianFinder.addNum(2);    // arr = [1, 2]
medianFinder.findMedian(); // return 1.5 (i.e., (1 + 2) / 2)
medianFinder.addNum(3);    // arr[1, 2, 3]
medianFinder.findMedian(); // return 2.0


Constraints:

-105 <= num <= 105
There will be at least one element in the data structure before calling findMedian.
At most 5 * 104 calls will be made to addNum and findMedian.


Follow up:

If all integer numbers from the stream are in the range [0, 100], how would you optimize your solution?
If 99% of all integer numbers from the stream are in the range [0, 100], how would you optimize your solution?



Intuition
The problem requires finding the median of a continuously growing data stream efficiently. The median is the middle
value in a sorted list:

If the number of elements is odd, the median is the middle element.
If the number of elements is even, the median is the average of the two middle elements.
Since inserting elements into a sorted list and finding the median repeatedly would be inefficient (O(N log N) per insert
if using sorting), we use two heaps (priority queues) to maintain balance efficiently:

Max Heap (Left Half - maxQ): Stores the smaller half of the numbers (largest element on top).
Min Heap (Right Half - minQ): Stores the larger half of the numbers (smallest element on top).
We maintain the invariant:

maxQ contains at most one more element than minQ to ensure efficient median computation.
Algorithm
Adding a number (addNum(num))

If maxQ is empty or num is less than or equal to the largest element in maxQ, add it to maxQ.
Otherwise, add it to minQ.
Balance the heaps:
If maxQ has more than one extra element than minQ, move the top element from maxQ to minQ.
If minQ has more elements than maxQ, move the top element from minQ to maxQ.
Finding the median (findMedian())

If maxQ has more elements, return the top element of maxQ (odd-sized total).
Otherwise, return the average of the tops of maxQ and minQ (even-sized total).
Time Complexity
addNum(num): O(log N) (insertion and rebalancing heaps)
findMedian(): O(1) (heap peek operation)
Overall Complexity: O(log N) per insert, O(1) per median query
 */


public class MedianInDataStream {
    PriorityQueue<Integer> maxQ = new PriorityQueue<>(Collections.reverseOrder());
    PriorityQueue<Integer> minQ = new PriorityQueue<>();
    public MedianInDataStream() {
    }

    public void addNum(int num) {
        if (maxQ.size() == 0 || maxQ.peek() >= num){
            maxQ.add(num);
        } else{
            minQ.add(num);
        }
        if (maxQ.size() > minQ.size() + 1){
            minQ.add(maxQ.poll());
        } else if (maxQ.size() < minQ.size()){
            maxQ.add(minQ.poll());
        }


    }

    public double findMedian() {
        if (maxQ.size() > minQ.size()) {
            return maxQ.peek();
        } else {
            int a1 = maxQ.peek();
            int a2 = minQ.peek();
            return (a1 + a2) /2.0;
        }
    }
}
