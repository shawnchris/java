package leetcode;

//Union-Find Solution
class UF {
    private int[] parent;
    private int[] rank;
    private int count;
    
    public UF (int numberOfElements) {
        parent = new int[numberOfElements];
        rank = new int[numberOfElements];
        count = numberOfElements;
        for (int i = 0; i < numberOfElements; i++) {
            parent[i] = i;
            rank[i] = 0;
        }
    }
    
    public int find(int p) {
        // my version of compression find
        if (parent[p] == p) return p;
        
        int q = p;
        while (parent[q] != q) {
            q = parent[q];
        }
        parent[p] = q;
        return q;
    }
    
    public void union(int p, int q) {
        int rootP = find(p);
        int rootQ = find(q);
        if (rootP == rootQ) return;
        
        if (rank[rootP] > rank[rootQ])
            parent[rootQ] = rootP;
        else if (rank[rootP] < rank[rootQ])
            parent[rootP] = rootQ;
        else {
            parent[rootQ] = rootP;
            rank[rootP]++;
        }
        count--;
    }
    
    public int count() {
        return count;
    }
    
}

public class A200_Number_of_Islands {
    public int numIslands(char[][] grid) {
        if (grid == null) return 0;
        int m = grid.length;
        if (m == 0) return 0;
        int n = grid[0].length;
        if (n == 0) return 0;
        
        int numZeros = 0;
        for (int i = 0; i < m; i++)
            for (int j = 0; j < n; j++)
                if (grid[i][j] == '0')
                    numZeros++;
        UF uf = new UF(m * n);
        for (int i = 0; i < m; i++)
            for (int j = 0; j < n; j++)
                if (grid[i][j] == '1') {
                    // check up
                    if (i - 1 >= 0 && grid[i - 1][j] == '1')
                        uf.union(i * n + j, (i - 1) * n + j);
                    // check left
                    if (j - 1 >= 0 && grid[i][j - 1] == '1')
                        uf.union(i * n + j, i * n + (j - 1));
                }
        
        return uf.count() - numZeros;
    }
}
