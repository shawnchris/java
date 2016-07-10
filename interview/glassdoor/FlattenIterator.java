package interview.glassdoor;

import java.util.*;

public class FlattenIterator {
	/*
	public static Iterator flattenIterator(Iterator<Iterator> nested) {
	    return new FIterator(nested);
	}
	*/

	class FIterator {
	    Iterator currentIterator = null;
	    Iterator<Iterator> nested = null;

	    public FIterator(Iterator<Iterator> nested) {
	        this.nested = nested;
	    }

	    public boolean hasNext() {
	        while ( ((null != currentIterator) && (!currentIterator.hasNext())) ||
	                ((null != currentIterator) && (nested.hasNext()))) {
	            currentIterator = nested.next();
	        }
	        return (currentIterator != null);
	    }

	    public Object next() {
	        if (!hasNext()) return null;
	        return currentIterator.next();
	    }
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	//newton-raphson
		/*public static float findzero()
		{
		    float delta = 0.01f;
		    float epsilon = 0.00001f;
		    float x = 0.0f;
		    float fxn = f(x);
		    while (Math.abs(fxn) > epsilon) {
		        float fxnp = (f(x+delta) - f(x-delta)) / (2 * delta);
		        x = x - fxn / fxnp;
		        fxn = f(x);
		    }
		    return x;
		}*/
}
