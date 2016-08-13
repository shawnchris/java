package leetcode;
import java.util.*;

public class A051_N_Queens_Iterative {
	public List<List<String>> solveNQueens(int n) {
		List<List<String>> results = new ArrayList<List<String>>();
		if (n <= 0)
			return results;

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
					results.add(printSolution(queens, n));
				} else {
					offset = offset + 1;
					queens[offset] = -1;
				}
			} else
				offset = offset - 1;
		}
		return results;
	}

	private List<String> printSolution(int[] queens, int n) {
		List<String> sol = new ArrayList<String>();
		char[] chars = new char[n];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (j != queens[i])
					chars[j] = '.';
				else
					chars[j] = 'Q';
			}
			sol.add(String.valueOf(chars));
			chars = new char[n];
		}
		return sol;
	}

	private boolean isSafe(int current, int[] queens) {
		int i = 0;
		while (i < current) {
			if (queens[i] == queens[current] || Math.abs(queens[i] - queens[current]) == Math.abs(i - current))
				return false;
			i = i + 1;
		}
		return true;
	}
}
