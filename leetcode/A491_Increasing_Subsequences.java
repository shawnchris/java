package leetcode;
import java.util.*;

public class A491_Increasing_Subsequences {
    public static List<List<Integer>> findSubsequences(int[] nums) {
    	List<List<Integer>> result = new ArrayList<List<Integer>>();
    	if (nums == null || nums.length == 0) return result;
    	
    	helper(nums, 0, new ArrayList<Integer>(), result);
    	
    	return result;
    }
    
    private static void helper(int[] nums, int start, List<Integer>curr, List<List<Integer>> result) {
    	if (curr.size() > 1) {
    		result.add(new ArrayList<Integer>(curr));
    	}
    	
    	for (int i = start; i < nums.length; i++) {
    		if (i != start && nums[i] == nums[i - 1] ||
    				curr.size() > 0 && nums[i] < curr.get(curr.size() - 1)) {
    			continue;
    		}
    		curr.add(nums[i]);
    		helper(nums, i + 1, curr, result);
    		curr.remove(curr.size() - 1);
    	}
    }
    
	public static void main(String[] args) {
		System.out.println(findSubsequences(new int[] {4, 6, 7, 7}));
		System.out.println(findSubsequences(new int[] {1,2,3,4,5,6,7,8,9,10,1,1,1,1,1}));
	}

}
