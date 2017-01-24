package leetcode;
import java.util.*;

public class A484_Find_Permutation {
    public static int[] findPermutation(String s) {
        if (s == null) return new int[0];
        int n = s.length();
        List<Integer> r = new ArrayList<Integer>();
        int i = 0, a = 1;
        
        while (i < n) {
        	if (s.charAt(i) == 'I') {
        		r.add(a);
        		a++;
        		i++;
        	}
        	else {
        		int j = i;
        		while (j < n && s.charAt(j) == 'D') j++;
        		int b = a + j - i;
        		for (; b >= a; b--) {
        			r.add(b);
        		}
        		a = a + j - i + 1;
        		i = j + 1;
        	}
        }
        
        if (r.size() < n + 1) r.add(n + 1);
        
        int[] result = new int[n + 1];
        for (int k = 0; k <= n; k++) {
        	result[k] = r.get(k);
        }
        
        return result;
    }
    
	public static void main(String[] args) {
		print(findPermutation("I"));
		print(findPermutation("DI"));
		print(findPermutation("D"));
		print(findPermutation("DDDD"));
		print(findPermutation("DDDI"));
		print(findPermutation("IDDI"));
		print(findPermutation(""));
		print(findPermutation("IDDIDD"));
	}
	
	private static void print(int[] a) {
		for (int num : a) {
			System.out.print(num + " ");
		}
		System.out.println();
	}

}
