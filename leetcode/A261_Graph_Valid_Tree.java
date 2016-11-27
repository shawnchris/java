package leetcode;
import java.util.*;

public class A261_Graph_Valid_Tree {
	public boolean validTree(int n, int[][] edges) {
        int m = edges.length;
        if (n - 1 != m) return false;
        if (n == 1) return true;
        
        int[] count = new int[n];
        Set<Integer>[] neigbour = new Set[n];
        for (int i = 0; i < n; i++) {
            neigbour[i] = new HashSet<Integer>();
        }
        for (int[] edge : edges) {
            count[edge[0]]++;
            count[edge[1]]++;
            neigbour[edge[0]].add(edge[1]);
            neigbour[edge[1]].add(edge[0]);
        }
        
        Queue<Integer> queue = new LinkedList<>();
        Set<Integer> visited = new HashSet<>();
        for (int i = 0; i < n; i++) {
            if (count[i] == 0) return false;
            if (count[i] == 1) {
                queue.add(i);
                //visited.add(i);
            }
        }
        
        while (!queue.isEmpty()) {
            int curr = queue.poll();
            visited.add(curr);
            for (int next : neigbour[curr]) {
                neigbour[curr].remove(next);
                neigbour[next].remove(curr);
                count[curr]--;
                count[next]--;
                if (count[next] == 1) {
                    queue.add(next);
                }
            }
        }
        
        return visited.size() == n;
    }
}
