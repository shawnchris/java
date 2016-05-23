package leetcode;
import java.util.*;

public class A090_SubsetsII {
    List<List<Integer>> result = null;
    
    private void helper(int[] nums, List<Integer> subset, int left, int pos) {
        result.add(new ArrayList<Integer>(subset));
        
        if (left == 0) return;
        
        for (int i = pos; i < nums.length; i++) {
            if (i != pos && nums[i] == nums[i - 1]) continue;
            subset.add(nums[i]);
            helper(nums, subset, left - 1, i + 1);
            subset.remove(subset.size() - 1);
        }
    }
    
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        result = new ArrayList<List<Integer>>();
        if (nums == null) return result;
        int n = nums.length;
        if (n == 0) return result;
        Arrays.sort(nums);
        helper(nums, new ArrayList<Integer>(), n, 0);
        return result;
    }
}
