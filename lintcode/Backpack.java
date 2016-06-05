package lintcode;

public class Backpack {
    public static int backPack(int m, int[] A) {
        if (A ==  null || A.length == 0 || m == 0)
            return 0;
        // dp[i][j] stands for if it is possible to
        // pick some from first i items to form size j?
        // dp[i][j] = dp[i - 1][j] || dp[i][j - a[i]]
        boolean[][] dp = new boolean[A.length + 1][m + 1];
        
        // init
        for (int i = 0; i <= A.length; i++) {
            dp[i][0] = true;
        }
        
        int max = 0;
        for (int i = 1; i <= A.length; i++) {
            for (int j = 1; j <= m; j++) {
                dp[i][j] = dp[i - 1][j];
                if (j - A[i - 1] >= 0) {
                    dp[i][j] = dp[i][j] || dp[i - 1][j - A[i - 1]];
                }
                if (dp[i][j]) {
                    max = Math.max(max, j);
                }
            }
        }
        for (int i = 0; i <= A.length; i++) {
            for (int j = 0; j <= m; j++) {
            	if (dp[i][j])
            		System.out.print("Y ");
            	else
            		System.out.print("N ");
            }
            System.out.println();
        }
        return max;
    }
    
    public static void main(String[] args) {
    	System.out.println(backPack(11, new int[]{2, 3, 5, 7}));
    }
}
