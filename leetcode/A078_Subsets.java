package leetcode;
import java.util.*;

public class A078_Subsets {
    private void helper(int[] nums, List<List<Integer>> result, List<Integer> subset, int start) {
        result.add(new ArrayList<Integer>(subset));
        
        for (int i = start; i < nums.length; i++) {
            subset.add(nums[i]);
            helper(nums, result, subset, i + 1);
            subset.remove(subset.size() - 1);
        }
    }
    
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        if (nums == null) return result;
        int len = nums.length;
        if (len == 0) return result;
        
        helper(nums, result, new ArrayList<Integer>(), 0);
        
        return result;
    }
}
