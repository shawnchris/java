package interview.uber;
import java.util.*;

public class API_Wrapper {
	private Queue<Long> queue = new LinkedList<>();
	
	public boolean wapper() {
		long ts = System.currentTimeMillis();
		if (queue.size() < 10) {
			queue.add(ts);
			return true;
		}
		
		if (ts - queue.peek() <= 10000)
			return false;
		
		while (ts - queue.peek() > 10000)
			queue.poll();
		queue.add(ts);
		return true;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
