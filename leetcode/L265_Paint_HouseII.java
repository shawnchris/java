package leetcode;
/*
 * There are a row of n houses, each house can be painted with one of the k
 * colors. The cost of painting each house with a certain color is different.
 * You have to paint all the houses such that no two adjacent houses have the
 * same color. The cost of painting each house with a certain color is
 * represented by a n x k cost matrix. For example, costs[0][0] is the cost of
 * painting house 0 with color 0; costs[1][2] is the cost of painting house 1
 * with color 2, and so on... Find the minimum cost to paint all houses.
 */
public class L265_Paint_HouseII {
	private int[][] getTwoMin(int[][] last, int cost, int index) {
		if (cost < last[0][0] && cost < last[0][1]) {
			last[0][1] = last[0][0];
			last[1][1] = last[1][0];
			last[0][0] = cost;
			last[1][0] = index;
		}
		else if (cost < last[0][1]) {
			last[0][1] = cost;
			last[1][1] = index;
		}
		return last;
	}
	
	public int minCost(int[][] costs) {
		if (costs == null || costs.length == 0)
			return 0;
		int n = costs.length;
		int k = costs[0].length;

		// [0][] costs, [1][] indexes; [][0] min1, [][1] min2;
		int[][] last = new int[2][2];
		last[0][0] = last[0][1] = Integer.MAX_VALUE;
		// init
		for (int j = 0; j < k; j++)
			last = getTwoMin(last, costs[0][j], j);
		
		for (int i = 1; i < n; i++) {
			int[][] curr = new int[2][2];
			curr[0][0] = curr[0][1] = Integer.MAX_VALUE;
			for (int j = 0; j < k; j++) {
				int cost = j != last[1][0] ? costs[i][j] + last[0][0] :
					costs[i][j] + last[0][1];
				curr = getTwoMin(curr, cost, j);
			}
			last = curr;
		}
		
		return last[0][0];
	}
	
	public static void main(String[] args) {
		int[][] costs = {{3, 2, 7, 9}, {6, 5, 3, 7}, {5, 8, 6, 3}};
		L265_Paint_HouseII ph = new L265_Paint_HouseII();
		System.out.println(ph.minCost(costs));
	}
}
