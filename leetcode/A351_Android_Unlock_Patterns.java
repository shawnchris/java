package leetcode;

public class A351_Android_Unlock_Patterns {
    public int numberOfPatterns(int m, int n) {
        int skip[][] = new int[10][10];
        skip[1][3] = skip[3][1] = 2;
        skip[1][7] = skip[7][1] = 4;
        skip[3][9] = skip[9][3] = 6;
        skip[7][9] = skip[9][7] = 8;
        skip[1][9] = skip[9][1] = skip[2][8] = skip[8][2] = skip[3][7] = skip[7][3] = skip[4][6] = skip[6][4] = 5;
        boolean visited[] = new boolean[10];
        int result = 0;
        
        for (int i = m; i <= n; i++) {
            result += traverse(skip, visited, 1, i - 1) * 4;
            result += traverse(skip, visited, 2, i - 1) * 4;
            result += traverse(skip, visited, 5, i - 1);
        }
        
        return result;
    }
    
    private int traverse(int[][] skip, boolean[] visited, int curr, int left) {
        if (left == 0) return 1;
        
        visited[curr] = true;
        int result = 0;
        for (int i = 1; i <= 9; i++) {
            if (!visited[i] && (skip[curr][i] == 0 || visited[skip[curr][i]])) {
                result += traverse(skip, visited, i, left - 1);
            }
        }
        visited[curr] = false;
        
        return result;
    }
    
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
