package leetcode;

//Dynamic Programming Solution
//dp[i] stands for the max money after robbing house(i).
//Apparently, dp[i] = max(dp[i-1], nums[i] + dp[i-2])
//because whether or not rob it leads to two results: dp[i-1] and nums[i] + dp[i-2]

public class A198_House_Robber {
    public int rob(int[] nums) {
        if (nums == null) return 0;
        int len = nums.length;
        if (len == 0) return 0;
        if (len == 1) return nums[0];
        
        int[] dp = new int[len];
        dp[0] = nums[0];
        dp[1] = Math.max(nums[0], nums[1]);
        for (int i = 2; i < len; i++) {
            dp[i] = Math.max(dp[i-1], nums[i] + dp[i-2]);
        }
        
        return dp[len-1];
    }
}
