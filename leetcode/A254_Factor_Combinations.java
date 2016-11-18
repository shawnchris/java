package leetcode;
import java.util.*;

public class A254_Factor_Combinations {
    public static List<List<Integer>> getFactors(int n) {
    	List<List<Integer>> result = new ArrayList<List<Integer>>();
        if (n < 4) return result;
        backTrack(n, 2, new ArrayList<Integer>(), result);
        return result;
    }
    
    private static void backTrack(int n, int start, ArrayList<Integer> current, List<List<Integer>> result) {
    	if (current.size() != 0) {
    		List<Integer> res = new ArrayList<Integer>(current);
    		res.add(n);
    		result.add(res);
    	}
    	for (int i = start; i * i <= n; i++) {
    		if (n % i == 0 && n / i >= i) {
    			current.add(i);
    			backTrack(n / i, i, current, result);
    			current.remove(current.size() - 1);
    		}
    	}
    }
    
	public static void main(String[] args) {
		System.out.println(getFactors(32));
	}

}
