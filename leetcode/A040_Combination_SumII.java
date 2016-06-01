package leetcode;
import java.util.*;

public class A040_Combination_SumII {
    private void helper(int[] candidates, int current, int target, int start, List<Integer> history, Set<List<Integer>> result) {
        if (current == target) {
            List<Integer> r = new ArrayList<>(history);
            result.add(r);
            return;
        }
        
        for (int i = start; i < candidates.length; i++) {
            if (current + candidates[i] > target)
                break;
            history.add(candidates[i]);
            helper(candidates, current + candidates[i], target, i + 1, history, result);
            history.remove(history.size() - 1);
        }
    }
    
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        Set<List<Integer>> result = new HashSet<List<Integer>>();
		int len = candidates.length;
		if (len == 0) return new ArrayList<List<Integer>>();
		
		Arrays.sort(candidates);
		helper(candidates, 0, target, 0, new ArrayList<Integer>(), result);
		
		return new ArrayList<List<Integer>>(result);
    }
}
