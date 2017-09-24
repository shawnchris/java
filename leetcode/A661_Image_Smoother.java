package leetcode;

public class A661_Image_Smoother {
    public int[][] imageSmoother(int[][] M) {
        int R = M.length, C = M[0].length;
        int[][] res = new int[R][C];

        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                double sum = 0, count = 0;
                for (int r = -1; r < 2; r++) {
                    for (int c = -1; c < 2; c++) {
                        int nr = i + r, nc = j + c;
                        if (nr < 0 || nr >= R || nc < 0 || nc >= C) continue;
                        sum += M[nr][nc];
                        count += 1;
                    }
                }
                res[i][j] = (int)Math.floor(sum / count);
            }
        }

        return res;
    }
}
