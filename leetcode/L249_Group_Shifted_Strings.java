package leetcode;
import java.util.*;
/*
 * Given a string, we can "shift" each of its letter to its successive letter,
 * for example: "abc" -> "bcd". We can keep "shifting" which forms the sequence:
 * "abc" -> "bcd" -> ... -> "xyz"
 * Given a list of strings which contains only lowercase alphabets, group all
 * strings that belong to the same shifting sequence.
 * For example, given: ["abc", "bcd", "acef", "xyz", "az", "ba", "a", "z"],
 * Return:
 * [
 *   ["abc","bcd","xyz"],
 *   ["az","ba"],
 *   ["acef"],
 *   ["a","z"]
 * ]
 * Note: For the return value, each inner list's elements must follow the 
 * lexicographic order.
 */
public class L249_Group_Shifted_Strings {
	private String getShifted(String s) {
		char[] ca = s.toCharArray();
		int delta = ca[0] - 'a';
		for (int i = 0; i < ca.length; i++) {
			//int shift = ca[i] - 'a' - delta;
			//if (shift < 0) shift += 26;
			ca[i] = (char)(ca[i] - delta);
			if (ca[i] < 'a') ca[i] += 26;
		}
		return new String(ca);
	}
	
	public List<List<String>> groupStrings(String[] strings) {
		List<List<String>> result = new ArrayList<List<String>>();
		if (strings == null || strings.length == 0) return result;
		
		Map<String, List<String>> map = new HashMap<>();
		Arrays.sort(strings);
		
		for (String s: strings) {
			if (s == null || s.length() == 0) continue;
			String key = getShifted(s);
			List<String> value = map.get(key);
			if (value == null) {
				value = new ArrayList<String>();
			}
			value.add(s);
			map.put(key, value);
		}
		
		for (String key: map.keySet()) {
			result.add(map.get(key));
		}
		
		return result;
	}
	public static void main(String[] args) {
		L249_Group_Shifted_Strings gss = new L249_Group_Shifted_Strings();
		String[] test = {"abc", "bcd", "acef", "xyz", "az", "ba", "a", "z"};
		List<List<String>> result = gss.groupStrings(test);
		for (List<String> ls: result) {
			for (String s: ls) {
				System.out.print(s + " ");
			}
			System.out.println();
		}
	}

}
