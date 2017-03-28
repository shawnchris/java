package other;
/*
问题：
象棋 （我就脑补当成是二维数组吧）棋盘上给两个点A, B  问一个knight最少要几步能从A 调到B    knight可以任意朝向L形跳,
即 从(x, y) 可以跳到(x-2,y-1); (x-2,y+1);(x+2,y-1);(x+2,y+1);(x-1,y+2);(x-1,y-2);(x+1,y-2);(x+1,y+2).

网上有人说用dijkstra写比较好，
我拿dijkstra写的  感觉复杂度是 Time = O(m * n * (log (m*n)))  
但我感觉其实bfs写更好 就是把priorityqueue换成queue 感觉那样复杂度可以降低到 O（m*n)？
还是我理解的不对
*/

import java.util.Comparator;
import java.util.PriorityQueue;

public class MianApp2 {
	public static void main(String[] args) {
		int[][] nums = new int[10][9];
		for (int i = 0; i < nums.length; i++) {
			for (int j = 0; j < nums[0].length; j++) {
				nums[i][j] = 0;
				System.out.print(0 + ", ");
			}
			System.out.println();
		}
		Solution2 so2 = new Solution2();
		System.out.println(so2.shortestPathKnight(nums, 1, 2, 2, 3));
		System.out.println(so2.shortestPathKnight(nums, 0, 0, 2, 1));
	}
}


/*
 * using dijkstra algorithm, 
 * 1. construct a minheap, offer the starting point into the heap
 * 2. expand the point to all the qualified neighbors (we need to check the border and generating rule : jump in "L" like shape)
 * 3. for every qualified point, we use a visited boolean to track if it has been visited before, 
 * 			we can avoid generate the cell more than once because the distance between two adjacent cell is 1(uniform), 
 * 			so the cell generated before has a smaller distance to the start point than the cell regenerated later.
 * 4. if we reaches the x2 y2, return the shortest path
 * 5. if the heap eventrually becomes empty, it means we can not reach the destination
 * 
 * Time = O(m * n * (log (m*n)))
 	because the worst case is we iterate every point in the matrix and put them into the heap once and pop once
 * 
 * 
 * */


class Solution2{
	public int shortestPathKnight(int[][] matrix, int x1, int y1, int x2, int y2) {
		if (matrix == null || matrix[0] == null) {
			return -1;
		}
		if (!checkValid(matrix, x1, y1) || !checkValid(matrix, x2, y2)) {
			return -1;
		}
		// I am going to store the position and the distance to the starting point in an array for every point we visited,
		// the array has three integer inside. ary[0] is the vertical index, ary[1] is the horizontal index, and ary[2] is the distance to starting point
		PriorityQueue<int[]> heap = new PriorityQueue<int[]>(new Comparator<int[]>(){
			public int compare(int[] a, int[] b) {
				return a[2] - b[2];
			}
		});

		int[][] steps = new int[][]{{1, 2}, {1, -2}, {-1, 2}, {-1, -2}, {2, 1}, {2, -1}, {-2, -1}, {-2, 1}};
		boolean[][] visited = new boolean[matrix.length][matrix[0].length];
		heap.offer(new int[]{x1, y1, 0});
		while (!heap.isEmpty()) {
			int[] temp = heap.poll();
			if(temp[0] == x2 && temp[1] == y2) {
				return temp[2];
			}
			for (int i = 0; i < steps.length; i++) {
				int xTemp = temp[0] + steps[i][0];
				int yTemp = temp[1] + steps[i][1];
				if (checkValid(matrix, xTemp, yTemp) && !visited[xTemp][yTemp]) {
					heap.offer(new int[]{xTemp, yTemp, temp[2] + 1});
					visited[xTemp][yTemp] = true;
				}
			}
		}
		return -1;
	}

	public boolean checkValid(int[][] matrix, int x, int y) {
		if (x >= matrix.length || x < 0 || y >= matrix[0].length || y < 0) {
			return false;
		}
		return true;
	}
}