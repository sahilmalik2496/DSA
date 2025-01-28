package arrays;

import java.util.HashMap;

/*
Problem Statement: Given an array containing both positive and negative integers, we have to find the length of the longest subarray with the sum of all elements equal to zeRo

if prefix(0,x) == prefix(0,y) => sum of subarray from (x+1) to (y) is zero . arr[x+1,y]=0
Now if at any point prefix_sum==0 , ie subarry from (0) to (y) is zero => that's why we set first_occ[0]=-1; (coz starting se y point tk is zero array and length of that array will be = y - (-1). here y represents the index at point where prefixsum becomes zero.

1. we first take one hashmap 'first_occ' which will store the first occurance of the prefix sum
2. set first_occ[0]=-1
3. Iterate over the array 3.1) calculate the prefix_sum. 3.2) check if our hashmap 'first_occ' contains prefix_sum or not 3.2.1) If prefix_sum doesnot exist -> set it's first occurance as 'i'. (first_occ[prefix_sum]=i) 3.3) Now calculate the size of zero subarray . int size = i - first_occ[arr[i]]; 3.4) Now update the value of max_size (variable storing the maximum size of subarray having sum=0)
4. return the max_size

3.1) calculate the prefix_sum. 3.2) check if our hashmap 'first_occ' contains prefix_sum or not 3.2.1) If prefix_sum doesnot exist -> set it's first occurance as 'i'. (first_occ[prefix_sum]=i) 3.3) Now calculate the size of zero subarray . int size = i - first_occ[arr[i]]; 3.4) Now update the value of max_size (variable storing the maximum size of subarray having sum=0)

 */
public class ZeroSumSubArray {
    int maxLen(int A[], int n)
    {
        // Your code here
        HashMap<Integer, Integer> mpp = new HashMap<Integer, Integer>();

        int maxi = 0;
        int sum = 0;

        for(int i = 0;i<n;i++) {

            sum += A[i];

            if(sum == 0) {
                maxi = i + 1;
            }
            else {
                if(mpp.get(sum) != null) {

                    maxi = Math.max(maxi, i - mpp.get(sum));
                }
                else {

                    mpp.put(sum, i);
                }
            }
        }
        return maxi;
    }
}
