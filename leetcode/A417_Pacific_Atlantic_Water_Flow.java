package leetcode;
import java.util.*;

public class A417_Pacific_Atlantic_Water_Flow {
    public List<int[]> pacificAtlantic(int[][] mat) {
        // Sanity check
    	List<int[]> result = new ArrayList<>();
    	if (mat == null) return result;
    	int M = mat.length;
    	if (M == 0) return result;
    	int N = mat[0].length;
    	if (N == 0) return result;
    	
    	int[][] visitedPac = new int[M][N];
    	int[][] visitedAlt = new int[M][N];
    	int[] dx = {-1, 1, 0, 0};
    	int[] dy = {0, 0, -1, 1};
    	
    	Queue<int[]> toVisit = new LinkedList<>();
    	// BFS from Pacific
    	for (int col = 0; col < N; col++) {
    		visitedPac[0][col] = 1;
    		toVisit.add(new int[] {0, col});
    	}
    	for (int row = 1; row < M; row++) {
    		visitedPac[row][0] = 1;
    		toVisit.add(new int[] {row, 0});
    	}
    	while (!toVisit.isEmpty()) {
    		int[] p = toVisit.poll();
    		int y = p[0], x = p[1];
    		for (int i = 0; i < 4; i++) {
    			int ny = y + dy[i];
    			int nx = x + dx[i];
    			if (ny >= 0 && ny < M && nx >= 0 && nx < N && 
    					visitedPac[ny][nx] == 0 && mat[y][x] <= mat[ny][nx]) {
    				visitedPac[ny][nx] = 1;
    				toVisit.add(new int[] {ny, nx});
    			}
    		}
    	}
    	// BFS from Atlantic
    	for (int col = 0; col < N; col++) {
    		visitedAlt[M - 1][col] = 1;
    		toVisit.add(new int[] {M - 1, col});
    	}
    	for (int row = 0; row < M; row++) {
    		visitedAlt[row][N - 1] = 1;
    		toVisit.add(new int[] {row, N - 1});
    	}
    	while (!toVisit.isEmpty()) {
    		int[] p = toVisit.poll();
    		int y = p[0], x = p[1];
    		for (int i = 0; i < 4; i++) {
    			int ny = y + dy[i];
    			int nx = x + dx[i];
    			if (ny >= 0 && ny < M && nx >= 0 && nx < N && 
    					visitedAlt[ny][nx] == 0 && mat[y][x] <= mat[ny][nx]) {
    				visitedAlt[ny][nx] = 1;
    				toVisit.add(new int[] {ny, nx});
    			}
    		}
    	}
    	
    	// Overlapping points are the solutions
    	for (int i = 0; i < M; i++) {
    		for (int j = 0; j < N; j++) {
    			if (visitedPac[i][j] == 1 && visitedAlt[i][j] == 1) {
    				result.add(new int[] {i, j});
    			}
    		}
    	}
    	
    	return result;
    }
    
	public static void main(String[] args) {
		A417_Pacific_Atlantic_Water_Flow fw = new A417_Pacific_Atlantic_Water_Flow();
		int[][] mat = {
				{1, 2, 2, 3, 5},
				{3, 2, 3, 4, 4},
				{2, 4, 5, 3, 1},
				{6, 7, 1, 4, 5},
				{5, 1, 1, 2, 4},
		};
		
		List<int[]> result = fw.pacificAtlantic(mat);
		for (int[] p: result)
			System.out.println(p[0] + "," + p[1]);

	}

}
