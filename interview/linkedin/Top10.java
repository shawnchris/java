package interview.linkedin;
import java.util.*;

public class Top10 {
	static final int size = 10;
	
	class Event implements Comparable<Event>{
		int eid;
		int count;
		public int compareTo(Event that) {
			return this.count - that.count;
		}
		public Event(int eid) {
			this.eid = eid;
			this.count = 0;
		}
		@Override
		public int hashCode() {
			return eid;
		}
	}
	
	Map<Integer, Event> map = new HashMap<>();
	PriorityQueue<Event> pq = new PriorityQueue<>();
	
	public void add(int eid) {
		Event e = map.getOrDefault(eid, new Event(eid));
		e.count++;
		map.put(eid, e);
		if (pq.contains(e)) return;
		
		if (pq.size() < size || e.count > pq.peek().count) {
			pq.add(e);
			if (pq.size() > size) {
				pq.poll();
			}
		}
	}
	
	public List<Integer> getTop10() {
		List<Integer> result = new ArrayList<>();
		PriorityQueue<Event> pq2 = new PriorityQueue<>(pq);
		while (!pq2.isEmpty()) {
			result.add(pq2.poll().eid);
		}
		return result;
	}
	
	public static void main(String[] args) {
		int[] events = {1,1,1,2,2,3,3,4,5,6,7,8,9,10,11,12,13,14,6,6,6,6,6};
		Top10 t = new Top10();
		for (int e : events) {
			t.add(e);
			System.out.println(t.getTop10());
		}
	}

}
