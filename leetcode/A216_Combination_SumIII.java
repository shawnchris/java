package leetcode;
import java.util.*;

public class A216_Combination_SumIII {
    private void helper(int current, int target, int start, int left, List<Integer> history, List<List<Integer>> result) {
        if (left == 0) {
            if (current == target) {
                List<Integer> r = new ArrayList<>(history);
                result.add(r);
            }
            return;
        }
        
        for (int i = start; i < 10; i++) {
            if (current + i > target)
                break;
            history.add(i);
            helper(current + i, target, i + 1, left - 1, history, result);
            history.remove(history.size() - 1);
        }
    }
    
    public List<List<Integer>> combinationSum3(int k, int n) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();
		if (k <= 0 || n <= 0) return result;

        helper(0, n, 1, k, new ArrayList<Integer>(), result);
		
		return result;
    }
}
