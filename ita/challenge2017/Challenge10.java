package ita.challenge2017;

import java.util.*;

public class Challenge10 {

  static int[] getMaxElementIndices(int[] a, int[] rotate) {
    // Find the max element
    int index = 0, max = a[0];
    for (int i = 1; i < a.length; i++) {
      if (a[i] > max) {
        max = a[i];
        index = i;
      }
    }

    int[] res = new int[rotate.length];
    // Rotate
    for (int i = 0; i < rotate.length; i++) {
      int r = rotate[i] % a.length;
      int s = index - r;
      if (s < 0) s += a.length;
      res[i] = s;
    }

    return res;
  }

  static String dnaComplement(String s) {
    StringBuilder sb = new StringBuilder(s).reverse();
    for (int i = 0; i < sb.length(); i++) {
      if (sb.charAt(i) == 'A') sb.setCharAt(i, 'T');
      else if (sb.charAt(i) == 'T') sb.setCharAt(i, 'A');
      else if (sb.charAt(i) == 'C') sb.setCharAt(i, 'G');
      else if (sb.charAt(i) == 'G') sb.setCharAt(i, 'C');
    }
    return sb.toString();
  }

  static long teamFormation(int[] score, int team, int m) {
    int n = score.length;
    long res = 0;
    if (n <= team) {
      for (int s : score) res += s;
      return res;
    }

    PriorityQueue<Integer> pq1 = new PriorityQueue<>((a, b) -> (b - a));
    PriorityQueue<Integer> pq2 = new PriorityQueue<>((a, b) -> (b - a));
    int left = 0, right = n - 1;
    while (left < m) {
      pq1.add(score[left]);
      left++;
    }
    while (right > left && right >= n - m) {
      pq2.add(score[right]);
      right--;
    }

    for (int i = 0; i < team; i++) {
      if (pq2.isEmpty() || !pq1.isEmpty() && pq1.peek() >= pq2.peek()) {
        res += pq1.poll();
        if (left < right) {
          pq1.add(score[left]);
          left++;
        }
      }
      else {
        res += pq2.poll();
        if (left < right) {
          pq2.add(score[right]);
          right--;
        }
      }
    }

    return res;
  }

  static int getMinimumMoves(int[] height) {
    int n = height.length;
    int[] dp = new int[n];
    dp[0] = 1;
    for (int i = 1; i < n; i++) {
      if (height[i] >= height[i - 1]) {
        dp[i] = dp[i - 1];
      }
      else {
        dp[i] = dp[i - 1] + 1;
      }
    }


    int j = n - 1, right = 0, min = dp[n - 1] + right;
    while (j >= 0) {
      right++;
      j--;
      while (j >= 0 && height[j] >= height[j + 1]) j--;
      int left = j >= 0 ? dp[j] : 0;
      min = Math.min(min, left + right);
    }

    return min;
  }

  static void textQueries(String[] sentences, String[] queries) {
    Map<String, Set<Integer>> map = new HashMap<>();
    for (int i = 0; i < sentences.length; i++) {
      String[] arr = sentences[i].split("\\s+");
      for (String ss : arr) {
        Set<Integer> set = map.getOrDefault(ss, new HashSet<Integer>());
        set.add(i);
        map.put(ss, set);
      }
    }

    for (String q : queries) {
      int[] mask = new int[sentences.length];
      String[] arr = q.split("\\s+");
      for (String ss : arr) {
        Set<Integer> set = map.get(ss);
        if (set == null) break;
        for (int i : set) mask[i]++;
      }

      // Print
      String res = "";
      for (int i = 0; i < mask.length; i++) {
        if (mask[i] == arr.length) {
          res += i + " ";
        }
      }

      if (res.length() == 0) res = "-1";
      System.out.println(res.trim());
    }
  }

  static int max = 0;
  static int[] cache = new int[10001];
  static int mod = 1000000000;
  static void drawLine(int[] lines) {
    if (max == 0) cache[0] = 1;
    for (int line : lines) {
      if (max < line) {
        for (int i = max + 1; i <= line; i++) {
          cache[i] = (cache[i - 1] + i) % mod;
        }
        max = line;
      }
      System.out.println(cache[line]);
    }
  }

  static class UnionFind {
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
      while (p != parent[p]) {
        parent[p] = parent[parent[p]];    // path compression by halving
        p = parent[p];
      }
      return p;
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
  static int zombieClusters(int[][] zombies) {
    int n = zombies.length;
    UnionFind uf = new UnionFind(n);

    for (int i = 0; i < zombies.length; i++) {
      for (int j = 0; j < zombies.length; j++) {
        if (i != j && zombies[i][j] == 1) {
          uf.union(i, j);
        }
      }
    }

    return uf.count;
  }

  static class Neighbor {
    int id; int weight;
    public Neighbor(int i, int w) {this.id = i; this.weight = w;}
  }
  static int matchTokens(int nodes, int[] from, int[] to, int[] weight) {
    Map<Integer, List<Neighbor>> graph = new HashMap<>();
    int n = from.length;
    for (int i = 0; i < n; i++) {
      List<Neighbor> nFrom = graph.getOrDefault(from[i], new ArrayList<>());
      List<Neighbor> nTo = graph.getOrDefault(to[i], new ArrayList<>());
      nFrom.add(new Neighbor(to[i], weight[i]));
      nTo.add(new Neighbor(from[i], weight[i]));
      graph.put(from[i], nFrom);
      graph.put(to[i], nTo);
    }

    int maxPaths = 0;
    Map<Integer, List<int[]>> pathToNodes = new HashMap<>();
    for (int i = 1; i <= nodes; i++) {
      for (int j = i + 1; j <= nodes; j++) {
        int paths = 0;
        for (Neighbor neighbor : graph.get(i)) {
          Set<Integer> visited = new HashSet<>();
          visited.add(i);
          if (hasPath(graph, visited, neighbor.id, j, neighbor.weight)) paths++;
        }
        maxPaths = Math.max(maxPaths, paths);

        List<int[]> list = pathToNodes.getOrDefault(paths, new ArrayList<>());
        list.add(new int[] {i, j});
        pathToNodes.put(paths, list);
      }
    }

    int res = 0;
    for (int[] pair : pathToNodes.get(maxPaths)) {
      res = Math.max(res, pair[0] * pair[1]);
    }
    return res;
  }
  static boolean hasPath(Map<Integer, List<Neighbor>> graph,
      Set<Integer> visited, int from, int to, int weight) {
    if (from == to) return true;
    if (visited.contains(from)) return false;

    visited.add(from);
    for (Neighbor neighbor : graph.get(from)) {
      if (neighbor.weight == weight && hasPath(graph, visited, neighbor.id, to, weight)) {
        return true;
      }
    }
    visited.remove(from);

    return false;
  }

  public static void main(String[] args) {
    // String[] s1 = {"jim likes mary", "kate likes tom", "tom does not like jim"};
    // String[] q1 = {"jim tom", "likes"};
    // String[] s2 = {"how it was done", "are you how", "it goes to", "goes done are it"};
    // String[] q2 = {"done it", "it"};
    // String[] s3 = {"it go will away", "go do art", "what to will east"};
    // String[] q3 = {"it will", "go east will", "will"};
    // textQueries(s1, q1);
    // textQueries(s2, q2);
    // textQueries(s3, q3);

    // int[] l1 = {3, 2, 4, 10000, 9999};
    // drawLine(l1);

    // int[][] z1 = {
    //     {1,1,0,0},
    //     {1,1,1,0},
    //     {0,1,1,0},
    //     {0,0,0,1}};
    // System.out.println(zombieClusters(z1));

    int[] from1 = {1, 1, 2, 2, 2};
    int[] to1 = {2, 2, 3, 3, 4};
    int[] weight1 = {1, 2, 1, 3, 3};
    System.out.println(matchTokens(4, from1, to1, weight1));
  }

}
