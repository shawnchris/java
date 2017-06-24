package leetcode;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class A562_Longest_Line_of_Consecutive_One_in_Matrix {
    public int longestLine(int[][] M) {
        int m = M.length;
        if (m <= 0) return 0;
        int n = M[0].length;
        if (n <= 0) return 0;

        int max = 0;
        int[][] dirs = {{0, 1}, {1, 0}, {1, 1}, {1, -1}};
        List<Set<String>> memo = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            memo.add(new HashSet<String>());
        }

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (M[i][j] == 0) continue;
                String pos = i + "," + j;
                for (int k = 0; k < 4; k++) {
                    if (!memo.get(k).contains(pos)) {
                        int count = 0;
                        for (int r = i, c = j; r < m && r >= 0 && c < n && c >= 0; r += dirs[k][0], c += dirs[k][1]) {
                            if (M[r][c] == 1) {
                                count++;
                                memo.get(k).add(r + "," + c);
                            }
                            else break;
                        }
                        max = Math.max(max, count);
                    }
                }
            }
        }

        return max;
    }
}
