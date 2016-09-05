package leetcode;
import java.util.*;

public class A179_Largest_Number {
    class MyComparator implements Comparator<String> {
        public int compare(String s1, String s2) {
            String t1 = s1 + s2;
            String t2 = s2 + s1;
            return -1 * t1.compareTo(t2);
        }
    }
    public String largestNumber(int[] nums) {
		String result = "";
		if (nums == null || nums.length == 0)
			return result;
		
		Queue<String> queue = new PriorityQueue<String>(nums.length, new MyComparator());
		
		for (int i = 0; i < nums.length; i++)
			queue.add(nums[i] + "");
		while (!queue.isEmpty())
			result += queue.poll();
		
		if (result.charAt(0) == '0') return "0";
		
		return result;
	}
}
