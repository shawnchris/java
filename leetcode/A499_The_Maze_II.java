package leetcode;

import java.util.Arrays;

public class A499_The_Maze_II {
    int min; //min distance to hole
    String minS; //min distance's path string
    int[] hole;
    int[][] maze; 
    int[][] map; //shortest distant traveling from ball to this point
    int[][] dirs = {{0,1},{-1,0},{1,0},{0,-1}}; //r, u, d, l
    public String findShortestWay(int[][] maze, int[] ball, int[] hole) {
        this.min = Integer.MAX_VALUE; 
        this.minS = null;
        this.hole = hole; 
        this.maze = maze; 
        this.map = new int[maze.length][maze[0].length];
        for (int i = 0; i < map.length; i++) Arrays.fill(map[i], Integer.MAX_VALUE); 
        move(ball[0], ball[1], 0, "", -1);
        return (minS==null) ? "impossible" : minS;
    }
    
    private void move(int r, int c, int cnt, String path, int dir){ //dir is a index of dirs 
        if (cnt > min || cnt > map[r][c]) return; //not a shortest route for sure
        
        int rr = r;
        int cc = c;
        if (dir != -1){ //if not from start point 
            //add path
            if (dir == 0) path += 'r';
            else if (dir == 1) path += 'u';
            else if (dir == 2) path += 'd';
            else path += 'l';
    
            //roll along dir 
            while(rr >= 0 && rr < maze.length && cc >= 0 && cc < maze[0].length && maze[rr][cc] == 0){
                map[rr][cc] = Math.min(map[rr][cc], cnt);
                if (rr == hole[0] && cc == hole[1]) { //check hole
                    if(cnt == min && path.compareTo(minS) < 0){
                        minS = path;
                    }
                    else if (cnt < min) {
                        min = cnt;
                        minS = path;
                    }
                    return; 
                }
                rr += dirs[dir][0]; //move along dir
                cc += dirs[dir][1];
                cnt++;
            }
            rr -= dirs[dir][0];//[rr,cc] is wall, need to walk back 1 step
            cc -= dirs[dir][1];
            cnt--;
        }
        
        //hit wall (or start) -> try to turn
        for(int i = 0; i < dirs.length; i++) {
            if (dir == i || dir == (3 - i)) continue; //dont keep going this dir and dont go back
            int newR = rr + dirs[i][0];
            int newC = cc + dirs[i][1];
            if (newR >= 0 && newR < maze.length && newC >= 0 && newC < maze[0].length && maze[newR][newC] == 0){ //can go
                move(rr, cc, cnt, path, i);
            }
        }
    }
    
	public static void main(String[] args) {
		A499_The_Maze_II m = new A499_The_Maze_II();
		
		int[][] maze1 = {{0,0,0,0,0},{1,1,0,0,1},{0,0,0,0,0},{0,1,0,0,1},{0,1,0,0,0}};
		int[] ball1 = {4, 3};
		int[] hole1 = {0, 1};
		System.out.println(m.findShortestWay(maze1, ball1, hole1));
		
		int[][] maze2 = {{0,0,1,0,0},{0,0,0,0,0},{0,0,0,1,0},{1,1,0,1,1},{0,0,0,0,0}};
		int[] ball2 = {0, 4};
		int[] hole2 = {4, 4};
		System.out.println(m.findShortestWay(maze2, ball2, hole2));
		
		int[][] maze3 = {
				{0,0,0,0,1,0,0,1,0,0,1,0,0,0,0,0,1,0,0,1,0,0,1,0,0,1,0,0,0,0},
				{0,0,0,0,0,1,0,0,1,0,0,0,0,0,1,0,0,1,0,0,1,0,0,1,0,0,0,0,0,0},
				{0,0,0,1,0,0,1,0,0,1,0,0,0,0,0,1,0,0,1,0,0,0,0,0,0,0,0,0,0,0},
				{1,0,0,1,0,0,1,0,0,0,0,0,1,0,0,1,0,0,1,0,0,1,0,0,1,0,0,1,0,0},
				{0,1,0,0,1,0,0,1,0,0,1,0,0,1,0,0,1,0,0,0,0,0,1,0,0,1,0,0,1,0},
				{0,0,1,0,0,1,0,0,1,0,0,1,0,0,1,0,0,1,0,0,1,0,0,1,0,0,0,0,0,1},
				{0,0,0,1,0,0,0,0,0,1,0,0,1,0,0,1,0,0,0,0,0,1,0,0,1,0,0,1,0,0},
				{1,0,0,1,0,0,1,0,0,1,0,0,1,0,0,1,0,0,1,0,0,1,0,0,1,0,0,1,0,0},
				{0,1,0,0,1,0, 0 ,1,0,0,1,0,0,1,0,0,1,0,0,1,0,0,1,0,0,0,0,0,1,0},
				{0,0,1,0,0,1,0,0,1,0,0,1,0,0,1,0,0,1,0,0,0,0,0,1,0,0,1,0,0,1},
				{0,0,0,1,0,0,1,0,0,1,0,0,1,0,0,1,0,0,1,0,0,0,0,0,0,0,0,1,0,0},
				{1,0,0,1,0,0,1,0,0,0,0,0,0,0,0,1,0,0,1,0,0,1,0,0,1,0,0,0,0,0},
				{0,1,0,0,0,0,0,0,0,0,1,0,0,1,0,0,1,0,0,1,0,0,1,0,0,1,0,0,1,0},
				{0,0,0,0,0,1,0,0,1,0,0,0,0,0,1,0,0,1,0,0,1,0,0,0,0,0,1,0,0,1},
				{0,0,0,1,0,0,1,0,0,1,0,0,1,0,0,0,0,0,1,0,0,0,0,0,1,0,0,0,0,0},
				{1,0,0,1,0,0,0,0,0,1,0,0,1,0,0,1,0,0,0,0,0,1,0,0,0,0,0,1,0,0},
				{0,1,0,0,1,0,0,1,0,0,1,0,0,0,0,0,0,0,0,1,0,0,1,0,0,1,0,0,0,0},
				{0,0,1,0,0,1,0,0,1,0,0,0,0,0,1,0,0,1,0,0,1,0,0,1,0,0,1,0,0,1},
				{0,0,0,0,0,0,1,0,0,1,0,0,1,0,0,0,0,0,1,0,0,1,0,0,0,0,0,1,0,0},
				{1,0,0,1,0,0,1,0,0,1,0,0,1,0,0,1,0,0,1,0,0,1,0,0,1,0,0,0,0,0},
				{0,1,0,0,1,0,0,0,0,0,1,0,0,1,0,0,1,0,0,1,0,0,0,0,0,0,0,0,0,0},
				{0,0,0,0,0,1,0,0,1,0,0,1,0,0,1,0,0,0,0,0,1,0,0,0,0,0,0,0,0,1},
				{0,0,0,1,0,0,1,0,0,1,0,0,1,0,0,1,0,0,0,0,0,1,0,0,1,0,0,1,0,0},
				{0,0,0,0,0,0,1,0,0,1,0,0,1,0,0,0,0,0,1,0,0,1,0,0,1,0,0,1,0,0},
				{0,0,0,0,1,0,0,1,0,0,0,0,0,1,0,0,1,0,0,1,0,0,1,0,0,1,0,0,0,0},
				{0,0,1,0,0,0,0,0,1,0,0,1,0,0,0,0,0,1,0,0,1,0,0,1,0,0,1,0,0,0},
				{0,0,0,1,0,0,1,0,0,1,0,0,1,0,0,0,0,0,0,0,0,1,0,0,0,0,0,1,0,0},
				{1,0,0,0,0,0,1,0,0,1,0,0,1, 0 ,0,1,0,0,1,0,0,1,0,0,1,0,0,1,0,0},
				{0,1,0,0,1,0,0,1,0,0,0,0,0,1,0,0,1,0,0,1,0,0,1,0,0,1,0,0,1,0},
				{0,0,1,0,0,1,0,0,0,0,0,1,0,0,0,0,0,1,0,0,1,0,0,0,0,0,1,0,0,0}};
		int[] ball3 = {27,13};
		int[] hole3 = {8,6};
		System.out.println(m.findShortestWay(maze3, ball3, hole3));
	}

}
