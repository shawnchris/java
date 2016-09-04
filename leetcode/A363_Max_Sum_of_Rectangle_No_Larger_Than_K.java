package leetcode;
import java.util.*;

public class A363_Max_Sum_of_Rectangle_No_Larger_Than_K {
    public static int maxSumSubmatrix(int[][] matrix, int cap) {
        if (matrix == null) return 0;
        int rows = matrix.length;
        if (rows == 0) return 0;
        int cols = matrix[0].length;
        if (cols == 0) return 0;
        
        int m = 0, n = 0, result = Integer.MIN_VALUE;
        boolean colIsBig = false;
        if (cols > rows) {
            m = rows;
            n = cols;
            colIsBig = true;
        }
        else {
            n = rows;
            m = cols;
        }
        
        for (int i = 0; i < m; i++) {
            int[] row = new int[n];
            for (int j = i; j < m; j++) {
                int sum = 0;
                TreeSet<Integer> preSum = new TreeSet<>();
                preSum.add(0);
                for (int k = 0; k < n; k++) {
                    row[k] += (colIsBig ? matrix[j][k] : matrix[k][j]);
                    sum += row[k];
                    Integer pre = preSum.ceiling(sum - cap);
                    if (pre != null) {
                        result = Math.max(result, sum - pre);
                    }
                    preSum.add(sum);
                }
            }
        }
        
        return result;
    }
	public static void main(String[] args) {
		int[][] matrix = {{1,0,1},{0,-2,3}};
		System.out.println(maxSumSubmatrix(matrix, 2));

	}

}
