package binarySearch;

import java.util.Arrays;

/*

https://takeuforward.org/data-structure/aggressive-cows-detailed-solution/

Problem Statement: You are given an array 'arr' of size 'n' which denotes the position of stalls.
You are also given an integer 'k' which denotes the number of aggressive cows.
You are given the task of assigning stalls to 'k' cows such that the minimum distance between any two of them
 is the  maximum possible.
Find the maximum possible minimum distance.

Example 1:
Input Format:
 N = 6, k = 4, arr[] = {0,3,4,7,10,9}
Result:
 3
Explanation:
 The maximum possible minimum distance between any two cows will be 3 when 4 cows are placed at positions {0, 3, 7, 10}.
 Here the distances between cows are 3, 4, and 3 respectively. We cannot make the minimum distance greater than 3 in any ways.

Overall Complexity
The binary search runs O(log D) times.
Each binary search iteration calls canPlaceCows(), which runs in O(N).
Thus, the overall time complexity is:
O(NlogN)+O(NlogD)=O(NlogN+NlogD)
Since log D is at most log (max coordinate - min coordinate), it is relatively small compared to N log N, making the
dominant complexity O(N log N).

Final Complexity:
(due to sorting and binary search)
O(NlogN)(due to sorting and binary search)
*/

public class AggressiveCows {
    
    // Checks if cows can be placed with at least 'minDist' between them
    private static boolean canPlaceCows(int[] stalls, int minDist, int cows) {
        int count = 1; // Place the first cow in the first stall
        int lastPlaced = stalls[0];
        
        for (int i = 1; i < stalls.length; i++) {
            if (stalls[i] - lastPlaced >= minDist) {
                count++; // Place the next cow
                lastPlaced = stalls[i]; // Update last placed cow position
                
                if (count >= cows) return true; // Successfully placed all cows
            }
        }
        return false;
    }

    // Finds the maximum possible minimum distance between cows
    public static int findMaxMinDistance(int[] stalls, int cows) {
        Arrays.sort(stalls); // Sort the stall positions
        
        int low = 1, high = stalls[stalls.length - 1] - stalls[0];
        int bestDist = 0;
        
        // Apply binary search on the minimum distance
        while (low <= high) {
            int mid = low + (high - low) / 2;
            
            if (canPlaceCows(stalls, mid, cows)) {
                bestDist = mid; // Update best distance
                low = mid + 1; // Try for a larger minimum distance
            } else {
                high = mid - 1; // Reduce search space
            }
        }
        return bestDist;
    }
    
    public static void main(String[] args) {
        int[] stalls = {0, 3, 4, 7, 10, 9};
        int cows = 4;
        
        int maxMinDistance = findMaxMinDistance(stalls, cows);
        System.out.println("The maximum possible minimum distance is: " + maxMinDistance);
    }
}
