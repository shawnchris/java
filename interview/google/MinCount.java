package interview.google;

public class MinCount {
	public static int minCount(String s) {
		if (s.length() == 0) return 0;
		
		int prev = Integer.MAX_VALUE, curr = 0, i = 0;
		while (i < s.length()) {
			if (s.charAt(i) == 'x') {
				curr++; i++;
			}
			else if (s.charAt(i) == '(') {
				int j = i + 1, count = 1;
				while (j < s.length() && count != 0) {
					if (s.charAt(j) == '(') count++;
					if (s.charAt(j) == ')') count--;
					j++;
				}
				curr += minCount(s.substring(i + 1, j - 1));
				i = j;
			}
			else if (s.charAt(i) == '|') {
				prev = Math.min(prev, curr);
				curr = 0;
				i++;
			}
		}
		
		return Math.min(prev, curr);
	}
	
	public static void main(String[] args) {
		String[] tests = {
				"xxxx",
				"xx|xxx",
				"(xx)|xxx",
				"(xxxx)",
				"(x|)x",
				"((xx|)|x)x(xxxx)",
		};
		for (String s : tests) {
			System.out.println(minCount(s));
		}
	}

}
