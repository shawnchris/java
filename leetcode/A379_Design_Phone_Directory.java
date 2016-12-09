package leetcode;
import java.util.*;

public class A379_Design_Phone_Directory {
	public class PhoneDirectory {

	    Set<Integer> pool;
	    /** Initialize your data structure here
	        @param maxNumbers - The maximum numbers that can be stored in the phone directory. */
	    public PhoneDirectory(int maxNumbers) {
	        pool = new LinkedHashSet<Integer>();
	        for (int i = 0; i < maxNumbers; i++) {
	            pool.add(i);
	        }
	    }
	    
	    /** Provide a number which is not assigned to anyone.
	        @return - Return an available number. Return -1 if none is available. */
	    public int get() {
	        if (pool.size() == 0) {
	            return -1;
	        }
	        else {
	            int res = pool.iterator().next();
	            pool.remove(res);
	            return res;
	        }
	    }
	    
	    /** Check if a number is available or not. */
	    public boolean check(int number) {
	        return pool.contains(number);
	    }
	    
	    /** Recycle or release a number. */
	    public void release(int number) {
	        pool.add(number);
	    }
	}
}
