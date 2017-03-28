package interview.amazon;
import java.util.*;

// Phone interview:
// Word Break I + return one solution

// Onsite interview:
// Yiwei: 124 Binary Tree Maximum Path Sum
// Sumeet: Data Structure for getMin/Max of a window of data stream.
// 		Use Binary Search Tree. Implement getMin/Max method.
// Bhargava: OOD for Badminton. 238	Product of Array Except Self.
// Matt: System Design of TinyURL.

public class Solution {
	private void print(char[][] route, int row, int col) {
	    if (row != 0 || col != 0) {
	        if (route[row][col] == 'u') {
	            print(route, row - 1, col);
	        }
	        else {
	            print(route, row, col - 1);
	        }
	    }
	    System.out.println("row = " + row + ", col = " + col);
	}

	public void findMaxBandwidth(int[][] matrix) {
	    int m = matrix.length;
	    int n = matrix[0].length;
	    char[][] route = new char[m][n];
	    
	    //init
	    for (int j = 1; j < n; j++) {
	        matrix[0][j] = Math.min(matrix[0][j - 1], matrix[0][j]);
	        route[0][j] = 'l';
	    }
	    for (int i = 1; i < m; i++) {
	        matrix[i][0] = Math.min(matrix[i - 1][0], matrix[i][0]);
	        route[i][0] = 'u';
	    }
	    
	    //traverse
	    for (int i = 1; i < m; i++) {
	        for (int j = 1; j < n; j++) {
	            if (matrix[i - 1][j] > matrix[i][j - 1]) {
	                matrix[i][j] = Math.min(matrix[i - 1][j], matrix[i][j]);
	                route[i][j] = 'u';
	            }
	            else {
	                matrix[i][j] = Math.min(matrix[i][j - 1], matrix[i][j]);
	                route[i][j] = 'l';
	            }
	        }
	    }
	    
	    print(route, m - 1, n - 1);
	}
	
	public static void main(String[] args) {
		Solution s = new Solution();
		int[][] matrix = {
				{5, 6, 3},
				{5, 2, 3},
				{4, 2, 7},
		};
		s.findMaxBandwidth(matrix);
	}

}
