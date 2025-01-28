package arrays;

/*
You are given an n x n 2D matrix representing an image, rotate the image by 90 degrees (clockwise).
You have to rotate the image in-place, which means you have to modify the input 2D matrix directly. DO NOT allocate another 2D matrix and do the rotation.

Intuition
1. Transpose the Matrix:
    * A transpose operation flips a matrix along its diagonal.
    * Rows become columns and vice versa. This prepares the matrix for the next step of rotation.
2. Reverse Rows:
    * Once transposed, reversing each row mirrors the matrix horizontally, completing the 90-degree clockwise rotation.
The combination of transposing and reversing rows achieves the desired transformation without requiring additional space, making it an in-place algorithm.

 */
public class RotateImage {
    public void rotate(int[][] matrix) {
        int n = matrix.length, m = matrix[0].length;
        for(int i=0; i< n; i++) {
            for(int j=i; j< m; j++) {
                int tmp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = tmp;
            }
        }

        for(int i=0; i< n; i++) {
            for(int j=0; j< m / 2; j++) {
                int t = matrix[i][j];
                matrix[i][j] = matrix[i][m-j-1];
                matrix[i][m-j-1] = t;
            }
        }
    }
}
