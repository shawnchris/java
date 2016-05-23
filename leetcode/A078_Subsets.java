package leetcode;
import java.util.*;

public class A078_Subsets {
    List<List<Integer>> result = null;
    
    private void helper(int[] nums, List<Integer> subset, int left, int pos) {
        result.add(new ArrayList<Integer>(subset));
        
        if (left == 0) return;
        
        for (int i = pos; i < nums.length; i++) {
            subset.add(nums[i]);
            helper(nums, subset, left - 1, i + 1);
            subset.remove(subset.size() - 1);
        }
    }
    
    public List<List<Integer>> subsets(int[] nums) {
        result = new ArrayList<List<Integer>>();
        if (nums == null) return result;
        int len = nums.length;
        if (len == 0) return result;
        
        Arrays.sort(nums);
        helper(nums, new ArrayList<Integer>(), len, 0);
        
        return result;
    }
}
