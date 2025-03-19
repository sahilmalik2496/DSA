package binarySearch
/*
Problem Statement: Given two sorted arrays of size m and n respectively, you are tasked with finding the element that would be at the kth position of the final sorted array.
 */ 

  
public class KthElementInSortedArrays {
    public static int kthElement(int[] arr1, int[] arr2, int m, int n, int k) {
        if (m > n) {
            return kthElement(arr2, arr1, n, m, k);
        }

        int low = Math.max(0, k - m);
        int high = Math.min(k, n);

        while (low <= high) {
            int cut1 = (low + high) / 2;
            int cut2 = k - cut1;

            int left1 = (cut1 == 0) ? Integer.MIN_VALUE : arr1[cut1 - 1];
            int left2 = (cut2 == 0) ? Integer.MIN_VALUE : arr2[cut2 - 1];
            int right1 = (cut1 == n) ? Integer.MAX_VALUE : arr1[cut1];
            int right2 = (cut2 == m) ? Integer.MAX_VALUE : arr2[cut2];

            if (left1 <= right2 && left2 <= right1) {
                return Math.max(left1, left2);
            } else if (left1 > right2) {
                high = cut1 - 1;
            } else {
                low = cut1 + 1;
            }
        }

        return -1; // Indicates an invalid case
    }

    public static void main(String[] args) {
        int[] arr1 = {2, 3, 6, 7, 9};
        int[] arr2 = {1, 4, 8, 10};
        int m = arr1.length;
        int n = arr2.length;
        int k = 5;

        System.out.println("The " + k + "th smallest element is: " + kthElement(arr1, arr2, m, n, k));
    }
}
