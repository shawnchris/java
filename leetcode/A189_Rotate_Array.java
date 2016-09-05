package leetcode;

//O(n) time O(1) space solution
public class A189_Rotate_Array {
    public void rotate(int[] nums, int k) {
        if (nums == null) return;
        int len = nums.length;
        if (len <= 1) return;
        if (k <= 0) return;
        
        int kk = k % len;
        int temp = 0;
        for (int i = 0; i < (len - kk) / 2; i++) {
            temp = nums[i];
            nums[i] = nums[len - kk - 1 - i];
            nums[len - kk - 1 - i] = temp;
        }
        for (int i = 0; i < kk / 2; i++) {
            temp = nums[len - kk + i];
            nums[len - kk + i] = nums[len - 1 - i];
            nums[len - 1 - i] = temp;
        }
        for (int i = 0; i < len / 2; i++) {
            temp = nums[i];
            nums[i] = nums[len - 1 - i];
            nums[len - 1 - i] = temp;
        }
    }
}
