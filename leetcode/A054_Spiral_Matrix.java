package leetcode;
import java.util.*;

public class A054_Spiral_Matrix {
	public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> res = new ArrayList<Integer>();
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0)
            return res;
        // declare indices
        int top = 0;
        int bottom = matrix.length - 1;
        int left = 0;
        int right = matrix[0].length - 1;

        while(true) {
            // 1. print top row
            for (int j = left; j <= right; j++) {
                res.add(matrix[top][j]);
            }
            top++;
            if (bottom < top) break;

            // 2. print rightmost column
            for (int i = top; i <= bottom; i++) {
                res.add(matrix[i][right]);
            }
            right--;
            if (left > right) break;
    
            // 3. print bottom row
            for (int j = right; j >= left; j--) {
                res.add(matrix[bottom][j]);
            }
            bottom--;
            if (bottom < top) break;  
    
            // 4. print leftmost column
            for (int i = bottom; i >= top; i--) {
                res.add(matrix[i][left]);
            }
            left++;
            if (left > right) break;   
        }
    
        return res;
    }
}
