package leetcode;
import java.util.*;

public class A039_Combination_Sum {
    private void helper(int[] candidates, int current, int target, int start, List<Integer> history, List<List<Integer>> result) {
        if (current == target) {
            List<Integer> r = new ArrayList<>(history);
            result.add(r);
            return;
        }
        
        for (int i = start; i < candidates.length; i++) {
            if (current + candidates[i] > target)
                break;
            history.add(candidates[i]);
            helper(candidates, current + candidates[i], target, i, history, result);
            history.remove(history.size() - 1);
        }
    }
    
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
		List<List<Integer>> result = new ArrayList<List<Integer>>();
		if (candidates == null) return result;
		int len = candidates.length; 
		if (len == 0) return result;
		
		Arrays.sort(candidates);
        helper(candidates, 0, target, 0, new ArrayList<Integer>(), result);
		
		return result;
	}
}
