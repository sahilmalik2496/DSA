package arrays;

import java.util.ArrayList;

/*

https://leetcode.com/problems/reverse-pairs/description/

Given an integer array nums, return the number of reverse pairs in the array.
A reverse pair is a pair (i, j) where:
* 0 <= i < j < nums.length and
* nums[i] > 2 * nums[j].
 */
public class ReversePair {
    int merge(int[] nums, int l, int mid, int r){
        ArrayList<Integer> temp = new ArrayList<>();

        int count =0, j= mid+1;
        for(int i=l; i<= mid; i++) {
            while(j<= r && nums[i] > 2* (long)nums[j]){
                j++;
            }

            count += (j - (mid+1));
        }


        int left = l, right = mid+1;

        while(left <= mid && right <= r){
            if (nums[left] < nums[right]){
                temp.add(nums[left++]);
            } else {
                temp.add(nums[right++]);
            }
        }

        while(left <= mid){
            temp.add(nums[left++]);
        }

        while(right <= r){
            temp.add(nums[right++]);
        }
        for(int i=l; i<= r; i++){
            nums[i] = temp.get(i-l);
        }

        return count;
    }


    int mergeSort(int[] nums, int l, int r){
        if (l>= r){
            return 0;
        }

        int mid = (l+r)/ 2;

        int inv = mergeSort(nums, l, mid);

        inv += mergeSort(nums, mid+1, r);

        inv += merge(nums, l, mid, r);
        return inv;
    }

    public int reversePairs(int[] nums) {
        return mergeSort(nums, 0, nums.length - 1);
    }

}
