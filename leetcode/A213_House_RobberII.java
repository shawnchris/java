package leetcode;

public class A213_House_RobberII {
    public int rob(int[] nums) {
        if (nums == null) return 0;
        int len = nums.length;
        if (len == 0) return 0;
        if (len == 1) return nums[0];
        
        int[] dp = new int[len * 2];
        dp[0] = nums[0];
        dp[1] = Math.max(nums[0], nums[1]);
        for (int i = 2; i < len * 2; i++) {
            dp[i] = Math.max(dp[i-1], nums[i % len] + dp[i-2]);
        }
        
        return dp[len * 2 - 1] - dp[len - 1];
    }
}
