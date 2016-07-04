package leetcode;

public class A041_First_Missing_Positive {
    public int firstMissingPositive(int[] nums) {
        if (nums == null || nums.length == 0)
            return 1;
        for (int i = 0; i < nums.length; i++) {
            while (nums[i] >= 0 && nums[i] < nums.length && nums[i] != nums[nums[i]]) {
                swap(nums, i, nums[i]);
            }
        }
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] != i) {
                return i;
            }
        }
        if (nums[0] == nums.length)
            return nums.length + 1;
        return nums.length;
    }
    
    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}
