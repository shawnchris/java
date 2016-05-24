package leetcode;
import java.util.*;

public class A046_Permutations {
    List<List<Integer>> result;
    private void helper(int[] nums, ArrayList<Integer> current, int left, HashSet<Integer> used) {
        if (left == 0) {
            result.add(new ArrayList<Integer>(current));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if (used.contains(i)) continue;
            current.add(nums[i]);
            used.add(i);
            helper(nums, current, left - 1, used);
            current.remove(current.size() - 1);
            used.remove(i);
        }
    }
    public List<List<Integer>> permute(int[] nums) {
        result = new ArrayList<List<Integer>>();
        if (nums == null || nums.length == 0) return result;
        Arrays.sort(nums);
        helper(nums, new ArrayList<Integer>(), nums.length, new HashSet<Integer>());
        return result;
    }
}
