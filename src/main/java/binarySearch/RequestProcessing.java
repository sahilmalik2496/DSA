package binarySearch;

/*
There are n services numbered from 1 to n in a system, and there are m requests to be processed. The service in which the
ith request is cached is denoted by cache[i] for all 1 <= i <= m. For any request, if it is processed from the cache,
then it takes 1 unit of time, otherwise 2 units of time. Different services can process different requests simultaneously,
but one service can only process one request at a time. Find the minimum time to process all the requests by allocating each
request to a service.
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

