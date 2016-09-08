package leetcode;
import java.util.*;

public class A284_Peeking_Iterator implements Iterator<Integer>{
    Iterator<Integer> iterator = null;
    Integer top = null;
    
	public A284_Peeking_Iterator(Iterator<Integer> iterator) {
	    // initialize any member here.
	    this.iterator = iterator;
	    if (iterator.hasNext())
	        top = iterator.next();
	}

    // Returns the next element in the iteration without advancing the iterator.
	public Integer peek() {
        if (top != null)
            return top;
        else
            throw new NoSuchElementException();
	}

	// hasNext() and next() should behave the same as in the Iterator interface.
	// Override them if needed.
	@Override
	public Integer next() {
        if (top != null) {
            Integer result = top;
            if (iterator.hasNext())
                top = iterator.next();
            else
                top = null;
            return result;
        }
        else
            throw new NoSuchElementException();
	}

	@Override
	public boolean hasNext() {
	    return top != null;
	}
}
