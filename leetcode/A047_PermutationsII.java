package leetcode;
import java.util.*;

public class A047_PermutationsII {
	public List<List<Integer>> permuteUnique2(int[] nums) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        if (nums == null || nums.length == 0) return result;
        
        Arrays.sort(nums);
        boolean[] used = new boolean[nums.length];
        helper(nums, nums.length, new ArrayList<Integer>(), used, result);
        return result;
    }
    
    private void helper(int[] nums, int left, List<Integer> current, boolean[] used, 
                            List<List<Integer>> result) {
        if (left == 0) {
            result.add(new ArrayList<Integer>(current));
            return;
        }
        
        for (int i = 0; i < nums.length; i++) {
            if (used[i]) continue;
            if (i != 0 && nums[i] == nums[i - 1] && !used[i - 1]) continue;
            used[i] = true;
            current.add(nums[i]);
            helper(nums, left - 1, current, used, result);
            current.remove(current.size() - 1);
            used[i] = false;
        }
    }
	
	
    List<List<Integer>> result = null;
    
    private void permuteHelper(int[] nums, int left, List<Integer> curResult, Set<Integer> used) {
        if (left == 0) {
            result.add(new ArrayList<Integer>(curResult));
            return;
        }
        boolean added = false;
        int lastAdded = 0;
        for (int i = 0; i < nums.length; i++) {
            if (used.contains(i)) continue;
            if (added && nums[i] == lastAdded) continue;
            curResult.add(nums[i]);
            used.add(i);
            added = true;
            lastAdded = nums[i];
            permuteHelper(nums, left - 1, curResult, used);
            curResult.remove(curResult.size() - 1);
            used.remove(i);
        }
    }
    
    public List<List<Integer>> permuteUnique(int[] nums) {
        if (nums == null) return null;
        result = new ArrayList<List<Integer>>();
        int len = nums.length;
        if (len == 0) return result;
        
        Arrays.sort(nums);
        permuteHelper(nums, len, new ArrayList<Integer>(), new HashSet<Integer>());
        
        return result;
    }
}
