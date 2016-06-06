package csc421;

import java.util.Comparator;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

/**
 * 
 * Minimum-weight spanning tree with Kruskal algorithm
 * 
 * @author Shan Gao
 *
 */
public class MST {

	class Edge {
		public char node1;
		public char node2;
		public int length;

		public Edge(char node1, char node2, int length) {
			this.node1 = node1;
			this.node2 = node2;
			this.length = length;
		}
	}
	
	/**
	 * Find out which tree/subset does a vertex belongs to
	 * @param forest - the set of vertex sets
	 * @param node - the vertex to look up
	 * @return - the set in which the vertex reside
	 */
	public Set<Character> Kruskal_FindSet(HashSet<HashSet<Character>> forest, char node) {
		for (Set<Character> tree : forest) {
			for (char leaf : tree) {
				if (leaf==node)
					return tree;
			}
		}
		return null;
	}
	
	/**
	 * Union two sets in the forest
	 * @param forest - the set of vertex sets
	 * @param node1 - the vertex resides in first set
	 * @param node2 - the vertex resides in second set
	 */
	public void Kruskal_Union(HashSet<HashSet<Character>> forest, char node1, char node2) {
		Set<Character> tree1 = null;
		Set<Character> tree2 = null;
		
		for (Set<Character> tree : forest) {
			for (char leaf : tree) {
				if (leaf==node1)
					tree1 = tree;
				if (leaf==node2)
					tree2 = tree;
			}
		}
		
		if (tree1 != tree2) {
			for (char leaf : tree2)
				tree1.add(leaf);
			// There seems to have bug with HashSet.remove function. So I need to clear the second set and leave it there.
			tree2.clear();
			forest.remove(tree2); // Bug in this function?
		}
	}
	
	/**
	 * Implementation of Kruskal MST algorithm
	 * @param graph - The undirected weighted graph represented by N*N matrix
	 */
	public void Kruskal(int[][] graph) {
		int n = graph.length;
		
		// The forest which contains all the trees/subsets.
		HashSet<HashSet<Character>> forest = new HashSet<HashSet<Character>>();
		// The min priority queue to store edges.
		PriorityQueue<Edge> edge = new PriorityQueue<Edge>(n*n,
				new Comparator<Edge>() {
					public int compare(Edge e1, Edge e2) {
						return e1.length - e2.length;
					}
		});

		// Make each vertex as a set and put into forest.
		// Put all edges into the min priority queue.
		for (int i=0; i<n; i++) {
			char node = (char)('a' + i);
			HashSet<Character> tree = new HashSet<Character>();
			tree.add(node);
			forest.add(tree);
			//System.out.println("Node: "+node);
			for (int j=i+1; j<n; j++) {
				if (graph[i][j] > 0) {
					Edge e = new Edge(node, (char)('a' + j), graph[i][j]);
					edge.add(e);
					//System.out.println("Edge: "+e.node1+" "+e.node2+" "+e.length);
				}
			}
		}

		//Execute Kruskal algorithm
		int weight = 0;
		while (!edge.isEmpty()) {
			Edge e = edge.poll();
			//Is it a safe edge?
			if (Kruskal_FindSet(forest, e.node1) != Kruskal_FindSet(forest, e.node2)) { //Safe
				weight += e.length;
				System.out.println("("+e.node1+", "+e.node2+"): "+e.length);
				Kruskal_Union(forest, e.node1, e.node2);
			}
		}
		System.out.println("Total weight: "+weight);

	}

	public static void main(String[] args) {
		// The example data in text book
		/*
		int[][] G1 = {{0, 4, 0, 0, 0, 0, 0, 8, 0},
				{0, 0, 8, 0, 0, 0, 0, 11, 0},
				{0, 0, 0, 7, 0, 4, 0, 0, 2},
				{0, 0, 0, 0, 9, 14, 0, 0, 0},
				{0, 0, 0, 0, 0, 10, 0, 0, 0},
				{0, 0, 0, 0, 0, 0, 2, 0, 0},
				{0, 0, 0, 0, 0, 0, 0, 1, 6},
				{0, 0, 0, 0, 0, 0, 0, 0, 7},
				{0, 0, 0, 0, 0, 0, 0, 0, 0}};
		*/
		// Data in assignment 5
		int[][] G2 = {{0, 12, 0, 17, 0, 7, 0, 12, 0, 7},
				{12, 0, 0, 4, 3, 19, 0, 0, 10, 15},
				{0, 0, 0, 3, 2, 2, 0, 3, 20, 0},
				{17, 4, 3, 0, 0, 0, 13, 0, 0, 0},
				{0, 3, 2, 0, 0, 9, 0, 12, 0, 12},
				{7, 19, 2, 0, 9, 0, 0, 4, 0, 15},
				{0, 0, 0, 13, 0, 0, 0, 18, 14, 16},
				{12, 0, 3, 0, 12, 4, 18, 0, 18, 0},
				{0, 10, 20, 0, 0, 0, 14, 18, 0, 0},
				{7, 15, 0, 0, 12, 15, 16, 0, 0, 0},};

		MST mst = new MST();
		//mst.Kruskal(G1);
		mst.Kruskal(G2);

	}

}
