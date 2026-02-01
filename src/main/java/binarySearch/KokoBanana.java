package binarySearch;

/*
Koko loves to eat bananas. There are n piles of bananas, the ith pile has piles[i] bananas. The guards have gone and will come back in h hours.

Koko can decide her bananas-per-hour eating speed of k. Each hour, she chooses some pile of bananas and eats k bananas from that pile. If the pile has less
than k bananas, she eats all of them instead and will not eat any more bananas during this hour.

Koko likes to eat slowly but still wants to finish eating all the bananas before the guards return.

Return the minimum integer k such that she can eat all the bananas within h hours.



Example 1:

Input: piles = [3,6,7,11], h = 8
Output: 4
Example 2:

Input: piles = [30,11,23,4,20], h = 5
Output: 30
Example 3:

Input: piles = [30,11,23,4,20], h = 6
Output: 23

 Explanation:

Example 1 (piles = [3,6,7,11], h = 8)
- k = 4 is minimal because hours = ceil(3/4)+ceil(6/4)+ceil(7/4)+ceil(11/4) = 1+2+2+3 = 8.
- k = 3 fails because hours = 1+2+3+4 = 10 > 8.

```text
k=4 -> 1 + 2 + 2 + 3 = 8 (OK)
k=3 -> 1 + 2 + 3 + 4 = 10 (too many)
```

Example 2 (piles = [30,11,23,4,20], h = 5)
- h equals the number of piles, so Koko must finish one whole pile each hour; the minimum k is the largest pile size.
- minimum k = 30.

```text
k=30 -> ceil(30/30)+ceil(11/30)+ceil(23/30)+ceil(4/30)+ceil(20/30) = 1+1+1+1+1 = 5
```

Example 3 (same piles, h = 6)
- k = 23 works: hours = 2 + 1 + 1 + 1 + 1 = 6.
- k = 22 fails: hours = 2 + 1 + 2 + 1 + 1 = 7 > 6.
- therefore minimum k = 23.

```text
k=23 -> 2 + 1 + 1 + 1 + 1 = 6 (OK)
k=22 -> 2 + 1 + 2 + 1 + 1 = 7 (too many)
```


 */

public class KokoBanana {
    static int maxVal(int[] piles){
        int ans=Integer.MIN_VALUE;
        for (int pile : piles) {
            ans = Math.max(ans, pile);
        }
        return ans;
    }
    static int totalHours(int[] arr, int mid){
        int total=0;
        for(int i=0;i<arr.length;i++){
            total+=Math.ceil((double)arr[i]/(double)mid);
        }
        return (int)total;
    }
    public int minEatingSpeed(int[] piles, int h) {
        int left=1;
        int right=maxVal(piles);
        int ans=Integer.MAX_VALUE;
        while(left<=right){
            int mid=(left+right)/2;
            int total=totalHours(piles,mid);

            if(total<=h){
                ans=mid;
                right=mid-1;
            }else{
                left=mid+1;
            }
        }
        return ans;
    }
}
