package leetcode;
import java.util.*;

public class A305_Number_of_Islands_II {
	class UnionFind {
        private int count = 0;
        private int[] parent, rank;
        
        public UnionFind(int n) {
            count = n;
            parent = new int[n];
            rank = new int[n];
            for (int i = 0; i < n; i++) {
                parent[i] = i;
            }
        }
        
        public int find(int p) {
            int q = parent[p];
            while (q != parent[q]) {
                q = parent[q];
            }
            parent[p] = q;
            return q;
        }
        
        public void union(int p, int q) {
            int rootP = find(p);
            int rootQ = find(q);
            if (rootP == rootQ) return;
            if (rank[rootQ] > rank[rootP]) {
                parent[rootP] = rootQ;
            }
            else {
                parent[rootQ] = rootP;
                if (rank[rootP] == rank[rootQ]) {
                    rank[rootP]++;
                }
            }
            count--;
        }
        
        public int count() {
            return count;
        }
    }
    public List<Integer> numIslands2(int m, int n, int[][] positions) {
        UnionFind uf = new UnionFind(m * n);
        List<Integer> result = new ArrayList<>();
        int[][] map = new int[m][n];
        for (int i = 0; i < positions.length; i++) {
            int row = positions[i][0], col = positions[i][1];
            map[row][col] = 1;
            if (row - 1 >= 0 && map [row - 1][col] == 1) {
                uf.union(row * n + col, (row - 1) * n + col);
            }
            if (col - 1 >= 0 && map [row][col - 1] == 1) {
                uf.union(row * n + col, row * n + col - 1);
            }
            if (row + 1 < m && map [row + 1][col] == 1) {
                uf.union(row * n + col, (row + 1) * n + col);
            }
            if (col + 1 < n && map [row][col + 1] == 1) {
                uf.union(row * n + col, row * n + col + 1);
            }
            result.add(uf.count() - (m * n - i - 1));
        }
        return result;
    }
	public static void main(String[] args) {
		A305_Number_of_Islands_II ni = new A305_Number_of_Islands_II();
		int[][] pos1 = {{0,0},{0,1},{1,2},{2,1}};
		System.out.println(ni.numIslands2(3, 3, pos1));

	}

}
