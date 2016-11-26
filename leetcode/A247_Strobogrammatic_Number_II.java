package leetcode;
import java.util.*;

public class A247_Strobogrammatic_Number_II {
	public static List<String> findStrobogrammatic(int n) {
        return helper(n, n);
    }
	
	private static List<String> helper(int max, int left) {
		List<String> res = new ArrayList<String>();
		// Base cases
		if (left == 0) {
			res.add("");
			return res;
		}
		if (left == 1) {
			res.addAll(Arrays.asList("0", "1", "8"));
			return res;
		}
		
		List<String> list = helper(max, left - 2);
		
		for (String s : list) {
			res.add("1" + s + "1");
			res.add("6" + s + "9");
			res.add("8" + s + "8");
			res.add("9" + s + "6");
			if (left != max)
				res.add("0" + s + "0");
		}
		
		return res;
	}
	
	public static void main(String[] args) {
		System.out.println(findStrobogrammatic(0));
		System.out.println(findStrobogrammatic(1));
		System.out.println(findStrobogrammatic(2));
		System.out.println(findStrobogrammatic(3));
		System.out.println(findStrobogrammatic(4));
		System.out.println(findStrobogrammatic(5));
		System.out.println(findStrobogrammatic(6));
		System.out.println(findStrobogrammatic(7));

	}

}
