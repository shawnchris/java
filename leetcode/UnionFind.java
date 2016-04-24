package leetcode;

public class UnionFind {
	private int[] parent;
	private int[] rank;
	private int count;
	
	public UnionFind(int n) {
		parent = new int[n];
		rank = new int[n];
		count = n;
		for (int i = 0; i < n; i++) {
			parent[i] = i;
			rank[i] = 0;
		}
	}
	
	public int getCount() {
		return count;
	}
	
	public int find(int p) {
		if (parent[p] == p) return p;
		
		int q = p;
		while (parent[q] != q)
			q = parent[q];
		parent[p] = q;
		return q;
	}
	
	public void union(int p, int q) {
		int rootp = find(p);
		int rootq = find(q);
		if (p == q) return;
		
		if (rank[rootp] > rank[rootq]) {
			parent[rootq] = rootp;
		}
		else if (rank[rootp] < rank[rootq]) {
			parent[rootp] = rootq;
		}
		else {
			parent[rootq] = rootp;
			rank[rootp]++;
		}
		
		count--;
	}
}
