package leetcode;
import java.util.*;

public class A325_Maximum_Size_Subarray_Sum_Equals_k {
    public int maxSubArrayLen(int[] nums, int k) {
        if (nums == null || nums.length == 0) return 0;
        
        Map<Long, Integer> sumToIndex = new HashMap<>();
        int max = 0;
        long sum = 0;
        sumToIndex.put((long)0, -1);
        
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            if (sumToIndex.containsKey(sum - k)) {
                max = Math.max(max, i - sumToIndex.get(sum - k));
            }
            if (!sumToIndex.containsKey(sum)) {
                sumToIndex.put(sum, i);
            }
        }
        
        return max;
    }
}
