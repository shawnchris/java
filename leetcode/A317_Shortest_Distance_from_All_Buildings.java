package leetcode;
import java.util.*;
public class A317_Shortest_Distance_from_All_Buildings {
	static int[] dr = {0, 0, -1, 1};
    static int[] dc = {-1, 1, 0, 0};
    
    public int shortestDistance(int[][] grid) {
        if (grid == null) return -1;
        int m = grid.length;
        if (m == 0) return -1;
        int n = grid[0].length;
        if (n == 0) return -1;
        
        int[][] distance = new int[m][n];
        int[][] reach = new int[m][n];
        int numBuilding = 0, result = Integer.MAX_VALUE;
        
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) {
                    numBuilding++;
                    bfs(grid, distance, reach, i, j);
                }
            }
        }
        
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (reach[i][j] == numBuilding) {
                    result = Math.min(result, distance[i][j]);
                }
            }
        }
        
        if (result == Integer.MAX_VALUE) return -1;
        return result;
    }
    
    private void bfs(int[][] grid, int[][] distance, int[][] reach, int row, int col) {
        Set<Long> visited = new HashSet<>();
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[] {row, col, 0});
        visited.add(((long)row << 31) | col);
        
        while (!queue.isEmpty()) {
            int[] p = queue.poll();
            int r1 = p[0];
            int c1 = p[1];
            for (int i = 0; i < 4; i++) {
                int c2 = c1 + dc[i];
                int r2 = r1 + dr[i];
                if (c2 < 0 || c2 >= grid[0].length || r2 < 0 || r2 >= grid.length
                    || grid[r2][c2] != 0) continue;
                long q = ((long)r2 << 31) | c2;
                if (visited.contains(q)) continue;
                
                reach[r2][c2]++;
                distance[r2][c2] += p[2] + 1;
                
                queue.add(new int[] {r2, c2, p[2] + 1});
                visited.add(q);
            }
        }
    }
    
    public static void main(String[] args) {
    	int[][] grid1 = {{1,0,2,0,1},
    			{0,0,0,0,0},
    			{0,0,1,0,0}};
    	
    	A317_Shortest_Distance_from_All_Buildings sd = new A317_Shortest_Distance_from_All_Buildings();
    	System.out.println(sd.shortestDistance(grid1));
    }
}
