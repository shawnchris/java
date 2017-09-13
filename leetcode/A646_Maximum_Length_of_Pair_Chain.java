package leetcode;

import java.util.Arrays;

public class A646_Maximum_Length_of_Pair_Chain {
    public int findLongestChain(int[][] pairs) {
        if (pairs == null || pairs.length == 0) return 0;
        int n = pairs.length, max = 1;
        int dp[] = new int[n];

        Arrays.sort(pairs, (a, b) -> (a[0] - b[0]));

        for (int i = 0; i < n; i++) {
            dp[i] = 1;
            for (int j = 0; j < i; j++) {
                if (pairs[i][0] > pairs[j][1]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
            max = Math.max(max, dp[i]);
        }

        return max;
    }
}
