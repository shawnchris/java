package other;

import java.util.*;

public class A {
    public int maxAncestorDiff(TreeNode root) {
        Integer left = root.left == null ? null : dfs(root.left, root.val, root.val);
        Integer right = root.right == null ? null : dfs(root.right, root.val, root.val);
        if (left == null) {
            return right;
        } else if (right == null) {
            return left;
        } else {
            return Math.max(left, right);
        }
    }

    private int dfs(TreeNode root, int min, int max) {
        int res = Math.max(Math.abs(root.val - min), Math.abs(root.val - max));
        if (root.left != null) {
            res = Math.max(res, dfs(root.left, Math.min(root.val, min), Math.max(root.val, max)));
        }
        if (root.right != null) {
            res = Math.max(res, dfs(root.right, Math.min(root.val, min), Math.max(root.val, max)));
        }
        return res;
    }

    public int longestArithSeqLength(int set[])
    {
        int n = set.length;
        if (n <= 2) return n;

        // Create a table and initialize all
        // values as 2. The value ofL[i][j] stores
        // LLAP with set[i] and set[j] as first two
        // elements of AP. Only valid entries are
        // the entries where j>i
        int L[][] = new int[n][n];

        // Initialize the result
        int llap = 2;

        // Fill entries in last column as 2.
        // There will always be two elements in
        // AP with last number of set as second
        // element in AP
        for (int i = 0; i < n; i++)
            L[i][n - 1] = 2;

        // Consider every element as second element of AP
        for (int j = n - 2; j >= 1; j--)
        {
            // Search for i and k for j
            int i = j -1 , k = j + 1;
            while (i >= 0 && k <= n - 1)
            {
                if (set[i] + set[k] < 2 * set[j])
                    k++;

                    // Before changing i, set L[i][j] as 2
                else if (set[i] + set[k] > 2 * set[j])
                {
                    L[i][j] = 2; i--;

                }

                else
                {
                    // Found i and k for j, LLAP with i and j as first two
                    // elements is equal to LLAP with j and k as first two
                    // elements plus 1. L[j][k] must have been filled
                    // before as we run the loop from right side
                    L[i][j] = L[j][k] + 1;

                    // Update overall LLAP, if needed
                    llap = Math.max(llap, L[i][j]);

                    // Change i and k to fill
                    // more L[i][j] values for current j
                    i--; k++;
                }
            }

            // If the loop was stopped due
            // to k becoming more than
            // n-1, set the remaining
            // entties in column j as 2
            while (i >= 0)
            {
                L[i][j] = 2;
                i--;
            }
        }
        return llap;
    }

    public boolean divisorGame(int N) {
        Boolean[] mem = new Boolean[N + 1];
        mem[1] = false;
        return helper(N, mem);
    }
    private boolean helper(int N, Boolean[] mem) {
        if (mem[N] == null) {
            for (int i = 1; i < N; i++) {
                if (N % i == 0) {
                    if (!helper(N - i, mem)) {
                        mem[N] = true;
                        break;
                    }
                }
            }
            mem[N] = false;
        }
        return mem[N];
    }

    public TreeNode recoverFromPreorder(String S) {
        Queue<int[]> queue = parse(S);
        return construct(queue, 0);
    }
    private TreeNode construct(Queue<int[]> queue, int depth) {
        if (queue.isEmpty()) {
            return null;
        }
        if (queue.peek()[0] != depth) {
            return null;
        }

        int[] n = queue.poll();
        TreeNode node = new TreeNode(n[1]);

        node.left = construct(queue, depth + 1);
        node.right = construct(queue, depth + 1);

        return node;
    }
    private Queue<int[]> parse(String S) {
        Queue<int[]> queue = new ArrayDeque<>();
        int i = 0, j = 0, k = 0;
        while (i < S.length()) {
            k = j;
            while (k < S.length() && S.charAt(k) != '-') k++;
            queue.add(new int[] {j - i, Integer.parseInt(S.substring(j, k))});

            i = k;
            j = k;
            while (j < S.length() && S.charAt(j) == '-') j++;
        }
        return queue;
    }

    public static void main(String[] args) {
        System.out.println(-1 % 4);
        System.out.println(Integer.MAX_VALUE);
        A a = new A();
        System.out.println(a.toNegativeBase(2, -2));
        System.out.println(a.toNegativeBase(3, -2));
        System.out.println(a.toNegativeBase(4, -2));
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
        queue.add(0); visited.add(0);

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

    // method to calculate gcd of two numbers
    static int gcd(int a, int b) {
        if (a == 0) return b;
        return gcd(b%a,a);
    }
}
