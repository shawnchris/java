package leetcode;

public class A283_Move_Zeroes {
    public void moveZeroes(int[] nums) {
        if (nums == null) return;
        int len = nums.length;
        if (len <= 1) return;
        
        int i = 0, j = -1;
        
        while (j < len && i < len) {
            while (i < len && nums[i] != 0)
                i++;
            if (j == -1) j = i + 1;
            while (j < len && nums[j] == 0)
                j++;
            if (j < len && i < len) {
                nums[i] = nums[j];
                nums[j] = 0;
            }
        }
    }
}
