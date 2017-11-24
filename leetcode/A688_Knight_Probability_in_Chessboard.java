package leetcode;

public class A688_Knight_Probability_in_Chessboard {
    public double knightProbability(int N, int K, int sr, int sc) {
        int[] dr = new int[]{-1, -1, 1, 1, -2, -2, 2, 2};
        int[] dc = new int[]{2, -2, 2, -2, 1, -1, 1, -1};

        int[] index = new int[N * N];
        int t = 0;
        for (int r = 0; r < N; r++) {
            for (int c = 0; c < N; c++) {
                if (r * N + c == canonical(r, c, N)) {
                    index[r * N + c] = t;
                    t++;
                } else {
                    index[r * N + c] = index[canonical(r, c, N)];
                }
            }
        }

        double[][] T = new double[t][t];
        int curRow = 0;
        for (int r = 0; r < N; r++) {
            for (int c = 0; c < N; c++) {
                if (r * N + c == canonical(r, c, N)) {
                    for (int k = 0; k < 8; k++) {
                        int cr = r + dr[k], cc = c + dc[k];
                        if (0 <= cr && cr < N && 0 <= cc && cc < N) {
                            T[curRow][index[canonical(cr, cc, N)]] += 0.125;
                        }
                    }
                    curRow++;
                }
            }
        }

        double[] row = matrixExpo(T, K)[index[sr*N + sc]];
        double ans = 0.0;
        for (double x: row) ans += x;
        return ans;
    }

    public int canonical(int r, int c, int N) {
        if (2*r > N) r = N-1-r;
        if (2*c > N) c = N-1-c;
        if (r > c) {
            int t = r;
            r = c;
            c = t;
        }
        return r * N + c;
    }
    public double[][] matrixMult(double[][] A, double[][] B) {
        double[][] ans = new double[A.length][A.length];
        for (int i = 0; i < A.length; i++) {
            for (int j = 0; j < B[0].length; j++) {
                for (int k = 0; k < B.length; k++) {
                    ans[i][j] += A[i][k] * B[k][j];
                }
            }
        }
        return ans;
    }
    public double[][] matrixExpo(double[][] A, int pow) {
        double[][] ans = new double[A.length][A.length];
        for (int i = 0; i < A.length; i++) ans[i][i] = 1;
        if (pow == 0) return ans;
        if (pow == 1) return A;
        if (pow % 2 == 1) return matrixMult(matrixExpo(A, pow-1), A);
        double[][] B = matrixExpo(A, pow / 2);
        return matrixMult(B, B);
    }
}
