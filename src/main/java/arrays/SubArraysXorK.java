package arrays;

import java.util.HashMap;
import java.util.Map;

/*

https://takeuforward.org/data-structure/count-the-number-of-subarrays-with-given-xor-k/


 Count the number of subarrays with given xor K
Problem Statement: Given an array of integers A and an integer B. Find the total number of subarrays having
bitwise XOR of all elements equal to k.

The problem is to count the number of subarrays in an array
a that have a XOR value equal to
k. The XOR operation has certain properties that make this problem efficient to solve using a prefix XOR
and a hashmap.
Key Properties of XOR:

A⊕A=0 (XOR of a number with itself is 0).
A⊕0=A (XOR of a number with 0 is the number itself).

(A⊕B)⊕C=A⊕(B⊕C) (Associative property).

Using these properties, we can derive that for any subarray from index i to j:
XOR(subarray) = XOR(prefix up to j) * XOR(prefix up to i-1).
Let er be the XOR of the prefix up to j:
If XOR(subarray) = k, then XOR(prefix up to i-1) = ar * k.
This means we need to find how many times or © k has appeared as a prefix XOR before index j.
 */
public class SubArraysXorK {
    public static int subarraysWithXorK(int []a, int k) {
        int n = a.length; //size of the given array.
        int xr = 0;
        Map<Integer, Integer> mpp = new HashMap<>(); //declaring the map.
        mpp.put(xr, 1); //setting the value of 0.
        int cnt = 0;

        for (int i = 0; i < n; i++) {
            // prefix XOR till index i:
            xr = xr ^ a[i];

            //By formula: x = xr^k:
            int x = xr ^ k;

            // add the occurrence of xr^k
            // to the count:
            if (mpp.containsKey(x)) {
                cnt += mpp.get(x);
            }

            // Insert the prefix xor till index i
            // into the map:
            if (mpp.containsKey(xr)) {
                mpp.put(xr, mpp.get(xr) + 1);
            } else {
                mpp.put(xr, 1);
            }
        }
        return cnt;
    }

    public static void main(String[] args) {
        int[] a = {4, 2, 2, 6, 4};
        int k = 6;
        int ans = subarraysWithXorK(a, k);
        System.out.println("The number of subarrays with XOR k is: " + ans);
    }
}
