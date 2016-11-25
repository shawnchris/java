package leetcode;
import java.util.*;

public class A362_Design_Hit_Counter {
	public class HitCounter {

	    int sum;
	    Deque<int[]> queue;
	    /** Initialize your data structure here. */
	    public HitCounter() {
	        sum = 0;
	        queue = new LinkedList<int[]>();
	    }
	    
	    /** Record a hit.
	        @param timestamp - The current timestamp (in seconds granularity). */
	    public void hit(int timestamp) {
	        if (!queue.isEmpty() && queue.peekLast()[0] == timestamp) {
	            queue.peekLast()[1]++;
	        }
	        else {
	            queue.add(new int[]{timestamp, 1});
	        }
	        sum++;
	        
	        while (timestamp - queue.peekFirst()[0] >= 300) {
	            sum -= queue.pollFirst()[1];
	        }
	    }
	    
	    /** Return the number of hits in the past 5 minutes.
	        @param timestamp - The current timestamp (in seconds granularity). */
	    public int getHits(int timestamp) {
	        if (queue.isEmpty()) return 0;
	        if (timestamp - queue.peekFirst()[0] < 300) {
	            return sum;
	        }
	        else {
	            int sum2 = sum;
	            Deque<int[]> queue2 = new LinkedList<>();
	            while (!queue.isEmpty()) {
	                queue2.add(queue.poll());
	                if (timestamp - queue2.peekLast()[0] >= 300) {
	                    sum2 -= queue2.peekLast()[1];
	                }
	            }
	            queue = queue2;
	            return sum2;
	        }
	    }
	}
	
	public class HitCounter2 {

	    int[] time;
	    int[] count;
	    
	    /** Initialize your data structure here. */
	    public HitCounter2() {
	        time = new int[300];
	        count = new int[300];
	    }
	    
	    /** Record a hit.
	        @param timestamp - The current timestamp (in seconds granularity). */
	    public void hit(int timestamp) {
	        int index = timestamp % 300;
	        if (time[index] != timestamp) {
	            time[index] = timestamp;
	            count[index] = 0;
	        }
	        count[index]++;
	    }
	    
	    /** Return the number of hits in the past 5 minutes.
	        @param timestamp - The current timestamp (in seconds granularity). */
	    public int getHits(int timestamp) {
	        int sum = 0;
	        for (int i = 0; i < 300; i++) {
	            if (timestamp - time[i] < 300) {
	                sum += count[i];
	            }
	        }
	        return sum;
	    }
	}
	
	
	public static void main(String[] args) {
		A362_Design_Hit_Counter dhc = new A362_Design_Hit_Counter();
		HitCounter ht1 = dhc.new HitCounter();
		ht1.hit(1);
		ht1.hit(1);
		ht1.hit(1);
		ht1.hit(300);
		System.out.println(ht1.getHits(300));
		ht1.hit(300);
		System.out.println(ht1.getHits(300));
		ht1.hit(301);
		System.out.println(ht1.getHits(301));
	}

}
