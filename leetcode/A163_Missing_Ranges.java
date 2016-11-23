package leetcode;
import java.util.*;

public class A163_Missing_Ranges {
    public static List<String> findMissingRanges(int[] nums, int lower, int upper) {
        List<String> result = new ArrayList<>();
        long left = lower;
        if (nums != null && nums.length != 0) {
            left = Math.min(lower, nums[0]);
            for (int i = 0; i < nums.length; i++) {
                if (i != 0 && nums[i] == nums[i - 1]) continue;
                if (nums[i] == left) {
                    left++;
                    continue;
                }
                insertRange(left, nums[i] - 1, result);
                left = (long)nums[i] + 1;
            }
        }
        
        if (left <= upper) insertRange(left, upper, result);
        
        return result;
    }
    
    private static void insertRange(long left, int right, List<String> result) {
    	if (left > right) return;
        if (right == left) {
            result.add(left + "");
        }
        else {
            result.add(left + "->" + right);
        }
    }
    
	public static void main(String[] args) {
		System.out.println(findMissingRanges(new int[] {0, 1, 3, 50, 75}, 0, 99));
		// ["2", "4->49", "51->74", "76->99"].
		
		System.out.println(findMissingRanges(new int[] {}, 1, 1));
		// ["1"]
		
		System.out.println(findMissingRanges(new int[] {1, 1, 1}, 1, 1));
		// []
		
		System.out.println(findMissingRanges(new int[] {Integer.MAX_VALUE}, 0, Integer.MAX_VALUE));
		// ["0->2147483646"]
	}

}
