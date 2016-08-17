package leetcode;

public class A075_Sort_Colors {
    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
    
    public void sortColors(int[] nums) {
        if (nums == null) return;
        int len = nums.length;
        if (len <= 1) return;
        
        int i = 0, j = len - 1;
        while (i < j) {
            while(i < j && nums[i] == 0)
                i++;
            while(i < j && nums[j] != 0)
                j--;
            if (i != j)
                swap(nums, i, j);
        }
        
        j = len - 1;
        while (i < j) {
            while(i < j && nums[i] == 1)
                i++;
            while(i < j && nums[j] != 1)
                j--;
            if (i != j)
                swap(nums, i, j);
        }
    }
}
