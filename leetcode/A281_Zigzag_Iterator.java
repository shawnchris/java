package leetcode;
import java.util.*;

public class A281_Zigzag_Iterator {
	public class ZigzagIterator {
	    Queue<Iterator<Integer>> queue;
	    
	    public ZigzagIterator(List<Integer> v1, List<Integer> v2) {
	        queue = new LinkedList<Iterator<Integer>>();
	        if (v1 != null && v1.size() != 0) queue.add(v1.iterator());
	        if (v2 != null && v2.size() != 0) queue.add(v2.iterator());
	    }

	    public int next() {
	        Iterator<Integer> it = queue.poll();
	        int result = it.next();
	        if (it.hasNext()) queue.add(it);
	        return result;
	    }

	    public boolean hasNext() {
	        return !queue.isEmpty();
	    }
	}
}
