package leetcode;

public class A186_Reverse_Words_in_a_String_II {
	public static void reverseWords(char[] s) {
        if (s == null || s.length == 0) return;
        int i = 0, j = 0;
        while (i < s.length) {
        	j = i;
        	while (j < s.length && s[j] != ' ') j++;
        	reverse(s, i, j);
        	i = j + 1;
        }
        reverse(s, 0, s.length);
    }
	
	private static void reverse(char[] s, int start, int end) {
		end--;
		while (start < end) {
			char temp = s[start];
			s[start] = s[end];
			s[end] = temp;
			start++;
			end--;
		}
	}
	
	public static void main(String[] args) {
		char[] input1 = "the sky is blue".toCharArray();
		reverseWords(input1);
		System.out.println(new String(input1));
	}

}
