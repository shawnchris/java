package leetcode;
import java.util.*;

public class A077_Combinations {
    private List<List<Integer>> result = null;
    
    private void combineHelper(List<Integer> subset, int n, int start, int left) {
        if (left == 0) {
            result.add(new ArrayList<Integer>(subset));
            return;
        }
        
        for (int i = start; i <= n; i++) {
            subset.add(i);
            combineHelper(subset, n, i + 1, left - 1);
            subset.remove(subset.size() - 1);
        }
    }
    
    public List<List<Integer>> combine(int n, int k) {
        result = new ArrayList<List<Integer>>();
        if (n == 0 || k == 0 || k > n) return result;
        
        combineHelper(new ArrayList<Integer>(), n, 1, k);
        return result;
    }
}
