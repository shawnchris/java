package leetcode;
import java.util.*;

public class A359_Logger_Rate_Limiter {
	public class Logger {
	    Queue<String> qm;
	    Queue<Integer> qt;
	    Set<String> set;
	    
	    /** Initialize your data structure here. */
	    public Logger() {
	        qm = new LinkedList<String>();
	        qt = new LinkedList<Integer>();
	        set = new HashSet<String>();
	    }
	    
	    /** Returns true if the message should be printed in the given timestamp, otherwise returns false.
	        If this method returns false, the message will not be printed.
	        The timestamp is in seconds granularity. */
	    public boolean shouldPrintMessage(int timestamp, String message) {
	        // Remove old messages
	        while (!qt.isEmpty() && timestamp - qt.peek() >= 10) {
	            qt.poll();
	            set.remove(qm.poll());
	        }
	        // Add new message
	        if (set.contains(message)) { // Ignore
	            return false;
	        }
	        else { // Add
	            qt.add(timestamp);
	            qm.add(message);
	            set.add(message);
	            return true;
	        }
	    }
	}
}
