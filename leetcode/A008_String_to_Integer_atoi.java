package leetcode;
import java.util.*;

public class A008_String_to_Integer_atoi {
    public int myAtoi(String str) {
        long result = 0;
		String input = str.trim();
		int length = input.length();
		
		if (length<=0)	return 0;
				
		Map<Character, Integer> numbers = new HashMap<>();
		for (int i=0; i<10; i++)
			numbers.put((char)(i+'0'), i);

		char first = input.charAt(0);
		if (!(numbers.containsKey(first) || first == '+' || first == '-'))
			return 0;
			
		boolean positive = true;
		if (first == '-')	positive = false;
		char c = ' ';
		for (int i=0; i<length; i++) {
			c = input.charAt(i);
			if (numbers.containsKey(c)) {
				result = result * 10 + numbers.get(c);
				if (result > Integer.MAX_VALUE) break;
			}
			else if (i>0) break;
		}
		if (!positive) result = result * -1;
		
		if (result > Integer.MAX_VALUE) return Integer.MAX_VALUE;
		if (result < Integer.MIN_VALUE) return Integer.MIN_VALUE;
		return (int)result;
    }
}
