package leetcode;
import java.util.*;

public class A163_Missing_Ranges {
	public static List<String> findMissingRanges(int[] nums, int lower, int upper) {
        List<String> result = new ArrayList<String>();
        long start = lower;
        
        if (nums != null && nums.length != 0) {
            for (int i = 0; i < nums.length; i++) {
                if (start < nums[i]) {
                    insertRange(start, nums[i] - 1, result);
                }
                start = (long)nums[i] + 1;
            }
        }
        
        if (start <= upper) insertRange(start, upper, result);
        
        return result;
    }
    
    private static void insertRange(long start, int end, List<String> result) {
        if (start > end) return;
        if (start == end) result.add(start + "");
        else result.add(start + "->" + end);
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
