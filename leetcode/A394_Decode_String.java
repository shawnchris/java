package leetcode;

public class A394_Decode_String {
	public String decodeString(String s) {
		StringBuilder result = new StringBuilder();

		for (int i = 0; i < s.length();) {

			if (Character.isDigit(s.charAt(i))) {
				// find the leading number
				int j = i;
				while (j < s.length() && Character.isDigit(s.charAt(j))) {
					j++;
				}
				int k = Integer.parseInt(s.substring(i, j));

				// find the string
				int l = j + 1, left = 1;
				while (l < s.length()) {
					if (s.charAt(l) == ']') {
						left--;
						if (left == 0)
							break;
					}
					if (s.charAt(l) == '[') {
						left++;
					}
					l++;
				}
				String sub = decodeString(s.substring(j + 1, l));

				// multiply
				for (int m = 0; m < k; m++) {
					result.append(sub);
				}

				i = l + 1;
			} else {
				int n = i;
				while (n < s.length() && Character.isLetter(s.charAt(n))) {
					n++;
				}
				String sub = s.substring(i, n);
				result.append(sub);
				i = n;
			}

		}

		return result.toString();
	}

	public static void main(String[] args) {
		A394_Decode_String ds = new A394_Decode_String();
		System.out.println(ds.decodeString("3[a]2[bc]"));
		System.out.println(ds.decodeString("3[a2[c]]"));
		System.out.println(ds.decodeString("3[a]2[bc]"));
		System.out.println(ds.decodeString("2[abc]3[cd]ef"));
	}

}
