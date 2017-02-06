package leetcode;
import java.util.*;

public class A329_Longest_Increasing_Path_in_a_Matrix {
	
	// topological sort
    public int longestIncreasingPath(int[][] matrix) {
        if (matrix == null) return 0;
        int m = matrix.length;
        if (m == 0) return 0;
        int n = matrix[0].length;
        if (n == 0) return 0;
        
        int[][] dir = {{0, 1}, {1, 0}};
        int[] incomings = new int[m * n];
        Set[] outgoings = new HashSet[m * n];
        for (int i = 0; i < m * n; i++) {
        	outgoings[i] = new HashSet<Integer>();
        }
        
        for (int i = 0; i < m; i++) {
        	for (int j = 0; j < n; j++) {
        		for (int[] d : dir) {
        			int r = i + d[0];
        			int c = j + d[1];
        			if (r < 0 || r >= m || c < 0 || c >= n
        				|| matrix[i][j] == matrix[r][c]) continue;
        			int big, small;
        			if (matrix[i][j] > matrix[r][c]) {
        				big = i * n + j;
        				small = r * n + c;
        			}
        			else {
        				big = r * n + c;
        				small = i * n + j;
        			}
        			incomings[big]++;
        			outgoings[small].add(big);
        		}
        	}
        }
        
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < m * n; i++) {
        	if (incomings[i] == 0) queue.add(i);
        }
        
        int level = 0;
        while (!queue.isEmpty()) {
        	level++;
        	int size = queue.size();
        	for (int i = 0; i < size; i++) {
        		int index = queue.poll();
        		for (Object o : outgoings[index]) {
        			int next = (int)o;
        			if (--incomings[next] == 0) {
        				queue.add(next);
        			}
        		}
        	}
        }
        
        return level;
    }
    
    // Memory search
    private static final int[][] dirs = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
    public int longestIncreasingPath2(int[][] matrix) {
    	if (matrix == null) return 0;
        int m = matrix.length;
        if (m == 0) return 0;
        int n = matrix[0].length;
        if (n == 0) return 0;
        
        int[][] cache = new int[m][n];
        
        int result = 0;
        for (int i = 0; i < m; i++) {
        	for (int j = 0; j < n; j++) {
        		int len = search(matrix, cache, i, j);
        		result = Math.max(result, len);
        	}
        }
        
        return result;
    }
    
    private int search(int[][] matrix, int[][] cache, int i, int j) {
    	if (cache[i][j] == 0) {
    		int max = 1;
    		for (int[] d : dirs) {
    			int r = i + d[0];
    			int c = j + d[1];
    			if (r < 0 || r >= matrix.length || c < 0 || c >= matrix[0].length
        				|| matrix[i][j] <= matrix[r][c]) continue;
    			int len = search(matrix, cache, r, c) + 1;
    			max = Math.max(max, len);
    		}
    		cache[i][j] = max;
    	}
    	return cache[i][j];
    }
    
	public static void main(String[] args) {
		int[][] nums1 = {
				  {9,9,4},
				  {6,6,8},
				  {2,1,1}
				};
		int[][] nums2 = {
				  {3,4,5},
				  {3,2,6},
				  {2,2,7}
				};
		A329_Longest_Increasing_Path_in_a_Matrix li = new A329_Longest_Increasing_Path_in_a_Matrix();
		System.out.println(li.longestIncreasingPath(nums1));
		System.out.println(li.longestIncreasingPath2(nums1));
		System.out.println(li.longestIncreasingPath(nums2));
		System.out.println(li.longestIncreasingPath2(nums2));
	}

}
