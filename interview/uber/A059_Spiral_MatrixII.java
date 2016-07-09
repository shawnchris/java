package interview.uber;

public class A059_Spiral_MatrixII {
	public int[][] generateMatrix(int n) {
        if (n <= 0) return new int[0][0];
        
        int[][] res = new int[n][n];
        int top = 0, bottom = n - 1, left = 0, right = n - 1;
        int num = 1;
        while (true) {
            for (int j = left; j <= right; j++) {
                res[top][j] = num;
                num++;
            }
            top++;
            if (bottom < top) break;
            
            for (int i = top; i <= bottom; i++) {
                res[i][right] = num;
                num++;
            }
            right--;
            if (right < left) break;
            
            for (int j = right; j >= left; j--) {
                res[bottom][j] = num;
                num++;
            }
            bottom--;
            if (bottom < top) break;
            
            for (int i = bottom; i >= top; i--) {
                res[i][left] = num;
                num++;
            }
            left++;
            if (right < left) break;
        }
        
        return res;
    }
}
