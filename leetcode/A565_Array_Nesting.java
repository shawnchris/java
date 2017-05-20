package leetcode;

import java.util.HashMap;
import java.util.Map;

public class A565_Array_Nesting {
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

        public int getMaxUnion() {
            Map<Integer, Integer> map = new HashMap<>();
            int max = 1;
            for (int i = 0; i < parent.length; i++) {
                int p = find(i);
                map.put(p, map.getOrDefault(p, 0) + 1);
                max = Math.max(max, map.get(p));
            }
            return max;
        }
    }

    public int arrayNesting(int[] nums) {
        int n = nums.length;
        UnionFind uf = new UnionFind(n);
        for (int i = 0; i < n; i++) {
            uf.union(i, nums[i]);
        }
        return uf.getMaxUnion();
    }
}
