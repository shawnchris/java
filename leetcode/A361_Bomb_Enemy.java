package leetcode;

public class A361_Bomb_Enemy {
    public int maxKilledEnemies(char[][] grid) {
        if (grid == null) return 0;
        int m = grid.length;
        if (m == 0) return 0;
        int n = grid[0].length;
        if (n == 0) return 0;
        
        int[][] dp = new int[m][n];
        for (int i = 0; i < m; i++) {
            count(grid, dp, i, 0, 0, 1);
            count(grid, dp, i, n - 1, 0, -1);
        }
        
        for (int j = 0; j < n; j++) {
            count(grid, dp, 0, j, 1, 0);
            count(grid, dp, m - 1, j, -1, 0);
        }
        
        int max = 0;
        for (int i = 0; i < m; i++) {
           for (int j = 0; j < n; j++) {
               if (grid[i][j] == '0') {
                   max = Math.max(max, dp[i][j]);
               }
           }
        }
        
        return max;
    }
    
    private void count(char[][] grid, int[][] dp, int row, int col, int dr, int dc) {
        int m = grid.length;
        int n = grid[0].length;
        int sum = 0;
        while (row >= 0 && row < m && col >= 0 && col < n) {
            if (grid[row][col] == 'W') {
                sum = 0;
            }
            else if (grid[row][col] == 'E') {
                sum++;
            }
            else if (grid[row][col] == '0') {
                dp[row][col] += sum;
            }
            row += dr;
            col += dc;
        }
    }
}
