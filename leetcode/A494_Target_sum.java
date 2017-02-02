package leetcode;

public class A494_Target_sum {
	int result = 0;
	
    public int findTargetSumWays(int[] nums, int S) {
        if(nums == null || nums.length == 0) return result;
        helper(nums, S, 0, 0);
        return result;
    }
    public void helper(int[] nums, int target, int pos, long eval){
        if(pos == nums.length){
            if(target == eval)
                result++;
            return;
        }
        helper(nums, target, pos + 1, eval + nums[pos]);
        helper(nums, target, pos + 1, eval - nums[pos]);
    }
    
	public static void main(String[] args) {
		A494_Target_sum ts = new A494_Target_sum();
		int[] nums1 = {1, 1, 1, 1, 1};
		System.out.println(ts.findTargetSumWays(nums1, 5));
	}

}
