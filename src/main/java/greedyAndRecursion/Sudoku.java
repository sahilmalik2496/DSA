package greedyAndRecursion;

import java.util.*;

/*
Write a program to solve a Sudoku puzzle by filling the empty cells.

A sudoku solution must satisfy all of the following rules:

Each of the digits 1-9 must occur exactly once in each row.
Each of the digits 1-9 must occur exactly once in each column.
Each of the digits 1-9 must occur exactly once in each of the 9 3x3 sub-boxes of the grid.
The '.' character indicates empty cells.



*/

class Sudoku {

    boolean isValid(char[][] board, int row, int col, char c) {
        for(int i=0; i< 9; i++) {
            if(board[i][col] == c){
                return false;
            }
            if(board[row][i] == c) {
                return false;
            }
            if(board[3*(row/3) + (i/3)][3*(col/3)+ (i%3)] == c) {
                return false;
            }
        }

            return true;
    }
    boolean solve(char[][] board) {
        for(int i=0; i< board.length; i++) {
            for(int j=0; j< board[0].length; j++) {
                if(board[i][j] == '.') {
                    for(char c='1' ; c<='9'; c++) {
                       if(isValid(board, i, j, c)) {
                            board[i][j] = c;
                            if(solve(board) == true) {
                                return true;
                            }
                            else{
                                board[i][j] = '.';
                            }
                       }
                    }

                       return false;
                }
            }
        }
        return true;
    }

    public void solveSudoku(char[][] board) {
        solve(board);
    }
}
