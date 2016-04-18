package other;
import java.util.*;
/*
 * Given an un-sorted array contains n+2 elements which are in range [1...n].
 * Find the two duplicate numbers.
 */
public class Find_Two_Dup_in_Array {

	private static void shuffle(int[] a) {
		Random rand = new Random();
		for (int i = 0; i < a.length - 1; i++) {
			int j = rand.nextInt(a.length - i);
			int temp = a[i];
			a[i] = a[j];
			a[j] = temp;
		}
	}
	
	public static int[] find_dup(int[] a) {
		int[] result = {-1, -1};
		int c = 0;
		for (int i = 0; i < a.length; i++) {
			int j = Math.abs(a[i]);
			int b = a[j];
			if (b < 0) { // find a result;
				result[c] = j;
				c++;
				if (c == 2) break;
			}
			else { // mark by negate a[j]
				a[j] = -a[j];
			}
		}
		return result;
	}
	
	public static void main(String[] args) {
		int n = 5000, tests = 5000;
		int dup1 = 128, dup2 = 256;
		int pass = 0, fail = 0;
		int[] a = new int[n+2];
		for (int t = 0; t < tests; t++) {
			for (int i = 0; i < n; i++)
				a[i] = i + 1;
			a[n] = dup1;
			a[n + 1] = dup2;
			shuffle(a);
			int[] result = find_dup(a);
			if (result[0] == dup1 && result[1] == dup2 || 
					result[0] == dup2 && result[1] == dup1)
				pass++;
			else
				fail++;
		}
		System.out.println("Total test cases: " + tests 
				+ ", pass: " + pass + ", fail: " + fail);
	}

}
