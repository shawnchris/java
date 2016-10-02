package leetcode;

public class A408_Valid_Word_Abbreviation {
	public boolean validWordAbbreviation(String word, String abbr) {
		if (word == null || abbr == null) return false;
		
		int i = 0, j = 0;
		
		while (i < abbr.length()) {
			if (Character.isLetter(abbr.charAt(i))) {
				// letter
				if (j >= word.length() || abbr.charAt(i) != word.charAt(j)) {
					return false;
				}
				i++;
				j++;
			}
			else {
				// digit
				if (abbr.charAt(i) == '0') return false;
				int i2 = i + 1;
				while (i2 < abbr.length() && Character.isDigit(abbr.charAt(i2))) {
					i2++;
				}
				int len = Integer.parseInt(abbr.substring(i, i2));
				for (int j2 = 0; j2 < len; j2++) {
					j++;
					if (j > word.length()) {
						return false;
					}
				}
				i = i2;
			}
		}
		
		if (j != word.length()) return false;
		
        return true;
    }
	public static void main(String[] args) {
		A408_Valid_Word_Abbreviation vw = new A408_Valid_Word_Abbreviation();
		String s1 = "internationalization", abbr1 = "i12iz4n";
		String s2 = "apple", abbr2 = "a2e";
		System.out.println(vw.validWordAbbreviation(s1, abbr1));
		System.out.println(vw.validWordAbbreviation(s2, abbr2));
		
		String s3 = "internationalization", abbr3 = "i5a11o1";
		System.out.println(vw.validWordAbbreviation(s3, abbr3));
		
		System.out.println(vw.validWordAbbreviation("hi", "1"));
		System.out.println(vw.validWordAbbreviation("hi", "02"));
	}

}
