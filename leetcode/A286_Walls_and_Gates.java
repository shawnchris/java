package leetcode;
import java.util.*;

public class A286_Walls_and_Gates {
	private static int INF = Integer.MAX_VALUE;
	
    public static void wallsAndGates(int[][] rooms) {
    	if (rooms == null) return;
        int R = rooms.length;
        if (R <= 0) return;
        int C = rooms[0].length;
        if (C <= 0) return;
        Queue<int[]> queue = new LinkedList<>();
        int[] dr = {0, 0, 1, -1};
        int[] dc = {1, -1, 0, 0};
        
        for (int i = 0; i < R; i++) {
    		for (int j = 0; j < C; j++) {
    			if (rooms[i][j] == 0) {
    				queue.offer(new int[] {i, j});
    			}
    		}
        }
        
        // BFS
        while (!queue.isEmpty()) {
        	int[] position = queue.poll();
        	int dist = rooms[position[0]][position[1]];
        	for (int i = 0; i < 4; i++) {
        		int row = position[0] + dr[i];
        		int col = position[1] + dc[i];
        		if (row < 0 || row >= R || col < 0 || col >= C || rooms[row][col] != INF)
        			continue;
        		rooms[row][col] = dist + 1;
        		queue.offer(new int[] {row, col});
        	}
        }
    }
    
    private static void print(int[][] rooms) {
    	for (int i = 0; i < rooms.length; i++) {
    		for (int j = 0; j < rooms[0].length; j++) {
    			if (rooms[i][j] == INF) {
    				System.out.print("INF\t");
    			}
    			else {
    				System.out.print(rooms[i][j] + "\t");
    			}
    		}
    		System.out.println();
    	}
    }
    
    public static void main(String[] args) {
    	int[][] rooms = 
    		{{INF,  -1,  0,  INF},
    		{INF, INF, INF,  -1},
    		{INF,  -1, INF,  -1},
    		{0,  -1, INF, INF}};
    	wallsAndGates(rooms);
    	print(rooms);
    }
}
