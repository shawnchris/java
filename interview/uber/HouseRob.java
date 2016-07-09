package interview.uber;
/*
 * Problem 1. Given an array, find the max sum of non-adjacent elements.
 * Problem 2. Given a tree, find the max sum of non-adjacent elements.
 * The difference is all element can be positive or negative.
 */
public class HouseRob {
	// Solution for "House Robber I" still works for this problem.
	public int problem1(int[] nums) {
		if (nums == null) return 0;
        int len = nums.length;
        if (len == 0) return 0;
        if (len == 1) return nums[0];
        
        int even = nums[0];
        int odd = Math.max(nums[0], nums[1]);
        for (int i = 2; i < len; i++) {
            if (i % 2 == 0)
                even = Math.max(odd, nums[i] + even);
            else
                odd = Math.max(even, nums[i] + odd);
        }
        
        return Math.max(even, odd);
	}
	
	// Solution for "House Robber III" still works for this problem.
	public int problem2(TreeNode root) {
		if (root == null) return 0;
        Result r = helper(root);
        return Math.max(r.rob, r.norob);
	}
	class Result {
        int rob;
        int norob;
        Result() { rob = 0; norob = 0;}
    }
    private Result helper(TreeNode root) {
        Result r = new Result();
        if (root == null)
            return r;
        
        Result lr = helper(root.left);
        Result rr = helper(root.right);
        
        r.rob = root.val + lr.norob + rr.norob;
        r.norob = Math.max(lr.rob, lr.norob) + Math.max(rr.rob, rr.norob);
        
        return r;
    }
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
