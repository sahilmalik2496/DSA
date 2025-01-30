package greedy;

import java.util.ArrayList;
import java.util.List;

/*
Given an integer array of size n, find all elements that appear more than ⌊ n/3 ⌋ times.



Example 1:

Input: nums = [3,2,3]
Output: [3]
Example 2:

Input: nums = [1]
Output: [1]
Example 3:

Input: nums = [1,2]
Output: [1,2]
 */
public class MajorityElement2 {
    public List<Integer> majorityElement(int[] nums) {
        int n = nums.length;
        int m1 =0, m2 =0, c1 =0, c2=0;

        for (int i=0; i< n; i++) {
            if (nums[i] == m1) {
                c1++;
            } else if (nums[i] == m2) {
                c2++;
            } else if (c1 == 0) {
                m1 = nums[i];
                c1++;
            } else if (c2 ==0) {
                m2 = nums[i];
                c2++;
            } else {
                c1--;c2--;
            }
        }

        c1 =0;c2 =0;
        for(int num: nums) {
            if (num == m1) {
                c1++;
            } else if (num == m2) {
                c2++;
            }
        }

        List<Integer> res = new ArrayList<>();
        if (c1 > n/3) {
            res.add(m1);
        }
        if (c2 > n/3) {
            res.add(m2);
        }

        return res;
    }
}
