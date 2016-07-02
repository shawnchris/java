package leetcode;

public class A026_Remove_Duplicates_from_Sorted_Array {
    public int removeDuplicates(int[] nums) {
        if (nums == null) return 0;
        int len = nums.length;
        if (len < 2) return len;
        int newlen = len;
        
        for (int i = 0; i < len - 1; i++) {
            int j = i + 1;
            for (; j < len; j++) {
                if (nums[j] == nums[i])
                    newlen--;
                else
                    break;
            }
            if (j == len)
                break;
            else {
                if (newlen != len) {
                    for (int k = j; k < len; k++)
                        nums[k - (j - i) + 1] = nums[k];
                    len = newlen;
                }
            }
        }
        
        return newlen;
    }
}
