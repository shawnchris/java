package interview.uber;
import java.util.*;
/*
 * Reverse word but not punctuation. e.g.:
 * "this,,,is.a word" -> "word,,,a.is this"
 */
public class Reverse_Word_Not_Punctuation {
	public static String reverse(String s) {
		if (s == null || s.length() == 0) return "";
		StringBuilder sb = new StringBuilder();
		Stack<String> words = new Stack<>();
		Queue<String> puncs = new LinkedList<>();
		boolean startWithWord = Character.isLetter(s.charAt(0));
		int i = 0, j = 0;
		
		// Parse the input string
		while (i < s.length()) {
			j = i;
			if (Character.isLetter(s.charAt(i))) {
				while (j < s.length() && Character.isLetter(s.charAt(j)))
					j++;
				words.push(s.substring(i, j));
			}
			else {
				while (j < s.length() && !Character.isLetter(s.charAt(j)))
					j++;
				puncs.add(s.substring(i, j));
			}
			i = j;
		}
		
		// Build the answer
		if (!startWithWord)
			sb.append(puncs.poll());
		while (!words.isEmpty()) {
			sb.append(words.pop());
			if (!puncs.isEmpty())
				sb.append(puncs.poll());
		}
		
		return sb.toString();
	}
	public static void main(String[] args) {
		System.out.println(reverse("this,,,is.a word"));
		System.out.println(reverse("..this,,,is.a word;;"));
	}

}
