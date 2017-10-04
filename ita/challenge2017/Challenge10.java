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

  public static void main(String[] args) {
    String[] s1 = {"tom likes rose", "jim likes mary", "nobody likes me"};
    String[] q1 = {"tom", "likes", "him"};
    textQueries(s1, q1);
  }

}
