package binarySearch;

/*
There are n services numbered from 1 to n in a system, and there are m requests to be processed. The service in which the
ith request is cached is denoted by cache[i] for all 1 <= i <= m. For any request, if it is processed from the cache,
then it takes 1 unit of time, otherwise 2 units of time. Different services can process different requests simultaneously,
but one service can only process one request at a time. Find the minimum time to process all the requests by allocating each
request to a service.

Given the sample input in `main`:

- `n = 3` (services)
- `requests = {1, 2, 1, 3, 1, 2}`

Step-by-step dry run:

1. **Count requests per service**
   `cache = [3, 2, 1]`
   (Service 1: 3 requests, Service 2: 2 requests, Service 3: 1 request)

2. **Binary search for minimum time**
   Initial: `left = 1`, `right = 12`, `minTime = 12`

   - **mid = 6**
     `isPossible([3,2,1], 6)`
     - leftoverRequests: 0
     - requestsToFill: (6-3)/2=1, (6-2)/2=2, (6-1)/2=2
     - leftoverRequests -= (1+2+2) = -5
     - returns true
     Update: `minTime = 6`, `right = 5`

   - **mid = 3**
     `isPossible([3,2,1], 3)`
     - leftoverRequests: 0
     - requestsToFill: (3-3)/2=0, (3-2)/2=0, (3-1)/2=1
     - leftoverRequests -= (0+0+1) = -1
     - returns true
     Update: `minTime = 3`, `right = 2`

   - **mid = 1**
     `isPossible([3,2,1], 1)`
     - leftoverRequests: (3-1)+(2-1)+(1-1)=2+1+0=3
     - requestsToFill: (1-3)/2=0, (1-2)/2=0, (1-1)/2=0
     - leftoverRequests -= 0
     - returns false
     Update: `left = 2`

   - **mid = 2**
     `isPossible([3,2,1], 2)`
     - leftoverRequests: (3-2)+(2-2)+(1-2)=1+0+0=1
     - requestsToFill: (2-3)/2=0, (2-2)/2=0, (2-1)/2=0
     - leftoverRequests -= 0
     - returns false
     Update: `left = 3`

3. **Result:**
   Minimum processing time is `3`.

This matches the output in `main`.

 */

public class RequestProcessing {

        public static boolean isPossible(int[] cache, int time) {
            int leftoverRequests = 0;

            // Collect requests not processed under the given time
            for (int c : cache) {
                leftoverRequests += Math.max(c - time, 0);
            }

            // Try adding them to servers which have time to process more requests
            for (int c : cache) {
                int requestsToFill = Math.max(time - c, 0) / 2;
                leftoverRequests -= requestsToFill;
            }

            return leftoverRequests <= 0;
        }

        public static int minProcessingTime(int n, int[] requests) {
            int[] cache = new int[n];

            // Count requests per service
            for (int r : requests) {
                cache[r - 1]++;
            }

            // Binary search to find minimum processing time
            int left = 1, right = requests.length * 2, minTime = right;

            while (left <= right) {
                int mid = (left + right) / 2;

                if (isPossible(cache, mid)) {
                    minTime = mid;
                    right = mid - 1; // Try to minimize further
                } else {
                    left = mid + 1;
                }
            }
            return minTime;
        }

        public static void main(String[] args) {
            int n = 3;
            int[] requests = {1, 2, 1, 3, 1, 2}; // Sample request array

            System.out.println("Minimum Processing Time: " + minProcessingTime(n, requests));
        }
    }

