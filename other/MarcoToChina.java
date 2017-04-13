package other;

import java.util.Arrays;

public class MarcoToChina {

	public static int findMax(int[][] matrix) {
		int result = 0;
		int m = matrix.length;
	    int n = matrix[0].length;
	    char[][] route = new char[m][n];
	    int[][] dp = new int[m][n];
	    
	    // Step 1, travel from upper-left to bottom-right
	    // Init
	    for (int j = 1; j < n; j++) {
	        dp[0][j] = dp[0][j - 1] + matrix[0][j];
	        route[0][j] = 'l';
	    }
	    for (int i = 1; i < m; i++) {
	        dp[i][0] = dp[i - 1][0] + matrix[i][0];
	        route[i][0] = 'u';
	    }
	    // DP
	    for (int i = 1; i < m; i++) {
	        for (int j = 1; j < n; j++) {
	            if (dp[i - 1][j] > dp[i][j - 1]) {
	                dp[i][j] = dp[i - 1][j] + matrix[i][j];
	                route[i][j] = 'u';
	            }
	            else {
	                dp[i][j] = dp[i][j - 1] + matrix[i][j];
	                route[i][j] = 'l';
	            }
	        }
	    }
	    result = dp[m - 1][n - 1];
	    
	    // Step 2, mark cell in the above path as -INF.
	    int row = m - 1, col = n - 1;
	    while (row != 0 || col != 0) {
	    	if (route[row][col] == 'u') {
	    		row--;
	    	}
	    	else {
	    		col--;
	    	}
	    	if (row != 0 || col != 0) {
	    		matrix[row][col] = Integer.MIN_VALUE;
	    	}
	    }
	    
	    // Step 3, travel from bottom-right to upper-left
	    // Init
	    for (int i = 0; i < m; i++) {
	    	Arrays.fill(dp[i], 0);
	    }
	    for (int j = n - 2; j >= 0; j--) {
	        dp[m - 1][j] = dp[m - 1][j + 1] + matrix[m - 1][j];
	    }
	    for (int i = m - 2; i >= 0; i--) {
	        dp[i][n - 1] = dp[i + 1][n - 1] + matrix[i][n - 1];
	    }
	    // DP
	    for (int i = m - 2; i >= 0; i--) {
	        for (int j = n - 2; j >= 0; j--) {
	            if (dp[i + 1][j] > dp[i][j + 1]) {
	                dp[i][j] = dp[i + 1][j] + matrix[i][j];
	            }
	            else {
	                dp[i][j] = dp[i][j + 1] + matrix[i][j];
	            }
	        }
	    }
	    result += dp[0][0];
	    
	    return result;
	}
	
	public static void main(String[] args) {
		int[][] test1 = {
				{0, 3, 9},
				{2, 8, 5},
				{5, 7, 0}};
		System.out.println(findMax(test1));
	}

}
