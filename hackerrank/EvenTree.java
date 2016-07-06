package hackerrank;

import java.util.*;

public class EvenTree {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int n = in.nextInt();
		int m = in.nextInt();
		int edges[][] = new int[m][2];
		for (int i = 0; i < m; i++) {
			edges[i][0] = in.nextInt();
			edges[i][1] = in.nextInt();
		}
		
		int result = 0;
		for (int i = 0; i < m; i++) { // for w/o each edge
			// each cut will split the original tree into 2 trees
			// store the tree which node "1" resides in into set "tree"
			// another tree will leave in set "all"
			Set<Integer> all = new HashSet<Integer>();
			Set<Integer> tree = new HashSet<Integer>();
			for (int j = 2; j <= n; j++)
				all.add(j);
			tree.add(1);
			for (int j = 0; j < m; j++) { // for each edge
				if (j != i) {
					if (tree.contains(edges[j][1])) {
						tree.add(edges[j][0]);
						all.remove(edges[j][0]);
					}
				}
			}
			if (all.size()%2 == 0 && tree.size()%2 == 0)
				result++;
			
		}
		System.out.println(result);
		in.close();
	}

}
/*
10 9
2 1
3 1
4 3
5 2
6 1
7 2
8 6
9 8
10 8

*/
/*
2
*/