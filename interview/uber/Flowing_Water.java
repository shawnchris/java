package interview.uber;
import java.util.*;

public class Flowing_Water {
	class Point {
		int row, col;
		public Point(int row, int col) {
			this.row = row;
			this.col = col;
		}
	}
    public List<Point> flowing_water2(int[][] mat) {
    	// Sanity check
    	List<Point> result = new ArrayList<>();
    	if (mat == null) return result;
    	int M = mat.length;
    	if (M == 0) return result;
    	int N = mat[0].length;
    	if (N == 0) return result;
    	
    	int[][] visitedPac = new int[M][N];
    	int[][] visitedAlt = new int[M][N];
    	int[] dx = {-1, 1, 0, 0};
    	int[] dy = {0, 0, -1, 1};
    	
    	Queue<Point> toVisit = new LinkedList<>();
    	// BFS from Pacific
    	for (int col = 0; col < N; col++) {
    		visitedPac[0][col] = 1;
    		toVisit.add(new Point(0, col));
    	}
    	for (int row = 1; row < M; row++) {
    		visitedPac[row][0] = 1;
    		toVisit.add(new Point(row, 0));
    	}
    	while (!toVisit.isEmpty()) {
    		Point p = toVisit.poll();
    		int y = p.row, x = p.col;
    		for (int i = 0; i < 4; i++) {
    			int ny = y + dy[i];
    			int nx = x + dx[i];
    			//if (ny == M - 1 && nx == 0 || ny == 1 && nx == N - 1) {
    				// do not pollute other ocean!
    			//	continue;
    			//}
    			if (ny >= 0 && ny < M && nx >= 0 && nx < N && 
    					visitedPac[ny][nx] == 0 && mat[y][x] <= mat[ny][nx]) {
    				visitedPac[ny][nx] = 1;
    				toVisit.add(new Point(ny, nx));
    			}
    		}
    	}
    	// BFS from Atlantic
    	for (int col = 0; col < N; col++) {
    		visitedAlt[M - 1][col] = 1;
    		toVisit.add(new Point(M - 1, col));
    	}
    	for (int row = 0; row < M; row++) {
    		visitedAlt[row][N - 1] = 1;
    		toVisit.add(new Point(row, N - 1));
    	}
    	while (!toVisit.isEmpty()) {
    		Point p = toVisit.poll();
    		int y = p.row, x = p.col;
    		for (int i = 0; i < 4; i++) {
    			int ny = y + dy[i];
    			int nx = x + dx[i];
    			//if (ny == M - 2 && nx == 0 || ny == 0 && nx == N - 1) {
    				// do not pollute other ocean!
    			//	continue;
    			//}
    			if (ny >= 0 && ny < M && nx >= 0 && nx < N && 
    					visitedAlt[ny][nx] == 0 && mat[y][x] <= mat[ny][nx]) {
    				visitedAlt[ny][nx] = 1;
    				toVisit.add(new Point(ny, nx));
    			}
    		}
    	}
    	
    	/* debug code
    	for (int i = 0; i < M; i++) {
    		for (int j = 0; j < N; j++) {
    			System.out.print(visitedPac[i][j] + " ");
    		}
    		System.out.println();
    	}
    	System.out.println();
    	for (int i = 0; i < M; i++) {
    		for (int j = 0; j < N; j++) {
    			System.out.print(visitedAlt[i][j] + " ");
    		}
    		System.out.println();
    	}
    	*/
    	// Overlapping points are the solutions
    	for (int i = 0; i < M; i++) {
    		for (int j = 0; j < N; j++) {
    			if (visitedPac[i][j] == 1 && visitedAlt[i][j] == 1) {
    				result.add(new Point(i, j));
    			}
    		}
    	}
    	
    	return result;
    }
	public static void main(String[] args) {
		Flowing_Water fw = new Flowing_Water();
		/*
		*    0  1  2   3   4   5   6
		* 0  ~  ~   ~   ~   ~   ~  ~
		* 1  ~  1   2   2   3  (5) *
		* 2  ~  3   2   3  (4) (4) *
		* 3  ~  2   4  (5)  3   1  *
		* 4  ~ (6) (7)  1   4   5  *
		* 5  ~ (5)  1   1   2   4  *
		* 6  *  *   *   *   *   *  *
		*/
		int[][] mat = {
				{0, 0, 0, 0, 0, 0, 0},
				{0, 1, 2, 2, 3, 5, 0},
				{0, 3, 2, 3, 4, 4, 0},
				{0, 2, 4, 5, 3, 1, 0},
				{0, 6, 7, 1, 4, 5, 0},
				{0, 5, 1, 1, 2, 4, 0},
				{0, 0, 0, 0, 0, 0, 0},
		};
		int[][] mat2 = {
				{1, 2, 2, 3, 5},
				{3, 2, 3, 4, 4},
				{2, 4, 5, 3, 1},
				{6, 7, 1, 4, 5},
				{5, 1, 1, 2, 4},
		};
		
		List<Point> result = fw.flowing_water2(mat2);
		for (Point p: result)
			System.out.println(p.row + "," + p.col);
	}

}
