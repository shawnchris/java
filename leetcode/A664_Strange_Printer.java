package leetcode;

public class A664_Strange_Printer {
    // We can further divide the substring to two parts: start -> start+k and start+k+1 -> start+len.
    // It is something as following:
    // index |start  ...  start + k| |start + k + 1 ... start + len|
    // char  |  a    ...       b   | |      c       ...      b     |
    // If we have s.charAt(start + k) == s.charAt(start + len), we can reduce one turn.
    public int strangePrinter(String s) {
        if (s.length() == 0) {
            return 0;
        }
        int n = s.length();
        int[][] dp = new int[n][n];

        for (int i = 0; i < n; i++) {
            dp[i][i] = 1;
        }
        for (int len = 1; len < n; len++) {
            for (int left = 0; left < n - len; left++) {
                int right = left + len;
                dp[left][right] = len + 1;
                for (int mid = left; mid < right; mid++) {
                    int steps = dp[left][mid] + dp[mid + 1][right];
                    if (s.charAt(mid) == s.charAt(right)) {
                        steps--;
                    }
                    dp[left][right] = Math.min(steps, dp[left][right]);
                }
            }
        }
        return dp[0][n - 1];
    }
}
