package leetcode;

public class A280_Wiggle_Sort {
    public void wiggleSort(int[] nums) {
        for (int i = 1; i < nums.length; i++) {
            if (i % 2 == 0) { // Even should be no bigger than odd
                if (nums[i] > nums[i - 1]) {
                    swap(nums, i);
                }
            }
            else { // Odd should be no smaller than even
                if (nums[i] < nums[i - 1]) {
                    swap(nums, i);
                }
            }
        }
    }
    
    private void swap(int[] nums, int i) {
        int temp = nums[i];
        nums[i] = nums[i - 1];
        nums[i - 1] = temp;
    }
}
