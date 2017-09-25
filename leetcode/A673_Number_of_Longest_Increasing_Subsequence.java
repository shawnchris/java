package leetcode;

public class A673_Number_of_Longest_Increasing_Subsequence {
    public int findNumberOfLIS(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        int n = nums.length;

        int[] dp = new int[n];
        int[] next = new int[n];
        dp[n - 1] = 1; next[n - 1] = 1;
        int len = 1;
        for (int i = n - 2; i >= 0; i--) {
            int max = 0, cnt = 0;
            for (int j = i + 1; j < n; j++){
                if (nums[j] > nums[i]){
                    max = Math.max(max, dp[j]);
                }
            }
            dp[i] = max + 1;
            len = Math.max(len, dp[i]);
            if (dp[i] == 1) next[i] = 1;
            else {
                for(int j = i + 1; j < n; ++j){
                    if(nums[j] > nums[i] && dp[j] == max){
                        next[i] += next[j];
                    }
                }
            }
        }

        int res = 0;
        for (int i = 0; i < n; ++i) {
            if (dp[i] == len){
                res += next[i];
            }
        }
        return res;
    }
}
