package leetcode;
import java.util.*;

public class A281_Zigzag_Iterator {
	class ZigzagIterator {
	    List<Integer> v1, v2;
	    int p1, p2, size1 = 0, size2 = 0;
	    boolean first = true;
	    public ZigzagIterator(List<Integer> v1, List<Integer> v2) {
	        this.v1 = v1;
	        this.v2 = v2;
	        p1 = 0;
	        p2 = 0;
	        if (v1 != null) size1 = v1.size();
	        if (v2 != null) size2 = v2.size();
	    }

	    public int next() {
	        if (first && p1 >= size1) first = false;
	        if (!first && p2 >= size2) first = true;
	        
	        int result = 0;
	        if (first) {
	            result = v1.get(p1);
	            p1++;
	        }
	        else {
	            result = v2.get(p2);
	            p2++;
	        }
	        first = !first;
	        
	        return result;
	    }

	    public boolean hasNext() {
	        if (p1 == size1 && p2 == size2) return false;
	        return true;
	    }
	}
}
