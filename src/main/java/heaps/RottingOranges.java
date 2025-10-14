package heaps;

import java.util.LinkedList;
import java.util.Queue;

/*

https://leetcode.com/problems/rotting-oranges/

You are given an m x n grid where each cell can have one of three values:

0 representing an empty cell,
1 representing a fresh orange, or
2 representing a rotten orange.
Every minute, any fresh orange that is 4-directionally adjacent to a rotten orange becomes rotten.

Return the minimum number of minutes that must elapse until no cell has a fresh orange. If this is impossible, return -1.
 */
public class RottingOranges {
        class Pair{
            int i; int j; int k;

            public Pair(int i, int j, int k) {
                this.i = i;
                this.j = j;
                this.k = k;
            }
        }

        public int orangesRotting(int[][] grid) {
            int n = grid.length, m = grid[0].length;

            Queue<Pair> q = new LinkedList<>();

            int[][] vis = new int[n][m];

            int fresh =0;
            for(int i=0; i< n; i++) {
                for(int j=0; j< m; j++) {
                    if (grid[i][j] == 2){
                        q.add(new Pair(i, j, 0));

                        vis[i][j] = 1;
                    } else {
                        vis[i][j] = 0;
                    }

                    if(grid[i][j] == 1) {
                        fresh++;
                    }
                }
            }

            int tm =0;
            int cnt =0;
            int row[] = {1, 0, -1, 0};
            int col[] = {0, 1, 0, -1};


            while(!q.isEmpty()){
                Pair p = q.poll();

                int r = p.i;
                int c = p.j;
                int t = p.k;
                tm = Math.max(tm, t);

                for(int i=0; i< 4; i++) {
                    int rw = r + row[i];
                    int cl = c + col[i];

                    if (rw >= 0 && rw <n && cl >=0 && cl < m && vis[rw][cl] == 0 && grid[rw][cl] == 1) {
                        q.add(new Pair(rw, cl, t+1));
                        cnt++;
                        vis[rw][cl] = 1;
                    }
                }
            }

            if (cnt != fresh){
                return -1;
            }

            return tm;
        }
}
