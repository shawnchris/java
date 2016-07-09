package hackerrank;

import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {
	static String[] buildSubsets(String s) {
		int len = s.length();
		List<String> r = new ArrayList<String>();
		
		r.add(s);
		for (int i = 0; i < len; i++) {
			for (int j = i + 1; j <= len; j++) {
				String t = s.substring(0, i) + s.substring(j, len);
				if (t.length() > 0)
					r.add(t);
			}
		}
		int c = 0;
		String[] result = new String[r.size()];
		for (String t : r) {
			result[c] = t;
			c++;
		}
		Arrays.sort(result);

		return result;
	}

	public static void main(String[] args) {
		buildSubsets("ba");

	}

}
