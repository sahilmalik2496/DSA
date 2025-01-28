package dp;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/*
 A frog is crossing a river. The river is divided into some number of units, and at each unit, there may or may not exist a stone. The frog can jump on a stone, but it must not jump into the water.
Given a list of stones positions (in units) in sorted ascending order, determine if the frog can cross the river by landing on the last stone. Initially, the frog is on the first stone and assumes the first jump must be 1 unit.
If the frog's last jump was k units, its next jump must be either k - 1, k, or k + 1 units. The frog can only jump in the forward direction.
 
Example 1:
Input: stones = [0,1,3,5,6,8,12,17]
Output: true
Explanation: The frog can jump to the last stone by jumping 1 unit to the 2nd stone, then 2 units to the 3rd stone, then 2 units to the 4th stone, then 3 units to the 6th stone, 4 units to the 7th stone, and 5 units to the 8th stone.
Example 2:
Input: stones = [0,1,2,3,4,8,9,11]
Output: false
Explanation: There is no way to jump to the last stone as the gap between the 5th and 6th stone is too large.


1. Dynamic Programming with Sets:
    * Use a map (dp) where each key is a stone position, and the value is a set of jump lengths that allow the frog to reach that stone.

 */
public class FrogJump {
    public boolean canCross(int[] stones) {
        if (stones == null || stones.length == 0) return false;

        // Use a map to store reachable jumps for each stone
        Map<Integer, Set<Integer>> dp = new HashMap<>();
        for (int stone : stones) {
            dp.put(stone, new HashSet<>());
        }

        // Initial jump is 0 at the first stone
        dp.get(0).add(0);

        for (int stone : stones) {
            for (int jump : dp.get(stone)) {
                // Calculate possible next jumps
                for (int nextJump = jump - 1; nextJump <= jump + 1; nextJump++) {
                    if (nextJump > 0 && dp.containsKey(stone + nextJump)) {
                        dp.get(stone + nextJump).add(nextJump);
                    }
                }
            }
        }

        // Check if the last stone has any reachable jumps
        return !dp.get(stones[stones.length - 1]).isEmpty();
    }
}
