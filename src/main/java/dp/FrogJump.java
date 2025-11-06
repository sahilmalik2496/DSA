package dp;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/*

https://leetcode.com/problems/frog-jump/description/

 A frog is crossing a river. The river is divided into some number of units, and at each unit, there
 may or may not exist a stone. The frog can jump on a stone, but it must not jump into the water.
Given a list of stones positions (in units) in sorted ascending order, determine if the frog can cross
the river by landing on the last stone. Initially, the frog is on the first stone and assumes the first
jump must be 1 unit.
If the frog's last jump was k units, its next jump must be either k - 1, k, or k + 1 units. The frog can
only jump in the forward direction.

Example 1:
Input: stones = [0,1,3,5,6,8,12,17]
Output: true
Explanation: The frog can jump to the last stone by jumping 1 unit to the 2nd stone, then 2 units to the 3rd stone,
 then 2 units to the 4th stone, then 3 units to the 6th stone, 4 units to the 7th stone, and 5 units to the 8th stone.
Example 2:
Input: stones = [0,1,2,3,4,8,9,11]
Output: false
Explanation: There is no way to jump to the last stone as the gap between the 5th and 6th stone is too large.


1. Dynamic Programming with Sets:
    * Use a map (dp) where each key is a stone position, and the value is a set of jump lengths that allow the frog to
     reach that stone.

 */
public class FrogJump {

    public boolean canCross(int[] stones) {
        if (stones == null || stones.length == 0) {
            return false;
        }

        // Use a map to store reachable jumps for each stone
        // The key is the stone's position, and the value is a Set of possible jump sizes
        // that landed on that stone.
        Map<Integer, Set<Integer>> dp = new HashMap<>();
        for (int stone : stones) {
            dp.put(stone, new HashSet<>());
        }

        // Initial jump is 0 at the first stone (stone at index 0, value 0)
        // This means we are at stone 0, and the 'jump' to get there was 0 (starting point).
        dp.get(0).add(0);

        // Iterate through each stone to explore possible jumps from it
        for (int stone : stones) {
            // For each possible jump size that landed on the current 'stone'
            for (int jump : dp.get(stone)) {
                // Calculate possible next jumps from the current 'stone'
                // A frog can jump k-1, k, or k+1 units from its current position,
                // where k is the last jump.
                for (int nextJump = jump - 1; nextJump <= jump + 1; nextJump++) {
                    // Ensure the next jump is positive (cannot jump backward or stay)
                    // And check if the target stone exists in our set of stones
                    if (nextJump > 0 && dp.containsKey(stone + nextJump)) {
                     //   System.out.println(stone + nextJump + " ");
                        // If the target stone exists, add this 'nextJump' as a way to reach it
                        dp.get(stone + nextJump).add(nextJump);
                    }
                }
            }
        }

        // After processing all stones, check if the last stone has any reachable jumps.
        // If the set of jumps for the last stone is not empty, it means we can reach it.
        return !dp.get(stones[stones.length - 1]).isEmpty(); 
    }

    public static void main(String[] args) {
        FrogJump frogJump = new FrogJump();

        // Example 1: Frog can cross
        int[] stones1 = {0, 1, 3, 5, 6, 8, 12, 17};
        System.out.println("Can frog cross for stones " + java.util.Arrays.toString(stones1) + "? " + frogJump.canCross(stones1)); // Expected: true

        // Example 2: Frog cannot cross
//        int[] stones2 = {0, 1, 2, 3, 4, 8, 9, 11};
//        System.out.println("Can frog cross for stones " + java.util.Arrays.toString(stones2) + "? " + frogJump.canCross(stones2)); // Expected: false
//
//        // Example 3: Empty stones array
//        int[] stones3 = {};
//        System.out.println("Can frog cross for stones " + java.util.Arrays.toString(stones3) + "? " + frogJump.canCross(stones3)); // Expected: false
//
//        // Example 4: Single stone
//        int[] stones4 = {0};
//        System.out.println("Can frog cross for stones " + java.util.Arrays.toString(stones4) + "? " + frogJump.canCross(stones4)); // Expected: true (already at the last stone)
//
//        // Example 5: Two stones, can cross
//        int[] stones5 = {0, 1};
//        System.out.println("Can frog cross for stones " + java.util.Arrays.toString(stones5) + "? " + frogJump.canCross(stones5)); // Expected: true
//
//        // Example 6: Two stones, cannot cross (gap too big)
//        int[] stones6 = {0, 2};
//        System.out.println("Can frog cross for stones " + java.util.Arrays.toString(stones6) + "? " + frogJump.canCross(stones6)); // Expected: false
    }
}