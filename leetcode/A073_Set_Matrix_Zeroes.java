package leetcode;

public class A073_Set_Matrix_Zeroes {
    private void markUpLeft(int[][] matrix, int y, int x) {
        matrix[0][x] = 0;
        matrix[y][0] = 0;
    }
    private void fillRow(int[][] matrix, int y, int colcount) {
        for (int i = 0; i < colcount; i++)
            matrix[y][i] = 0;
    }
    private void fillCol(int[][] matrix, int x, int rowcount) {
        for (int i = 0; i < rowcount; i++)
            matrix[i][x] = 0;
    }
    
    public void setZeroes(int[][] matrix) {
        if (matrix == null) return;
        int m = matrix.length;
        if (m == 0) return;
        int n = matrix[0].length;
        if (n == 0) return;
        
        boolean flagTop = false, flagLeft = false;
        if (matrix[0][0] == 0) {
            flagTop = true;
            flagLeft = true;
        }
        else {
            for (int i = 1; i < m; i++)
                if (matrix[i][0] == 0) {
                    flagLeft = true;
                    break;
                }
            for (int i = 1; i < n; i++)
                if (matrix[0][i] == 0) {
                    flagTop = true;
                    break;
                }
        }
            
        for (int i = 1; i < m; i++)
            for (int j = 1; j < n; j++)
                if (matrix[i][j] == 0)
                    markUpLeft(matrix, i, j);
        
        for (int i = 1; i < m; i++)
            if (matrix[i][0] == 0)
                fillRow(matrix, i, n);
        for (int i = 1; i < n; i++)
            if (matrix[0][i] == 0)
                fillCol(matrix, i, m);
        
        if (flagTop)
            fillRow(matrix, 0, n);
        if (flagLeft)
            fillCol(matrix, 0, m);
    }
}
