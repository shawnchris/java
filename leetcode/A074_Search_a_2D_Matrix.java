package leetcode;

public class A074_Search_a_2D_Matrix {
	
	public boolean searchMatrix(int[][] matrix, int target) {
        if (matrix == null) return false;
        int m = matrix.length;
        if (m == 0) return false;
        int n = matrix[0].length;
        if (n == 0) return false;
        if (matrix[0][0] > target) return false;
        if (matrix[m - 1][n - 1] < target) return false;
        
        int start = 0, end = m * n - 1, mid = 0;
        while (start <= end) {
            mid = start + (end - start) / 2;
            if (matrix[mid / n][mid % n] == target)
                return true;
            if (matrix[mid / n][mid % n] < target)
                start = mid + 1;
            else
                end = mid - 1;
        }
        return false;
    }
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
