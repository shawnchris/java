package leetcode;
import java.util.*;

public class A407_Trapping_Rain_Water_II {
    class Cell {
        int x, y, h;
        public Cell(int x, int y, int height) {
            this.x = x;
            this.y = y;
            this.h = height;
        }
    }
    public int trapRainWater(int[][] heights) {
        if (heights == null || heights.length == 0 || heights[0].length == 0) {
            return 0;
        }

        PriorityQueue<Cell> queue = new PriorityQueue<Cell>(1, new Comparator<Cell>(){
            public int compare(Cell A, Cell B) {
                return A.h - B.h;
            }
        });
        int n = heights.length;
        int m = heights[0].length;
        boolean[][] visited = new boolean[n][m];

        //LEFT-RIGHT
        for (int i = 0; i < n; i++) {
            visited[i][0] = true;
            visited[i][m - 1] = true;
            queue.offer(new Cell(i, 0, heights[i][0]));
            queue.offer(new Cell(i, m - 1, heights[i][m - 1]));
        }
        //TOP-BOTTOM
        for (int i = 0; i < m; i++) {
            visited[0][i] = true;
            visited[n - 1][i] = true;
            queue.offer(new Cell(0, i, heights[0][i]));
            queue.offer(new Cell(n - 1, i, heights[n - 1][i]));
        }

        int[] xs = {0,  0, 1, -1};
        int[] ys = {1, -1, 0,  0};
        int sum = 0;
        while (!queue.isEmpty()) {
            Cell cell = queue.poll();
            for (int i = 0; i < 4; i++) {
                int nx = cell.x + xs[i];
                int ny = cell.y + ys[i];
                if (nx >= 0 && nx < n && ny >= 0 && ny < m && !visited[nx][ny]) {
                    visited[nx][ny] = true;
                    sum += Math.max(0, cell.h - heights[nx][ny]);
                    if (cell.h - heights[nx][ny] > 0) {
                    	System.out.println("From: " + cell.x + "," + cell.y + " to: " + nx + "," + ny + " sum: " + sum);
                    }
                    queue.offer(new Cell(nx, ny, Math.max(heights[nx][ny], cell.h)));
                }
            }
        }//end while
        
        return sum;

    }
    
    public static void main(String[] args) {
    	A407_Trapping_Rain_Water_II trw = new A407_Trapping_Rain_Water_II();
    	int[][] heights = {
           {1,4,3,1,3,2},
           {3,2,1,3,2,4},
           {2,3,3,2,3,1}
    	};
    	System.out.println(trw.trapRainWater(heights));
    }
}
