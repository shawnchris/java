package leetcode;
import java.util.*;

public class A542_01_Matrix {
    public List<List<Integer>> updateMatrix(List<List<Integer>> matrix) {
        int m = matrix.size();
        int n = matrix.get(0).size();
        
        Queue<int[]> queue = new LinkedList<>();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix.get(i).get(j) == 0) {
                    queue.offer(new int[] {i, j});
                }
                else {
                    matrix.get(i).set(j, Integer.MAX_VALUE);
                }
            }
        }
        
        int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        
        while (!queue.isEmpty()) {
            int[] cell = queue.poll();
            for (int[] d : dirs) {
                int r = cell[0] + d[0];
                int c = cell[1] + d[1];
                if (r < 0 || r >= m || c < 0 || c >= n || 
                    matrix.get(r).get(c) <= matrix.get(cell[0]).get(cell[1]) + 1) continue;
                queue.add(new int[] {r, c});
                matrix.get(r).set(c, matrix.get(cell[0]).get(cell[1]) + 1);
            }
        }
        
        return matrix;
    }
}
