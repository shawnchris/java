package interview.uber;
import java.util.*;

/*
 * Implement a rate limiter which only allow 10 requests per second.
 * Return true if request is allowed, otherwise return false.
 */

public class API_Wrapper {
	// Queue solution
	private Queue<Long> queue = new LinkedList<>();
	public boolean wrapper1() {
		long ts = System.currentTimeMillis();
		if (queue.size() < 10) {
			queue.add(ts);
			return true;
		}
		
		if (ts - queue.peek() <= 1000)
			return false;
		
		while (ts - queue.peek() > 1000)
			queue.poll();
		queue.add(ts);
		return true;
	}
	
	// Circular array solution
	private long[] ca = new long[10];
	int ptr = 0;
	public boolean wrapper2() {
		long ts = System.currentTimeMillis();
		if (ts - ca[(ptr + 1) % 10] > 1000) {
			ca[ptr] = ts;
			ptr = (ptr + 1) % 10;
			return true;
		}
		return false;
	}
	
	// How to handle 10^9 queries per hour?
	// 1 hour = 3600 seconds. Each second -> one bucket
	// Package 3*10^5 queries as one.
	
	// How to support multiple threads? Lock
	
	// How to support limit by user? Store user id
	
	// How to support query with different quotas? Acquire(quota)
	
	//
	
	
	public static void main(String[] args) {
		System.out.println((9 - 9) % 10);
	}

}
