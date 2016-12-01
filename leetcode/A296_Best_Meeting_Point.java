package leetcode;
import java.util.*;

public class A296_Best_Meeting_Point {
	
    public static int minTotalDistance(int[][] grid) {
        if (grid == null) return 0;
        int m = grid.length;
        if (m == 0) return 0;
        int n = grid[0].length;
        if (n == 0) return 0;
        
        List<Integer> row = new ArrayList<>();
        List<Integer> col = new ArrayList<>();
        
        for (int i = 0; i < m; i++) {
        	for (int j = 0; j < n; j++) {
        		if (grid[i][j] == 1) {
        			row.add(i);
        			col.add(j);
        		}
        	}
        }
        
        return distMedian(row) + distMedian(col);
    }
    
    private static int distMedian(List<Integer> list) {
    	Collections.sort(list);
    	int index = list.size() / 2;
    	int median = list.get(index);
    	int result = 0;
    	for (int i = 0; i < list.size(); i++) {
    		result += (i < index ? median - list.get(i) : list.get(i) - median);
    	}
    	return result;
    }
    
	public static void main(String[] args) {
		int[][] grid1 = {{1,0,0,0,1},{0,0,0,0,0},{0,0,1,0,0}};
		System.out.println(minTotalDistance(grid1)); // 6
		int[][] grid2 =
			{{0,1,0,0,0,0,0,0,0},
			{0,1,0,0,0,1,0,0,0},
			{1,1,1,0,1,0,1,0,0},
			{0,0,1,0,0,0,0,1,0},
			{1,0,1,1,0,1,0,0,0}};
		System.out.println(minTotalDistance(grid2)); // 39
	}

}
