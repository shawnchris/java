package leetcode;

public class A463_Island_Perimeter {
    public static int islandPerimeter(int[][] grid) {
        int result = 0;
        if (grid == null || grid.length == 0) return result;
        int m = grid.length;
        int n = grid[0].length;
        if (n == 0) return result;
        
        for (int i = 0; i < m; i++) {
        	for (int j = 0; j < n; j++) {
        		if (grid[i][j] == 1) {
        			int t = 4;
        			if (i - 1 >= 0 && grid[i - 1][j] == 1) t--;
        			if (i + 1 < m && grid[i + 1][j] == 1) t--;
        			if (j - 1 >= 0 && grid[i][j - 1] == 1) t--;
        			if (j + 1 < n && grid[i][j + 1] == 1) t--;
        			result += t;
        		}
        	}
        }
        
        return result;
    }
    
	public static void main(String[] args) {
		int[][] grid = {{0,1,0,0},
		                {1,1,1,0},
		                {0,1,0,0},
		                {1,1,0,0}};
		System.out.println(islandPerimeter(grid));
	}

}
