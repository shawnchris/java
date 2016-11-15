package leetcode;

public class A453_Minimum_Moves_to_Equal_Array_Elements {
    public int minMoves(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        int min = nums[0];
        for (int i = 1; i < nums.length; i++) {
            min = Math.min(min, nums[i]);
        }
        
        int result = 0;
        for (int num : nums) {
            result += num - min;
        }
        
        return result;
    }
}
