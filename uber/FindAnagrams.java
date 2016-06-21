package uber;
import java.util.*;
/*
 * Find all substring from str2 which are anagram of str1. 
 */
public class FindAnagrams {
	public static List<String> findAnagrams (String str1, String str2) {
		List <String> result = new ArrayList<>();
		if (str1 == null || str2 == null || str1.length() == 0 || 
				str2.length() == 0 || str2.length() < str1.length())
			return result;
		int len1 = str1.length(), len2 = str2.length();
		Map<Character, Integer> map = new HashMap<>();
		
		for (int i = 0; i < len1; i++) {
			char k = str1.charAt(i);
			Integer v = map.get(k);
			if (v == null) v = 1;
			else v++;
			map.put(k, v);
		}
		
		int left = len1;
		for (int i = 0; i < len2; i++) {
			char k = str2.charAt(i);
			Integer v = map.get(k);
			if (v == null) continue;
			v--;
			map.put(k, v);
			if (v >= 0) left--;
			if (left == 0) {
				result.add(str2.substring(i + 1 - len1, i + 1));
			}
			
			if (i + 1 - len1 >= 0) {
				char m = str2.charAt(i + 1 - len1);
				Integer n = map.get(m);
				if (n != null) {
					n++;
					map.put(m, n);
					if (n > 0) left++;
				}
			}
		}
		
		return result;
	}
	public static void main(String[] args) {
		List<String> result = findAnagrams("aabc", "abcabbcaa");
		for (String s: result) {
			System.out.println(s);
		}
	}

}
