package leetcode;

public class A027_Remove_Element {
    public int removeElement(int[] nums, int val) {
        if (nums == null) return 0;
        int len = nums.length;
        if (len == 0) return 0;
        
        int i = 0, j = len - 1;
        while (i < j) {
            while (i < j && nums[j] == val)
                j--;
            while (i < j && nums[i] != val)
                i++;
            if (i != j) {
                nums[i] = nums[i] ^ nums[j];
                nums[j] = nums[i] ^ nums[j];
                nums[i] = nums[i] ^ nums[j];
            }
        }
        
        return nums[j] == val ? j : j + 1;
    }
}
