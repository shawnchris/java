package leetcode;

public class A115_Distinct_Subsequences {
    public int numDistinct(String s, String t) {
        if (s == null || t == null) return -1;
        int lens = s.length(), lent = t.length();
        if (lens == 0) return 0;
        
        // dp[i][j] stands for distinct number of first i chars of s and first j chars of t
        int[][] dp = new int[lens + 1][lent + 1];
        // init dp[i][0] = 1;
        for (int i = 0; i <= lens; i++)
            dp[i][0] = 1;
        // 
        for (int i = 1; i <= lens; i++) {
            for (int j = 1; j <= lent; j++) {
                if (s.charAt(i -1) == t.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + dp[i - 1][j];
                }
                else {
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }
        
        return dp[lens][lent];
    }
}
