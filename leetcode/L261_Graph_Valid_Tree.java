package leetcode;

/*
 Given n nodes labeled from 0 to n - 1 and a list of undirected edges (each edge is a pair of nodes),
 write a function to check whether these edges make up a valid tree.
For example:
Given n = 5 and edges = [[0, 1], [0, 2], [0, 3], [1, 4]], return true.
Given n = 5 and edges = [[0, 1], [1, 2], [2, 3], [1, 3], [1, 4]], return false.
Hint:
Given n = 5 and edges = [[0, 1], [1, 2], [3, 4]], what should your return? Is this case a valid tree?
Note:
You can assume that no duplicate edges will appear in edges. Since all edges are undirected, 
[0, 1] is the same as [1, 0] and thus will not appear together in edges.
 */
/*
 * Solution:
 * 1. Tree has n - 1 edges.
 * 2. No circle. Use union find to check.
 */
public class L261_Graph_Valid_Tree {
	public boolean validTree(int n, int[][] edges) {
		if (n < 0 || edges == null || edges.length != n - 1)
			return false;
		
		UnionFind uf = new UnionFind(n);
		for (int i = 0; i < edges.length; i++) {
			int p = edges[i][0];
			int q = edges[i][1];
			if (uf.find(p) == uf.find(q))
				return false;
			uf.union(p, q);
		}
		
		return true;
	}
	
	public static void main(String[] args) {
		L261_Graph_Valid_Tree gvt = new L261_Graph_Valid_Tree();
		int[][] edge1 = {{0, 1}, {0, 2}, {0, 3}, {1, 4}};
		int[][] edge2 = {{0, 1}, {0, 2}, {1, 2}, {3, 4}};
		
		System.out.println(gvt.validTree(5, edge1));
		System.out.println(gvt.validTree(5, edge2));
	}

}
