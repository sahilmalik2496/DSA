package binarySearch;

/*

Problem Statement: Given two numbers N and M, find the Nth root of M. The nth root of a number M is defined as a number X when raised to the power N equals M. If the 'nth root is not an integer, return -1.
 */

public class NthRootFinder {

    // Function to check if `mid^n` is equal to `m`, greater, or smaller
    private static int checkPower(int mid, int n, int m) {
        long ans = 1;
        for (int i = 1; i <= n; i++) {
            ans *= mid;
            if (ans > m) return 2; // Exceeded m
        }
        return ans == m ? 1 : 0; // 1 if found, 0 if less
    }

    public static int findNthRoot(int n, int m) {
        int low = 1, high = m;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            int result = checkPower(mid, n, m);

            if (result == 1) return mid; // Found exact root
            else if (result == 0) low = mid + 1; // Need larger root
            else high = mid - 1; // Need smaller root
        }
        return -1; // No integer root found
    }

    public static void main(String[] args) {
        int n = 3, m = 27;
        System.out.println("The answer is: " + findNthRoot(n, m));
    }
}
