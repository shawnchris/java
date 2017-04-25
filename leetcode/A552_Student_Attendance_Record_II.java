package leetcode;

public class A552_Student_Attendance_Record_II {
    final int MOD = 1000000007;
    final int M = 6;
    
    int[][] mul(int[][] A, int[][] B) {
        int[][] C = new int[M][M];
        for (int i = 0; i < M; i++)
            for (int j = 0; j < M; j++)
                for (int k = 0; k < M; k++)
                    C[i][j] = (int) ((C[i][j] + (long) A[i][k] * B[k][j]) % MOD);
        return C;
    }
    
    
    int[][] pow(int[][] A, int n) {
        int[][] res = new int[M][M];
        for (int i = 0; i < M; i++)
            res[i][i] = 1;
        while (n > 0) {
            if (n % 2 == 1)
                res = mul(res, A);
            A = mul(A, A);
            n /= 2;
        }
        return res;
    }
    
    public int checkRecord(int n) {
        int[][] A = {
                {0, 0, 1, 0, 0, 0},
                {1, 0, 1, 0, 0, 0},
                {0, 1, 1, 0, 0, 0},
                {0, 0, 1, 0, 0, 1},
                {0, 0, 1, 1, 0, 1},
                {0, 0, 1, 0, 1, 1},
        };
        return pow(A, n + 1)[5][2];
    }
}
