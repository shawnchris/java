package lintcode;

public class BackPackII {
    public static int backPackII(int m, int[] A, int V[]) {
        if (m == 0 || A == null || A.length == 0 || V == null || V.length == 0)
            return 0;
        
        // dp[i][j] stands for the max value of picking some item from
        // the first i items and the size is j.
        // dp[i][j] = MAX{ dp[i - 1][j], dp[i - 1][j - A[i]] + V[i] }
        int[][] dp = new int[A.length + 1][m + 1];
        
        for (int j = 1; j <= m; j++) {
            dp[0][j] = Integer.MIN_VALUE;
        }
        for (int i = 0; i <= A.length; i++) {
            dp[i][0] = 0;
        }
        
        int max = 0;
        for (int i = 1; i <= A.length; i++) {
            for (int j = 1; j <= m; j++) {
                dp[i][j] = dp[i - 1][j];
                if (j - A[i - 1] >= 0) {
                    dp[i][j] = Math.max(dp[i][j], dp[i - 1][j - A[i - 1]] + V[i - 1]);
                }
                max = Math.max(max, dp[i][j]);
            }
        }
        
        for (int i = 0; i <= A.length; i++) {
            for (int j = 0; j <= m; j++) {
            	if (dp[i][j] >= 0)
            		System.out.print(dp[i][j] + "\t");
            	else
            		System.out.print("--\t");
            }
            System.out.println();
        }
        
        return max;
    }
    
	public static void main(String[] args) {
		System.out.println(
				backPackII(10, new int[] {2, 3, 5, 7}, new int[]{1, 5, 2, 4}));
	}

}
