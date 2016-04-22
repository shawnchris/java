package other;
import java.util.*;

public class Labyrinth {
	/*Given a grid, return a solution object.
    *
    * A solution object is a struct that contains the following.
    * = amountOfTime == an int stating how much time it took to complete the maze.
    * = directions == an array of Directions that dictates the path through the maze.
    * = isPossible == a boolean that says *if* there is a path through the maze.
    *
    * A Direction is an enum with six states {Direction.EAST, Direction.WEST, Direction.SOUTH,
    * Direction.NORTH, Direction.UP, Direction.DOWN}
    */
	enum Direction {
		EAST,
		WEST,
		SOUTH,
		NORTH,
		UP,
		DOWN
	}
	class Solution {
		int amountOfTime;
		Direction[] directions;
		boolean isPossible;
	}
	public Solution savePrincess(char[][][] grid) {
	    //init the result
	    Solution sn = new Solution();
		sn.amountOfTime = 0;
		sn.directions = null;
		sn.isPossible = false;
		
		//sanity check
		if (grid == null) return sn;
		int Z = grid.length;
		if (Z == 0) return sn;
		int Y = grid[0].length;
		if (Y == 0) return sn;
		int X = grid[0][0].length;
		if (X == 0) return sn;
		
		class Cell {
			int x, y, z;
			Cell preCell;
			Direction preMove;
			public Cell (int z, int y, int x, Cell p, Direction d) {
				this.z = z;
				this.y = y;
				this.x = x;
				preCell = p;
				preMove = d;
			}
		}
		
		int[] dx = {-1, 1, 0, 0, 0, 0};
		int[] dy = {0, 0, -1, 1, 0, 0};
		int[] dz = {0, 0, 0, 0, -1, 1};
		Direction[] dmap = {Direction.WEST, Direction.EAST, Direction.NORTH,
				Direction.SOUTH, Direction.UP, Direction.DOWN};
		Queue<Cell> queue = new LinkedList<>();
		
		
		//start BFS from where the prince stays
		boolean found = false;
		int x = 0, y = 0, z = 0;
		for (z = 0; z < Z && !found; z++)
			for (y = 0; y < Y && !found; y++)
				for (x = 0; x < X && !found; x++)
					if (grid[z][y][x] == '1') {
						found = true;
						Cell start = new Cell(z, y, x, null, null);
						queue.add(start);
						break;
					}
		if (!found) return sn;
		
		found = false;
		while (!queue.isEmpty() && !found) {
			Cell c = queue.poll();
			//search 6 possible directions
			for (int i = 0; i < 6; i++) {
				x = c.x + dx[i];
				y = c.y + dy[i];
				z = c.z + dz[i];
				//is the new direction legal?
				if (x < 0 || x >= X || y < 0 || y >= Y || z < 0 || z >= Z)
					continue;
				if (grid[z][y][x] == '2') { //find the solution!
					found = true;
					sn.isPossible = true;
					List<Direction> dl = new ArrayList<>();
					dl.add(dmap[i]);
					while (c.preCell != null) {
						dl.add(0, c.preMove);
						c = c.preCell;
					}
					sn.directions = new Direction[dl.size()];
					for (int j = 0; j < dl.size(); j++)
						sn.directions[j] = dl.get(j);
					sn.amountOfTime = dl.size() * 5;
					break;
				}
				if (grid[z][y][x] != '.')
					continue;
				//mark the cell as visited
				grid[z][y][x] = 'V';
				Cell nextStep = new Cell(z, y, x, c, dmap[i]);
				queue.add(nextStep);
			}
		}
		
		return sn;
	}
	
	public static void main(String[] args) {
		Labyrinth l = new Labyrinth();
		char[][][] grid = {{
			{'1', '.', '.'},
			{'o', 'o', '.'},
			{'.', '.', '.'}},
			
			{{'o', 'o', 'o'},
			{'.', '.', 'o'},
			{'.', 'o', 'o'}},
			
			{{'o', 'o', 'o'},
			{'o', '.', '.'},
			{'o', '.', '2'}},
		};
		
		Solution sn = l.savePrincess(grid);
		
		System.out.println(sn.isPossible);
		System.out.println(sn.amountOfTime);
		for (int i = 0; sn.directions!=null && i < sn.directions.length; i++)
			System.out.println(sn.directions[i]);

	}

}
