package hackerrank;

import java.util.*;

public class BiggerIsGreater {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int t = Integer.parseInt(in.nextLine());
		for (int i = 0; i < t; i++) { // for each test case
			String str = in.nextLine();
			int len = str.length();
			// search for a pivot backward which char(i) < char(i+1)
			int j = len - 2;
			for (; j >= 0; j--)
				if (str.charAt(j) < str.charAt(j+1))
					break;
			if (j < 0)
				System.out.println("no answer");
			else {
				// get the index of the smallest char which greater than char(j)
				int idx = 0;
				char smallest ='z';
				for (int k = j + 1; k < len; k++)
					if (str.charAt(k) > str.charAt(j) && str.charAt(k) <= smallest) {
						smallest = str.charAt(k);
						idx = k;
					}
				//System.out.println(smallest);
				char[] array = new char[len-j-1];
				for (int k = j, l = 0; k < len; k++) {
					if (k == idx) continue;
					array[l] = str.charAt(k);
					l++;
				}
				Arrays.sort(array);
				System.out.println(str.substring(0, j) + smallest + new String(array));
			}
		}
		in.close();
	}

}
/*
5
ab
bb
hefg
dhck
dkhc

*/
/*
ba
no answer
hegf
dhkc
hcdk
*/