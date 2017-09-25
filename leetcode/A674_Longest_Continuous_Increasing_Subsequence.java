package leetcode;

public class A674_Longest_Continuous_Increasing_Subsequence {
    public int findLengthOfLCIS(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        int n = nums.length;
        int[] dp = new int[n];

        int max = 1;
        dp[0] = 1;
        for (int i = 1; i < n; i++) {
            if (nums[i] > nums[i - 1]) {
                dp[i] = dp[i - 1] + 1;
            }
            else {
                dp[i] = 1;
            }
            max = Math.max(max, dp[i]);
        }

        return max;
    }
}
