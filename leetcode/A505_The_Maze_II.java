package leetcode;
import java.util.*;

public class A505_The_Maze_II {
    public int shortestDistance(int[][] maze, int[] start, int[] dest) {
        int m = maze.length, n = maze[0].length;
        int[][] dist = new int[m][n];
        for (int[] row : dist) Arrays.fill(row, Integer.MAX_VALUE);
        dist[start[0]][start[1]] = 0;
        
        PriorityQueue<int[]> queue = new PriorityQueue<>((a, b) -> (a[2] - b[2]));
        queue.add(new int[] {start[0], start[1], 0});
        
        int[][] dirs = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
        
        while (!queue.isEmpty()) {
            int[] p = queue.poll();
            // System.out.println(p[0] + " " + p[1]);
            for (int i = 0; i < 4; i++) {
                int r = p[0], c = p[1], d = p[2];
                while (r >= 0 && r < m && c >= 0 && c < n && maze[r][c] != 1) {
                    // if (r == dest[0] && c == dest[1]) return true;
                    r += dirs[i][0];
                    c += dirs[i][1];
                    d++;
                }
                r -= dirs[i][0];
                c -= dirs[i][1];
                d--;
                if (dist[r][c] <= d) continue;
                
                if (r == dest[0] && c == dest[1]) return d;
                dist[r][c] = d;
                queue.add(new int[] {r, c, d});
            }
        }
        
        return -1;
    }
}
