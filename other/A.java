package other;

import java.util.*;

public class A {

    public String thousandSeparator(int n) {
        if (n == 0) {
            return "0";
        }
        StringBuilder sb = new StringBuilder();
        int c = 0;
        while (n > 0) {
            sb.insert(0, n % 10);
            n /= 10;
            c++;
            if (c == 3 && n > 0) {
                sb.insert(0, ".");
                c = 0;
            }
        }
        return sb.toString();
    }

    public List<Integer> findSmallestSetOfVertices(int n, List<List<Integer>> edges) {
        int[] inDegrees = new int[n];
        for (List<Integer> edge : edges) {
            inDegrees[edge.get(1)]++;
        }
        List<Integer> res = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (inDegrees[i] == 0) {
                res.add(i);
            }
        }
        return res;
    }

    public int minOperations(int[] nums) {
        int res = 0;
        int max = 0;
        for (int num : nums) {
            int[] r = div(num);
            max = Math.max(max, r[1]);
            res += r[0];
        }
        return res + max;
    }
    private int[] div (int num) {
        int[] res = new int[2];
        while (num > 0) {
            if (num % 2 == 0) {
                num = num / 2;
                res[1]++;
            } else {
                num -= 1;
                res[0]++;
            }
        }
        return res;
    }

    public boolean containsCycle(char[][] grid) {
        int m = grid.length, n = grid[0].length;
        UF uf = new UF(m * n);

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (i > 0 && grid[i][j] == grid[i - 1][j]) {
                    if (uf.find(i * n + j) == uf.find((i - 1) * n + j)) {
                        return true;
                    } else {
                        uf.union(i * n + j, (i - 1) * n + j);
                    }
                }
                if (j > 0 && grid[i][j] == grid[i][j - 1]) {
                    if (uf.find(i * n + j) == uf.find(i * n + j - 1)) {
                        return true;
                    } else {
                        uf.union(i * n + j, i * n + j - 1);
                    }
                }
            }
        }

        return false;
    }


    public static void main(String[] args) {
        System.out.println(-1 % 4);
        System.out.println(Integer.MAX_VALUE);
        System.out.println(Integer.MIN_VALUE);
        A a = new A();
        System.out.println(a);
    }

    public int fromNegativeBase(String str, int negBase) {
        int res = 0;
        for (int i = 0; i < str.length(); i++) {
            res += Integer.valueOf(str.substring(str.length() - i - 1, str.length() - i)) * Math.pow(negBase, i);
        }
        return res;
    }

    public String toNegativeBase(int n, int negBase) {
        //  If n is zero then in any base it will be 0 only
        if (n == 0) return "0";

        String converted = "";
        while (n != 0) {
            // Get remainder by negative base, it can be negative also
            int remainder = n % negBase;
            n /= negBase;

            // if remainder is negative, add abs(base) to it and add 1 to n
            if (remainder < 0) {
                remainder += (-negBase);
                n += 1;
            }

            // convert remainder to string add into the result
            converted = remainder + converted;
        }

        return converted;
    }

    public int orangesRotting(int[][] A) {
        int m = A.length, n = A[0].length;
        Queue<Integer> queue = new ArrayDeque<>();
        Set<Integer> visited = new HashSet<>();

        int fresh = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (A[i][j] == 2) {
                    queue.add(i * n + j);
                    visited.add(i * n + j);
                } else if (A[i][j] == 1) {
                    fresh++;
                }
            }
        }

        if (queue.isEmpty()) {
            return -1;
        }
        if (fresh == 0) {
            return 0;
        }

        int step = 0;
        int[][] dirs = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
        while (!queue.isEmpty()) {
            step++;
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int p = queue.poll();
                int r = p / n;
                int c = p % n;
                for (int[] d : dirs) {
                    int nr = r + d[0];
                    int nc = c + d[1];
                    if (nr >= m || nr < 0 || nc >= n || nc < 0 || A[nr][nc] != 1 || visited.contains(nr * n + nc)) {
                        continue;
                    }
                    A[nr][nc] = 2;
                    fresh--;
                    visited.add(nr * n + nc);
                    queue.add(nr * n + nc);
                }
            }
        }

        return fresh == 0 ? step - 1 : -1;
    }

    private int bfs(int[][] A) {
        int m = A.length, n = A[0].length;
        Queue<Integer> queue = new ArrayDeque<>();
        Set<Integer> visited = new HashSet<>();
        queue.add(0);
        visited.add(0);

        int step = 0;
        int[][] dirs = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
        while (!queue.isEmpty()) {
            step++;
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int p = queue.poll();
                int r = p / n;
                int c = p % n;
                for (int[] d : dirs) {
                    int nr = r + d[0];
                    int nc = c + d[1];
                    if (nr >= m || nr < 0 || nc >= n || nc < 0 || visited.contains(nr * n + nc)) continue;
                    if (A[nr][nc] == 1) {
                        if (false) return step;
                        continue;
                    }
                    queue.add(nr * n + nc);
                }
            }
        }

        return -1;
    }

    private Map<Integer, int[]> getMap() {
        Map<Integer, int[]> adj = new HashMap<>();
        adj.put(1, new int[]{6, 8});
        adj.put(2, new int[]{9, 7});
        adj.put(3, new int[]{4, 8});
        adj.put(4, new int[]{3, 9, 0});
        adj.put(5, new int[]{});
        adj.put(6, new int[]{1, 7, 0});
        adj.put(7, new int[]{6, 2});
        adj.put(8, new int[]{3, 1});
        adj.put(9, new int[]{4, 2});
        adj.put(0, new int[]{6, 4});
        return adj;
    }

    private void print(int[] a) {
        for (int i : a) {
            System.out.print(i + "\t");
        }
        System.out.println();
    }

    private void print(int[][] a) {
        for (int[] b : a) {
            print(b);
        }
    }

    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    public class Interval {
        int start;
        int end;

        Interval() {
            start = 0;
            end = 0;
        }

        Interval(int s, int e) {
            start = s;
            end = e;
        }

        @Override
        public String toString() {
            return "[" + start + "," + end + "]";
        }
    }

    static class Pair extends Object {
        int a, b;

        Pair(int a, int b) {
            this.a = a;
            this.b = b;
        }
//        @Override
//        public int hashCode() {
//            return
//        }
    }

    class UF {
        private int[] parent;
        private int[] rank;
        private int count;

        public UF(int numberOfElements) {
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

    static class TrieNode {
        private static final int R = 26;
        public TrieNode[] next;
        public int isWord;
        public TrieNode() {
            next = new TrieNode[R];
            isWord = 0;
        }
    }

    private void printArray(int[] a) {
        for (int i = 0; i < a.length; i++) {
            System.out.print(a[i] + "\t");
        }
        System.out.println();
    }

    // method to calculate gcd of two numbers
    static int gcd(int a, int b) {
        if (a == 0) return b;
        return gcd(b % a, a);
    }

    static boolean colLine(int x1, int y1, int x2, int y2, int x3, int y3) {
        if ((y3 - y2) * (x2 - x1) ==
                (y2 - y1) * (x3 - x2))
            return true;
        else
            return false;
    }
}
