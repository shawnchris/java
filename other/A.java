package other;

import java.util.*;
import java.util.stream.Collectors;

public class A {

    public int binaryGap(int N) {
        int prev = -1, index = 0, max = 0;
        while (N > 0) {
            if ((N & 1) == 1)  {
                if (prev == -1) {
                    prev = index;
                } else {
                    max = Math.max(max, index - prev);
                    prev = index;
                }
            }
            index++;
            N >>= 1;
        }
        return max;
    }

    public int[] advantageCount(int[] A, int[] B) {
        int n = A.length;
        List<Integer> a = new ArrayList<>();
        List<int[]> b = new ArrayList<>();
        for (int i : A) {
            a.add(i);
        }
        Collections.sort(a, (i, j) -> j - i);
        for (int i = 0; i < n; i++) {
            b.add(new int[] {B[i], i});
        }
        Collections.sort(b, (i, j) -> j[0] - i[0]);

        int[] res = new int[n];
        for (int[] p : b) {
            if (a.get(0) > p[0]) { // Use first
                res[p[1]] = a.remove(0);
            } else { // Use last
                res[p[1]] = a.remove(a.size() - 1);
            }
        }

        return res;
    }

    public boolean reorderedPowerOf2(int N) {
        List<String> power2 = new ArrayList<>();
        for (long i = 1; i <= Integer.MAX_VALUE; i *= 2) {
            power2.add(i + "");
        }

        int[] counts = new int[10];
        for (char c : (N + "").toCharArray()) {
            counts[c - '0']++;
        }

        for (String p : power2) {
            if (p.length() != (N + "").length()) continue;
            int[] counts2 = new int[10];
            for (char c : p.toCharArray()) {
                counts2[c - '0']++;
            }
            if (equals(counts, counts2)) return true;
        }

        return false;
    }

    private boolean equals(int[] a, int[] b) {
        for (int i = 0 ; i < a.length; i++) {
            if (a[i] != b[i]) return false;
        }
        return true;
    }

    public int minRefuelStops(int target, int startFuel, int[][] stations) {
        int step = 0;
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[] {-1, startFuel});

        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int[] curr = queue.poll();
                if (curr[1] > target) return step;

                for (int s = curr[0] + 1; s < stations.length; s++) {
                    if (stations[s][0] > curr[1]) break;
                    queue.add(new int[] {s, curr[1] + stations[s][1]});
                }
            }
            step++;
        }

        return -1;
    }

    public static void main(String[] args) {
        System.out.println();
        System.out.println(Integer.MAX_VALUE);
        A a = new A();
        System.out.println(a.minRefuelStops(100, 10, new int[][] {{10,60},{20,30},{30,30},{60,40}}));

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
        ListNode(int x) { val = x; }
    }
}
