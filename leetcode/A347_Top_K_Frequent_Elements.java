package leetcode;
import java.util.*;

public class A347_Top_K_Frequent_Elements {
	public List<Integer> topKFrequent(int[] nums, int k) {
        class Pair implements Comparable<Pair>{
            int key;
            int value;
            public Pair(int k, int v) {
                key = k;
                value = v;
            }
            @Override
            public int compareTo(Pair that) {
                return this.value - that.value;
            }
        }
        Map<Integer, Integer> map = new HashMap<>();
        for (int i: nums) {
            if (map.containsKey(i))
                map.put(i, map.get(i) + 1);
            else
                map.put(i, 1);
        }
        int c = 0;
        Queue<Pair> queue = new PriorityQueue<>();
        for (Integer key: map.keySet()) {
            c++;
            if (c <= k) {
                queue.add(new Pair(key, map.get(key)));
            }
            else {
                if (map.get(key) <= queue.peek().value)
                    continue;
                queue.poll();
                queue.add(new Pair(key, map.get(key)));
            }
        }
        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < k; i++)
            result.add(0, queue.poll().key);
        
        return result;
    }
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
