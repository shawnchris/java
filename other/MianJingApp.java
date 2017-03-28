package other;

//给一个二维数组， 然后给一个起点(a, b)，现在 从起点开始可以往右下移动，每次(a, b) => (a + b, b) or (a, a + b)，问能否访问到终点 (m, n)  


import java.util.*;
public class MianJingApp {
	public static void main(String[] args) {
		int[][] nums = new int[10][9];
		for (int i = 0; i < nums.length; i++) {
			for (int j = 0; j < nums[0].length; j++) {
				nums[i][j] = 0;
				System.out.print(0 + ", ");
			}
			System.out.println();
		}

		MianJingSolution so = new MianJingSolution();
		System.out.println(so.checkValid(nums, 1, 1, 2, 6));
	}
}

/*
 * algorithm:
 * using bfs, we can generate the two paths from the starting point. continue checking if we can reach to point m, n
 * 
 * detail:
 * as we use bfs, we can use a queue to store the points that we are going to visit in each level,
 * for every point int the queue, we do the following:
 * 		 1. check if reaches point(m, n) -> if yes -> return true
 * 		 2. expand the quanlified two paths(x + y, y) or (x, x + y) and offer them into queue
 * we should avoid duplicate visits by using a boolean[][] array.
 * and if the queue becomes empty, it means there is no qualified point -> return false
 * 
 * 
 * */


// space complexity = O(m * n); 
/*
 * time:
 * depth = Math.max(m , n)
 * upper bounded by O(m * n)
 * 
 * 
 * */

class MianJingSolution {
	public boolean checkValid(int[][] nums, int a, int b, int m, int n) {
		if (nums == null || nums[0] == null) {
			return false;
		}
		if (m < 0 || n < 0 || m >= nums.length  || n >= nums[0].length) {
			return false;
		}
		if (a < 0 || a > m || b < 0 || b > n) {
			return false;
		}
		Queue<int[]> queue = new LinkedList<>();
		boolean[][] visited = new boolean[m + 1][n + 1];
		queue.offer(new int[]{a, b});
		visited[a][b] = true;
		while (!queue.isEmpty()) {
			int size = queue.size();
			for (int i = 0; i < size; i++) {
				int[] temp = queue.poll();
				if (temp[0] == m && temp[1] == n) {
					return true;
				}

				if (temp[0] + temp[1] <= m && !visited[temp[0] + temp[1]][temp[1]]) {
					//System.out.println(temp[0] + temp[1] + ", " + temp[1]);
					queue.offer(new int[]{temp[0] + temp[1], temp[1]});
					visited[temp[0] + temp[1]][temp[1]] = true;
				}
				if (temp[0] + temp[1] <= n && !visited[temp[0]][temp[0] + temp[1]]) {
					//System.out.println(temp[0] + ", " + (temp[0] + temp[1]));
					queue.offer(new int[]{temp[0], temp[0] + temp[1]});
					visited[temp[0]][temp[0] + temp[1]] = true;
				}
			}
		}
		return false;
	}
}