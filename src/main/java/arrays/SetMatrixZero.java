package arrays;

/*

https://leetcode.com/problems/set-matrix-zeroes/description/


Given an m x n integer matrix matrix, if an element is 0, set its entire row and column to 0's.

You must do it in place.

Key Idea
1. Mark Rows and Columns: Use the first row and column of the matrix as markers to indicate which rows and columns need to be set to zero.
2. Two-Pass Solution:
    * First pass: Mark rows and columns that should be set to zero.
    * Second pass: Update the matrix based on the markers.

Steps in the Code
1. Check if the First Row Needs to be Zeroed:
    * Use a flag variable to determine if the first row contains any 0.
2. Mark Columns and Rows:
    * Traverse the rest of the matrix. If any cell is 0, set the corresponding cell in the first row and first column to 0 (marking the column and row).
3. Set Marked Rows to Zero:
    * For each row that is marked (except the first row), set all its elements to 0.
4. Set Marked Columns to Zero:
    * For each column that is marked (based on the first row), set all its elements to 0.
5. Zero the First Row:
    * If the flag is set, zero out the first row.


 */
public class SetMatrixZero {
    public void setZeroes(int[][] matrix) {
        int n = matrix.length, m = matrix[0].length;
        int flag = 0;
        for(int i=0; i< m; i++) {
            if(matrix[0][i] == 0) {
                flag = 1;
                break;
            }
        }

        for(int i=1; i< n; i++) {
            boolean f = false;
            for(int j=0; j<m; j++) {
                if(matrix[i][j] == 0) {
                    matrix[0][j] = 0;
                    f = true;
                }
            }

            if (f == true) {
                for(int j=0; j<m; j++) {
                    matrix[i][j] =0;
                }
            }

        }

        for(int j=0; j< m; j++) {
            if (matrix[0][j] == 0) {
                for(int i=1; i< n; i++) {
                    matrix[i][j] = 0;
                }
            }
        }
        if (flag == 1) {
            for(int i=0; i< m; i++) {
                matrix[0][i] =0;
            }
        }
    }
}
