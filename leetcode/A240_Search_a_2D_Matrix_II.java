package leetcode;

public class A240_Search_a_2D_Matrix_II {
    // O(M+N) solution: Every time the upper-right element is the special one: 
	// all elements less than it are at its left side and all elements greater than it are behind.
    public boolean searchMatrix(int[][] matrix, int target) {
		if (matrix == null)
			return false;
		int M = matrix.length;
		int N = matrix[0].length;
		if (M == 0  || N == 0)
			return false;
	    
		int m = 0, n = N - 1;
	    while (m < M && n >= 0) {
	        if (matrix[m][n] == target)
	            return true;
	        else if (matrix[m][n] > target) {
	            n--;
	        } else 
	            m++;
	    }
	    return false;
	}
}
