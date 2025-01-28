package arrays;

import java.util.HashSet;
import java.util.Set;

public class LongestConsecutiveSubSequence {
    public int longestConsecutive(int[] nums) {
        int n = nums.length;

        Set<Integer> set = new HashSet<>();

        for(int i: nums){
            set.add(i);
        }
        int count =0, res =0;
        for(int i: nums){
            if (!set.contains(i-1)){
                count = 1;
                int x = i;
                while(set.contains(x+1)){
                    count+=1;
                    x++;
                }
                res = Math.max(res, count);
            }
        }
        return res;
    }
}
