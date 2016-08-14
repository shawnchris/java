package leetcode;

public class A052_N_QueensII_Iterative {
	public int totalNQueens(int n) {
        int results = 0;
		if(n <= 0) return results;

		int[] queens = new int[n];
		int offset = 0;

		queens[0] = -1;
		while (offset >= 0 && offset < n) {
			queens[offset] = queens[offset] + 1;
			while (queens[offset] < n && isSafe(offset, queens) == false) {
				queens[offset] = queens[offset] + 1;
			}
			if (queens[offset] < n) {
				if (offset == n - 1) {
					results++;
				} else {
					offset = offset + 1;
					queens[offset] = -1;
				}
			}
			else offset = offset - 1;
		}
		return results;
	}

	private boolean isSafe(int current, int[] queens) {
		int i = 0;
		while (i < current) {
			if (queens[i] == queens[current] || Math.abs(queens[i] - queens[current]) == Math.abs(i - current)) return false;
			i = i + 1;
		}
        return true;
	}
}
