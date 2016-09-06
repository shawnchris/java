package leetcode;
import java.util.*;

public class A220_Contains_Duplicate_III {
    static Set<Integer> set = new HashSet<Integer>();
    
    boolean hasNearbyAD(int i, int t) {
        if (set == null) return false;
        int len = set.size();
        if (len == 0) return false;
        if (len > t) {
            for (int j = -1 * t; j <= t; j++)
                if (set.contains(i + j))
                    return true;
        }
        else {
            for (int j : set)
                if (Math.abs((long)i - (long)j) <= t)
                    return true;
        }
        return false;
    }
    
    public boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
        boolean result = false;
        int len = nums.length;
        if (len <= 1) return result;
        if (k <= 0) return result;
        
        set.clear();
        for (int i = 0; i < len; i++) {
            if (hasNearbyAD(nums[i], t)) {
                result = true;
                break;
            }
            set.add(nums[i]);
            if (set.size() > k)
                set.remove(nums[i - k]);
        }
        
        return result;
    }
}
