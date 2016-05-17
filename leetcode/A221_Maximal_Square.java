package leetcode;

public class A221_Maximal_Square {
    public int maximalSquare(char[][] matrix) {
        int Y = matrix.length;
        if (Y == 0) return 0;
        int X = matrix[0].length;
        
        // dp(i, j) represents the length of the square whose lower-right
        // corner is located at (i, j)
        // dp(i, j) = min{ dp(i-1, j-1), dp(i-1, j), dp(i, j-1) } + 1
        int[][] dp = new int[Y][X];
        int max = 0;
        
        for (int i = 0; i < Y; i++) {
            dp[i][0] = matrix[i][0] - '0';
            if (dp[i][0] == 1) max = 1;
        }
        for (int i = 1; i < X; i++) {
            dp[0][i] = matrix[0][i] - '0';
            if (dp[0][1] == 1) max = 1;
        }
        
        for (int y = 1; y < Y; y++) {
            for (int x = 1; x < X; x++) {
                if (matrix[y][x] == '1') {
                    dp[y][x] = Math.min(dp[y-1][x-1], Math.min(dp[y-1][x], dp[y][x-1])) + 1;
                    max = Math.max(max, dp[y][x]);
                }
                else
                    dp[y][x] = 0;
            }
        }
        
        return max * max;
    }
}
