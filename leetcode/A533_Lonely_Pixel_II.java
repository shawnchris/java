package leetcode;
import java.util.*;

public class A533_Lonely_Pixel_II {
	public class Solution {
	    public int findBlackPixel(char[][] picture, int N) {
	        int m = picture.length;
	        if (m == 0) return 0;
	        int n = picture[0].length;
	        if (n == 0) return 0;
	        
	        Map<String, Integer> map = new HashMap<>();
	        int[] colCount = new int[n];
	        
	        for (int i = 0; i < m; i++) {
	            String key = scanRow(picture, i, N, colCount);
	            if (key.length() != 0) {
	                map.put(key, map.getOrDefault(key, 0) + 1);
	            }
	        }
	        
	        int result = 0;
	        for (String key : map.keySet()) {
	            if (map.get(key) == N) {
	                for (int j = 0; j < n; j++) {
	                    if (key.charAt(j) == 'B' && colCount[j] == N) {
	                        result += N;
	                    }
	                }
	            }
	        }
	        
	        return result;
	    }
	    
	    private String scanRow(char[][] picture, int row, int target, int[] colCount) {
	        int n = picture[0].length;
	        int rowCount = 0;
	        StringBuilder sb = new StringBuilder();
	        
	        for (int j = 0; j < n; j++) {
	            if (picture[row][j] == 'B') {
	                rowCount++;
	                colCount[j]++;
	            }
	            sb.append(picture[row][j]);
	        }
	        
	        if (rowCount == target) return sb.toString();
	        return "";
	    }
	}
}
