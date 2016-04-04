package leetcode;

import java.util.*;

public class A274_H_Index {
	public int hIndex(int[] citations) {
        int len = citations.length;
        if (len == 0) return 0;
        
        Arrays.sort(citations);
        if (citations[len - 1] < 1) return 0;
        if (citations[0] >= len) return len;
        
        int i = len - 1;
        while (i >= 0 && len - i <= citations[i])
            i--;
        
        return len - i - 1;
    }
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
