package arrays;

import java.util.ArrayList;

/*
 Count inversions in an array
Problem Statement: Given an array of N integers, count the inversion of the array (using merge-sort).

What is an inversion of an array? Definition: for all i & j < size of array, if i < j then you have to find pair (A[i],A[j]) such that A[j] < A[i].

This problem can be solved efficiently using a modified Merge Sort. Instead of comparing all pairs (which takes
O(n^2)
O(n2)), we leverage the divide and conquer approach of merge sort to count inversions during the merging step.

Key Observations
1. Divide and Conquer:
    * The array is split into two halves recursively using merge sort.
    * While merging two sorted halves, count the inversions caused by the elements from the left half being greater than elements in the right half.
2. Inversion Counting During Merge:
    * If an element in the left half (arr[left]) is greater than an element in the right half (arr[right]), all remaining elements in the left half will also form inversions with arr[right] because the halves are already sorted.
3. Efficiency:
    * Counting inversions during the merge step takes   O(n) for each merge.
    * Recursively sorting and merging takes O(n \log n)  O(nlogn), making this approach far more efficient than a brute-force method.


 */
public class CountInversion {
    private static int merge(int[] arr, int low, int mid, int high) {
        ArrayList<Integer> temp = new ArrayList<>(); // temporary array
        int left = low;      // starting index of left half of arr
        int right = mid + 1;   // starting index of right half of arr

        //Modification 1: cnt variable to count the pairs:
        int cnt = 0;

        //storing elements in the temporary array in a sorted manner//

        while (left <= mid && right <= high) {
            if (arr[left] <= arr[right]) {
                temp.add(arr[left]);
                left++;
            } else {
                temp.add(arr[right]);
                cnt += (mid - left + 1); //Modification 2
                right++;
            }
        }

        // if elements on the left half are still left //

        while (left <= mid) {
            temp.add(arr[left]);
            left++;
        }

        //  if elements on the right half are still left //
        while (right <= high) {
            temp.add(arr[right]);
            right++;
        }

        // transfering all elements from temporary to arr //
        for (int i = low; i <= high; i++) {
            arr[i] = temp.get(i - low);
        }
        return cnt; // Modification 3
    }

    public static int mergeSort(int[] arr, int low, int high) {
        int cnt = 0;
        if (low >= high) return cnt;
        int mid = (low + high) / 2 ;
        cnt += mergeSort(arr, low, mid);  // left half
        cnt += mergeSort(arr, mid + 1, high); // right half
        cnt += merge(arr, low, mid, high);  // merging sorted halves
        return cnt;
    }

    public static int numberOfInversions(int[] a, int n) {
        // Count the number of pairs:
        return mergeSort(a, 0, n - 1);
    }


    public static void main(String[] args) {
        int[] a = {5, 4, 3, 2, 1};
        int n = 5;
        int cnt = numberOfInversions(a, n);
        System.out.println("The number of inversions are: " + cnt);
    }
}
