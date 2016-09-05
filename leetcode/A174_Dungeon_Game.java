package leetcode;

public class A174_Dungeon_Game {
    public int calculateMinimumHP(int[][] dungeon) {
        int M = dungeon.length;
        int N = dungeon[0].length;
        
        // Stands for the min health before the Knight enters a room. Health must >= 1.
        int[][] health = new int[M][N];
        
        // Init bottom-right cell.
        if (dungeon[M - 1][N - 1] < 0)
            health[M - 1][N - 1] = 1 - dungeon[M - 1][N - 1];
        else
            health[M - 1][N - 1] = 1;
        // Init bottom row and right column
        for (int n = N - 2; n >= 0; n--) {
            int h = health[M - 1][n + 1] - dungeon[M - 1][n];
            health[M-1][n] = (h > 0 ? h : 1);
        }
        for (int m = M - 2; m >= 0; m--) {
            int h = health[m + 1][N - 1] - dungeon[m][N - 1];
            health[m][N - 1] = (h > 0 ? h : 1);
        }
        
        for (int m = M - 2; m >= 0; m--) {
            for (int n = N - 2; n >= 0; n--) {
                int h = Math.min(health[m + 1][n], health[m][n + 1]) - dungeon[m][n];
                health[m][n] = (h > 0 ? h : 1);
            }
        }
        
        return health[0][0];
    }
}
