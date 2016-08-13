package cc189;

public class C01Q01_Is_Unique {
	class QuestionA {
		public boolean isUniqueChars(String str) {
			if (str.length() > 128) {
				return false;
			}
			boolean[] char_set = new boolean[128];
			for (int i = 0; i < str.length(); i++) {
				int val = str.charAt(i);
				if (char_set[val]) return false;
				char_set[val] = true;
			}
			return true;
		}
	}
	
	class QuestionB {

		/* Assumes only letters a through z. */
		public boolean isUniqueChars(String str) {
			if (str.length() > 26) { // Only 26 characters
				return false;
			}
			int checker = 0;
			for (int i = 0; i < str.length(); i++) {
				int val = str.charAt(i) - 'a';
				if ((checker & (1 << val)) > 0) return false;
				checker |= (1 << val);
			}
			return true;
		}
	}
	
	public static void main(String[] args) {
		String[] words = {"abcde", "hello", "apple", "kite", "padle"};
		C01Q01_Is_Unique c01q01 = new C01Q01_Is_Unique();
		for (String word : words) {
			boolean wordA = (c01q01.new QuestionA()).isUniqueChars(word);
			boolean wordB = (c01q01.new QuestionB()).isUniqueChars(word);
			if (wordA == wordB) {
				System.out.println(word + ": " + wordA);
			} else {
				System.out.println(word + ": " + wordA + " vs " + wordB);
			}
		}
	}
}
