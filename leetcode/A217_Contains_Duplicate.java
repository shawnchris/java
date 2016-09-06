package leetcode;
import java.util.*;

public class A217_Contains_Duplicate {
    static HashSet<Integer> set = new HashSet<Integer>();
    
    public boolean containsDuplicate(int[] nums) {
        boolean result = false;
        int len = nums.length;
        if (len <= 1) return result;
        
        set.clear();
        for (int i = 0; i < len; i++) {
            if (set.contains(nums[i])) {
                result = true;
                break;
            }
            else
                set.add(nums[i]);
        }
        
        return result;
    }
}
