package leetcode;
import java.util.*;

public class A047_PermutationsII {
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
