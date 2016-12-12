package leetcode;
import java.util.*;

public class A455_Assign_Cookies {
	// TreeMap solution
    public int findContentChildren(int[] g, int[] s) {
        if (g == null || g.length == 0 || s == null || s.length == 0) return 0;
        
        TreeMap<Integer, Integer> map = new TreeMap<>();
        for (int cookieSize : s) {
        	if (map.containsKey(cookieSize)) {
        		map.put(cookieSize, map.get(cookieSize) + 1);
        	}
        	else {
        		map.put(cookieSize, 1);
        	}
        }
        
        int result = 0;
        //Arrays.sort(g);
        for (int greed : g) {
        	Integer cookieSize = map.ceilingKey(greed);
        	if (cookieSize != null) {
        		int cookieNumber = map.get(cookieSize) - 1;
        		result++;
        		if (cookieNumber <= 0) {
        			map.remove(cookieSize);
        		}
        		else {
        			map.put(cookieSize, cookieNumber);
        		}
        	}
        }
        
        return result;
    }
    
    // Sort solution
    public int findContentChildren2(int[] g, int[] s) {
        if (g == null || g.length == 0 || s == null || s.length == 0) return 0;
        
        int result = 0;
        Arrays.sort(g);
        Arrays.sort(s);
        int i = g.length - 1, j = s.length - 1;
        while (i >= 0 && j >= 0) {
            if (g[i] <= s[j]) {
                result++;
                i--;
                j--;
            }
            else {
                i--;
            }
        }
        
        return result;
    }
    
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
