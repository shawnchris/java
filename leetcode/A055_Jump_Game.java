package leetcode;

public class A055_Jump_Game {
	// Greedy solution
    public boolean canJump(int[] nums) {
        int len = nums.length;
        int farest = 0;
        
        for (int i = 0; i < len; i++) {
            if (i <= farest)
                farest = Math.max(farest, i + nums[i]);
            else
                return false;
        }
        
        return true;
    }
}
