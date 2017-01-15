package leetcode;

public class A487_Max_Consecutive_Ones_II {
    public static int findMaxConsecutiveOnes(int[] nums) {
        int result = 0, n = nums.length, count = 0;
        int[] dp = new int[n];
        
        for (int i = 0; i < n; i++) {
        	dp[i] = count;
        	if (nums[i] == 1) {
        		count++;
        		result = Math.max(count, result);
        	}
        	else count = 0;
        }
        
        count = 0;
        for (int i = n - 1; i >= 0; i--) {
        	dp[i] += count;
        	if (nums[i] == 1) count++;
        	else count = 0;
        }
        
        for (int i = 0; i < n; i++) {
        	if (nums[i] == 0) {
        		result = Math.max(result, dp[i] + 1);
        	}
        }
        
        return result;
    }
    
	public static void main(String[] args) {
		System.out.println(findMaxConsecutiveOnes(new int[] {1,1,0,1,1,1}));
		System.out.println(findMaxConsecutiveOnes(new int[] {}));
		System.out.println(findMaxConsecutiveOnes(new int[] {1}));
		System.out.println(findMaxConsecutiveOnes(new int[] {1,0,1,0,1}));
		System.out.println(findMaxConsecutiveOnes(new int[] {1,0,1,0,1,1}));
	}

}
