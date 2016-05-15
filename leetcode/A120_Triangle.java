package leetcode;
import java.util.*;

public class A120_Triangle {
    public int minimumTotal(List<List<Integer>> triangle) {
        int rowNum = triangle.size();
        int[] dp = new int[rowNum];
        for (int i = 0; i < triangle.get(rowNum - 1).size(); i++) {
            dp[i] = triangle.get(rowNum - 1).get(i);
        }
        for (int row = rowNum - 2; row >= 0; row--) {// for each layer
            for (int col = 0; col <= row; col++) {
                dp[col] = Math.min(dp[col], dp[col + 1])
                    + triangle.get(row).get(col);
            }
        }
        return dp[0];
    }
}
