package arrays;

/*

A permutation of an array of integers is an arrangement of its members into a sequence or linear order.

* For example, for arr = [1,2,3], the following are all the permutations of arr: [1,2,3], [1,3,2], [2, 1, 3], [2, 3, 1], [3,1,2], [3,2,1].
The next permutation of an array of integers is the next lexicographically greater permutation of its integer. More formally, if all the permutations of the array are sorted in one container according to their lexicographical order, then the next permutation of that array is the permutation that follows it in the sorted container. If such arrangement is not possible, the array must be rearranged as the lowest possible order (i.e., sorted in ascending order).

Permutations are Ordered: For any sequence of numbers, there is a specific lexicographical (dictionary-like) order of permutations. For example, permutations of [1, 2, 3] are:

Why Find Pivot 1?
* Looking for the Decreasing Point:
    * A permutation stops increasing when a number is smaller than the one after it.
    * For example, in [1, 2, 3], moving from right to left:
        * 3 > 2: pivot found
    * This "pivot" marks the point where we can make the smallest change to generate the next permutation.

3. Why Find Pivot 2?
* Smallest Swap to Maintain Order:
    * Once we identify the pivot (nums[i-1]), we need to replace it with the smallest number to the right that is larger than it. This ensures:
        * The permutation remains as small as possible.
        * It still becomes the next permutation.
    * For example, in [1, 3, 2]:
        * Pivot is 1. The smallest number larger than 1 to its right is 2.
        * Swap 1 and 2.

4. Why Reverse the Subarray?
* Sort the Remaining Elements:
    * After swapping, the numbers to the right of the pivot are still in descending order (since we traversed from right to left to find the pivot).
    * To get the smallest possible permutation, we reverse this subarray to make it ascending.
    * Example:
        * After swapping in [1, 3, 2], the subarray becomes [3].
        * Reverse it: [1, 2, 3].
Putting It All Together
The algorithm cleverly breaks the problem into smaller steps:
1. Find the pivot to locate the smallest change point.
2. Swap intelligently to make the smallest increment.
3. Reverse the rest to ensure the suffix is in its smallest possible order.
This approach ensures we compute the next permutation in a minimal number of operations without generating all permutations.

Intuitive Analogy
Imagine you are rearranging numbers in increasing order. You want to "just barely" increment the sequence:
1. Look for the first place you can "bump up" the value (pivot1).
2. Replace it with the smallest possible value that is still larger (pivot2).
3. Fix everything to the right to make it as small as possible.
This mimics how a dictionary would list the next word after a given one!

 */
public class NextPermutation {
    public void reverse(int[] nums, int pivot) {
        int end = nums.length-1;
        while(pivot < end){
            swap(nums, pivot, end);
            pivot++; end --;
        }
    }

    private void swap(int[] arr, int i, int j){
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public void nextPermutation(int[] nums) {
        int pivot1 = -1, pivot2 = -1;
        for(int i=nums.length-1; i>0; i--) {
            if(nums[i] > nums[i-1]){
                pivot1 = i-1;
                break;
            }
        }
        if(pivot1>=0) {
            int j= nums.length-1;
            while(j>=0 && nums[j]<=nums[pivot1]) {
                j--;
            }
            swap(nums, pivot1, j);
        }
        reverse(nums, pivot1 + 1);
    }
}
