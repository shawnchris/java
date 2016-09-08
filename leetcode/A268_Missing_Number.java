package leetcode;

//O(n) solution using swap idea
public class A268_Missing_Number {
    public int missingNumber(int[] nums) {
        int len = nums.length, i = 0;
        while (i < len) {
            if (nums[i] < len && nums[nums[i]] != nums[i]) {
                int temp = nums[nums[i]];
                nums[nums[i]] = nums[i];
                nums[i] = temp;
            }
            else
                i++;
        }
        
        for (i = 0; i < len; i++)
            if (nums[i] != i)
                return i;
        
        return len;
    }
}
