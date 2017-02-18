package other;
import java.util.*;

interface MyStream extends Iterator<Integer> {
	Iterator<Integer> iterator();
}

class StreamWrapper implements Comparable<StreamWrapper>, Iterator<Integer> {
	Integer next = null;
	Iterator<Integer> iterator;
	
	public StreamWrapper (MyStream stream) {
		iterator = stream.iterator();
		if (iterator.hasNext()) {
			next = iterator.next();
		}
	}
	
	public boolean hasNext() {
		return next != null;
	}
	
	public Integer next() {
		Integer result = next;
		if (iterator.hasNext()) {
			next = iterator.next();
		}
		else {
			next = null;
		}
		return result;
	}
	
	public int compareTo(StreamWrapper that) {
		return this.next - that.next;
	}
}

class StreamIterator implements Iterator<Integer> {
	PriorityQueue<StreamWrapper> pq;
	public StreamIterator(MyStream s1, MyStream s2) {
		StreamWrapper sw1 = new StreamWrapper(s1);
		StreamWrapper sw2 = new StreamWrapper(s2);
		pq = new PriorityQueue<>();
		if (sw1.hasNext()) pq.add(sw1);
		if (sw2.hasNext()) pq.add(sw2);
	}
	
	public boolean hasNext() {
		return !pq.isEmpty();
	}
	
	public Integer next() {
		StreamWrapper top = pq.poll();
		Integer result = top.next();
		if (top.hasNext()) pq.add(top);
		return result;
	}
}

public class Test2 {

	public static void main(String[] args) {
		System.out.println(true ^ false);
		byte[] array;
		byte b = 0b1111111;
		byte mask = 0b1111;
		b = (byte)(b & mask);
		System.out.println(b);
		
		String pattern = "[+-]?(([0-9]+)|([0-9]*(\\.[0-9]+)))(e[0-9]+)?";
		
		String[] ss = {"0", // true
				" 0.1 ", // true
				"abc", // false
				"1 a", // false
				"2e10", // true
				"11", // true
				"-e.1", // false
				"-5.2e1", // true
				"+", // false
				};
		
		for (String s : ss) {
			System.out.println(s.trim().matches(pattern));
		}
		
		Float.parseFloat("");
	}

}
