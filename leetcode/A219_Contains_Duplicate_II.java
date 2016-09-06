package leetcode;
import java.util.*;

public class A219_Contains_Duplicate_II {
    public boolean containsNearbyDuplicate(int[] nums, int k) {
        boolean result = false;
        int len = nums.length;
        if (len <= 1) return result;
        
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < len; i++) {
            if (set.contains(nums[i])) {
                result = true;
                break;
            }
            else {
                set.add(nums[i]);
                if (set.size() > k)
                    set.remove(nums[i - k]);
            }
        }
        
        return result;
    }
}
