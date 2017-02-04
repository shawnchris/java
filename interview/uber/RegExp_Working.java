package interview.uber;

public class RegExp_Working {
	
	public static boolean isMatch(String pattern, String target) {
		// Base case
		if (pattern.length() == 0) {
			if (target.length() == 0)
				return true;
			else
				return false;
		}
		// Get the first pattern
		char p = pattern.charAt(0);
		// Default bounds
		int lowerBound = 1, upperBound = 1, pEnd = 1;
		// Update upper/lower bounds
		if (pattern.length() > 3 && pattern.charAt(1) == '{') {
			while (pEnd < pattern.length() && pattern.charAt(pEnd) != '}') {
				pEnd++;
			}
			String[] sa = pattern.substring(2, pEnd).split(",");
			lowerBound = Integer.parseInt(sa[0].trim());
			upperBound = Integer.parseInt(sa[1].trim());
			pEnd++;
		}
		// Does target match lower bound?
		if (target.length() < lowerBound) return false;
		for (int i = 0; i < lowerBound; i++) {
			if (target.charAt(i) != p) {
				return false;
			}
		}
		// Recursively check from lowerBound to upperBound if any success match
		int k = 0;
		do {
			if (isMatch(pattern.substring(pEnd), target.substring(lowerBound + k)))
				return true;
			k++;
		} while (lowerBound + k <= upperBound);
		
		// No match found
		return false;
	}

	public static void main(String[] args) {
		System.out.println(isMatch("a{0, 2}", "")); //true
		System.out.println(isMatch("a{0, 2}", "a")); //true
		System.out.println(isMatch("a{0, 2}", "aa"));  //true
		System.out.println(isMatch("a{0, 2}", "aaa"));  //false
		System.out.println(isMatch("a{1, 2}", "")); //false
		System.out.println(isMatch("a{1, 2}a{0, 2}", "aa")); //true
		System.out.println(isMatch("ab{0, 2}", "ab")); //true
		System.out.println(isMatch("a{1, 2}a{0, 2}b", "aaaab")); //true
		System.out.println(isMatch("a{1, 2}ba{0, 2}", "aba")); //true
		System.out.println(isMatch("a{1, 2}ba{0, 2}", "baa")); //false
		System.out.println(isMatch("abcde", "abcde")); //true
		System.out.println(isMatch("abcde{0, 5}", "abcdeee")); //true
	}

}
