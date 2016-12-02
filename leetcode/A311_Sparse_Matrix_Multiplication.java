package leetcode;

public class A311_Sparse_Matrix_Multiplication {
	public int[][] multiply2(int[][] A, int[][] B) {
        int m = A.length, n = A[0].length, nB = B[0].length;
        int[][] C = new int[m][nB];

        for(int i = 0; i < m; i++) {
            for(int k = 0; k < n; k++) {
                if (A[i][k] != 0) {
                    for (int j = 0; j < nB; j++) {
                        if (B[k][j] != 0) {
                        	C[i][j] += A[i][k] * B[k][j];
                        }
                    }
                }
            }
        }
        return C;
    }
	
    public int[][] multiply(int[][] A, int[][] B) {
        int X = A.length, Y = A[0].length, Z = B[0].length;
        int[][] res = new int[X][Z];
        
        for (int row = 0; row < X; row++) {
            for (int col = 0; col < Z; col++) {
                int sum = 0;
                for (int i = 0; i < Y; i++) {
                    sum += A[row][i] * B[i][col];
                }
                res[row][col] = sum;
            }
        }
        
        return res;
    }
}
