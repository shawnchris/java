package leetcode;
import java.util.*;

public class A451_Sort_Characters_By_Frequency {
	class Pair implements Comparable<Pair> {
        int n;
        char c;
        public Pair(char c, int n) {
            this.n = n; this.c = c;
        }
        public int compareTo(Pair that) {
            return this.n != that.n ? that.n - this.n : this.c - that.c;
        }
    }
    public String frequencySort(String s) {
        char[] array = s.toCharArray();
        Map<Character, Integer> freq = new HashMap<>();
        
        for (int i = 0; i < array.length; i++) {
            freq.put(array[i], freq.getOrDefault(array[i], 0) + 1);
        }
        
        PriorityQueue<Pair> pq = new PriorityQueue<>();
        for (char c : freq.keySet()) {
            pq.add(new Pair(c, freq.get(c)));
        }
        
        StringBuilder sb = new StringBuilder();
        while (!pq.isEmpty()) {
            Pair p = pq.poll();
            for (int i = 0; i < p.n; i++) {
                sb.append(p.c);
            }
        }
        
        return sb.toString();
    }
}
