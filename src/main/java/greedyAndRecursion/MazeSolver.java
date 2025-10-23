package greedyAndRecursion;

/*
Consider a rat placed at (0, 0) in a square matrix of order N * N. It has to reach the destination at (N - 1, N - 1).
 Find all possible paths that the rat can take to reach from source to destination. The directions in which the rat can
  move are 'U'(up), 'D'(down), 'L' (left), 'R' (right). Value 0 at a cell in the matrix represents that it is blocked
   and the rat cannot move to it while value 1 at a cell in the matrix represents that rat can travel through it.

Note: In a path, no cell can be visited more than one time.

Print the answer in lexicographical(sorted) order

Examples:

Example 1:

Input:
N = 4
m[][] = {{1, 0, 0, 0},
        {1, 1, 0, 1}, 
        {1, 1, 0, 0},
        {0, 1, 1, 1}}

Output: DDRDRR DRDDRR


*/


import java.util.ArrayList;

public class MazeSolver {
    private static void solve(int i, int j, int[][] a, int n, ArrayList<String> ans, String move,
                              int[][] vis, int[] di, int[] dj) {
        if (i == n - 1 && j == n - 1) {
            ans.add(move);
            return;
        }
        
        String dir = "DLRU";
        for (int ind = 0; ind < 4; ind++) {
            int nexti = i + di[ind];
            int nextj = j + dj[ind];
            
            if (isValidMove(nexti, nextj, n, vis, a)) {
                vis[i][j] = 1;
                solve(nexti, nextj, a, n, ans, move + dir.charAt(ind), vis, di, dj);
                vis[i][j] = 0;
            }
        }
    }
    
    private static boolean isValidMove(int i, int j, int n, int[][] vis, int[][] a) {
        return i >= 0 && j >= 0 && i < n && j < n && vis[i][j] == 0 && a[i][j] == 1;
    }
    
    public static ArrayList<String> findPath(int[][] m, int n) {
        int[][] vis = new int[n][n];
        
        int[] di = {+1, 0, 0, -1};
        int[] dj = {0, -1, 1, 0};
        
        ArrayList<String> ans = new ArrayList<>();
        if (m[0][0] == 1) {
            solve(0, 0, m, n, ans, "", vis, di, dj);
        }
        
        return ans;
    }
}


