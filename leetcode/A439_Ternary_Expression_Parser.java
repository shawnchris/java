package leetcode;

public class A439_Ternary_Expression_Parser {
	public static String parseTernary(String expression) {
		// Base case
		if (expression.length() <= 1) return expression;
		// Where is the ":" ?
		int index = 0, level = 0;
		while (index < expression.length()) {
			char c = expression.charAt(index);
			if (c == '?') level++;
			if (c == ':') {
				if (level == 1) break;
				else level--;
			}
			index++;
		}
        if (expression.charAt(0) == 'T') {
        	return parseTernary(expression.substring(2, index));
        }
        else {
        	return parseTernary(expression.substring(index + 1));
        }
    }
	public static void main(String[] args) {
		System.out.println(parseTernary("T?2:3"));
		System.out.println(parseTernary("F?1:T?4:5"));
		System.out.println(parseTernary("T?T?F:5:3"));
	}

}
