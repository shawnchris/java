package leetcode;

public class A209_Min_Size_Subarray_Sum {

	public int minSubArrayLen(int s, int[] nums) {
        if (nums == null) return -1;
        int len = nums.length;
        if (len == 0) return 0;
        
        int j = 0, sum = 0, min = Integer.MAX_VALUE;
        for (int i = 0; i < len; i++) {
            while (j < len && sum < s) {
                sum += nums[j];
                j++;
            }
            
            if (sum >= s)
                min = Math.min(min, j - i);
            else if (j >= len)
                break;
            
            sum -= nums[i];
        }
        
        return min == Integer.MAX_VALUE ? 0 : min;
    }
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
