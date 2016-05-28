package other;

import java.util.*;

public class PajiOA {
	public int solution(int x) {
		String str = "" + x;
		int index = -1;
		int i = 0;
		
		while (i < str.length()) {
			int j = i + 1;
			if (j < str.length() && str.charAt(i) != str.charAt(j)) {
				i++;
				continue;
			}
			while (j < str.length() && str.charAt(i) == str.charAt(j)) {
				index = j;
				j++;
			}
			if (j < str.length() && str.charAt(index) < str.charAt(j)) {
				// find the result
				break;
			}
			i = j;
		}
		
		String result = str.substring(0, index) + str.substring(index + 1);
		return Integer.parseInt(result);

	}

	public int isSolution2(String input) {
		if (input == null || input.length() < 1) {
			return 0;
		}
		String[] lines = input.split("\n");
		Stack<String> stack = new Stack<>();
		Set<String> used = new HashSet<>();
		String parent = "";
		int result = 0;
		
		for (int i = 0; i < lines.length; i++) {
			int indent = countSpace(lines[i]);
			String fileName = lines[i].substring(indent);
			while (stack.size() > indent) { // find the right parent
				String dir = stack.pop();
				parent = parent.substring(0, parent.length() - dir.length() - 1);
			}
			if (isImage(fileName) && !used.contains(parent)) {
				if (parent.length() > 0) {
					result += parent.length();
				}
				else {
					result += 1; // the root directory
				}
				used.add(parent);
			}
			stack.push(fileName);
			parent += "/" + fileName; // with an "/"
		}

		return result;
	}

	private static boolean isImage(String str) {
		str = str.toLowerCase();
		String[] extension = { ".jpeg", ".png", ".gif" };
		for (String ex : extension) {
			if (str.endsWith(ex)) {
				return true;
			}
		}
		return false;
	}

	private static int countSpace(String str) {
		int count = 0;
		for (int i = 0; i < str.length(); i++) {
			if (Character.isWhitespace(str.charAt(i))) {
				count++;
			} else {
				break;
			}
		}
		return count;
	}

	public static void main(String[] args) {

		PajiOA test = new PajiOA();
		System.out.println(test.solution(223336226));
		System.out.println(test.solution(2211));
		System.out.println(test.solution(221569));
		System.out.println(test.solution(11));
		System.out.println(test.solution(99888777));

		String input1 = "dir1\n dir11\n dir12\n  picture.jpeg\n  dir121\n  file1.txt\ndir2\n file2.gif";
		String input2 = "dir1\n dir11\n dir12\n  picture.jpeg\n  dir121\n  file1.txt\ndir2\n file2.gif\nfile3.png";
		String input3 = "dir1\n dir11\n dir12\n  picture.jpeg\n  dir121\n  file1.txt\ndir2\n file2.gif\nfile3.png\nfile4.jpeg";
		String input4 = "dir1\n dir11\n dir12\n  picture.jpeg\n  dir121\n   121.gif\n  file1.txt\ndir2\n file2.gif";
		System.out.println(test.isSolution2(input1));
		System.out.println(test.isSolution2(input2));
		System.out.println(test.isSolution2(input3));
		System.out.println(test.isSolution2(input4));

	}
}
