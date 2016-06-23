package other;
import java.util.*;
/*
 * Input 3(abc)2(bc)cc, output: "abcabcabcbcbccc".
 */
public class PajiPhone {

	public static String transform(String s) {
		if (s == null || s.length() == 0) return ""; 
		StringBuilder sb = new StringBuilder();
		List<Integer> numbers = new ArrayList<>();
		List<String> words = new ArrayList<>();
		
		int i = 0, j = 0;
		// Parse the input string
		while (i < s.length()) {
			j = i;
			// Check number
			int num = 1;
			if (Character.isDigit(s.charAt(i))) {
				 while (j < s.length() && Character.isDigit(s.charAt(j)))
					 j++;
				 num = Integer.parseInt(s.substring(i, j));
			}
			numbers.add(num);
			
			// Check word
			String word = "";
			while (j < s.length() && s.charAt(j) == '(')
				j++;
			i = j;
			while (j < s.length() && Character.isLetter(s.charAt(j)))
				 j++;
			word = s.substring(i, j);
			words.add(word);
			
			// To the next start position
			while (j < s.length() && s.charAt(j) == ')')
				j++;
			i = j;
		}
		
		// Calculate the output
		for (int k = 0; k < numbers.size(); k++) {
			int num = numbers.get(k);
			for (int l = 0; l < num; l++) {
				sb.append(words.get(k));
			}
		}
		
		return sb.toString();
	}
	
	public static void main(String[] args) {
		System.out.println(transform("3(abc)2(bc)cc"));
		System.out.println(transform("(abc)2(bc)cc"));
		System.out.println(transform("(abc)2(bc)3(cc)"));
	}

}
