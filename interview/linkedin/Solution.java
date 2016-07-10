package interview.linkedin;

import java.util.*;

public class Solution {
	/*
	 *   threadSafe bounded blocking queue implementation.
	 *   Expected to be used in a Producer->Consumer pattern
	 */
	public interface MultiPutBlockingBoundedQueue
	{
	  /*
	   * Sets the capacity of the buffer. Can be called only once.
	   * If called more than once or if the passed capacity is <= 0,
	   * throw an exception.
	   */
	  public void init(int capacity) throws Exception;
	 
	  /*
	   * Get the next item from the queue
	   * throws Exception if not initialized
	   */
	  public Object get() throws Exception;
	 
	  /*
	   * Put the item to the tail of the queue.
	   * throws Exception if not initialized
	   */
	  public void put(Object obj) throws Exception;
	 
	  /*
	   * Put the list of items in an atomic manner.
	   * The list can be more than the capacity
	   * throws Exception if not initialized
	   */
	  //public void multiput(List objs) throws Exception;
	}

	class MPBBQImpl implements MultiPutBlockingBoundedQueue {
	    private boolean isInitalized = false;
	    private int size = 0;
	    private int capacity = 0;
	    private List<Object> list;
	    
	    public void init(int capacity) throws Exception {
	        isInitalized = true;
	        this.capacity = capacity;
	        list = new ArrayList<Object>(capacity);
	    }
	    
	    public synchronized Object get() throws Exception {
	        if (!isInitalized)
	            throw new Exception("No initalized!");
	        while (size == 0) {
	            wait();
	        }
	        Object res = list.get(0);
	        list.remove(0);
	        size--;
	        notify();
	        return res;
	    }
	    
	    public synchronized void put(Object obj) throws Exception {
	        if (!isInitalized)
	            throw new Exception("No initalized!");
	        
	        while (size == capacity) {
	            wait();
	        }
	        list.add(obj);
	        size++;
	        notify();
	    }
	    
	}

	/**
	 * Given a nested list of integers, returns the sum of all integers in the list weighted by their depth
	 * For example, given the list {{1,1},2,{1,1}} the function should return 10 (four 1's at depth 2, one 2 at depth 1)
	 * Given the list {1,{4,{6}}} the function should return 27 (one 1 at depth 1, one 4 at depth 2, and one 6 at depth 3)
	 */
	public int depthSum (List<NestedInteger> input)
	{
	    //Implementation here
	    return helper(input, 1);
	}

	public int helper(List<NestedInteger> input, int level) {
	    
	    int result = 0;
	    // Interate through the input
	    for (NestedInteger n : input) {
	        // Check if the element is an integer or an nested integer object
	        if (n.isInteger())
	            result += n.getInteger() * level;
	        else
	            result += helper(n.getList(), level + 1);
	    }
	    
	    return result;
	}
	 
	/**
	 * This is the interface that represents nested lists.
	 * You should not implement it, or speculate about its implementation.
	 */
	public interface NestedInteger
	{
	    /** @return true if this NestedInteger holds a single integer, rather than a nested list */
	    boolean isInteger();
	 
	    /** @return the single integer that this NestedInteger holds, if it holds a single integer
	     * Return null if this NestedInteger holds a nested list */
	    Integer getInteger();
	 
	    /** @return the nested list that this NestedInteger holds, if it holds a nested list
	     * Return null if this NestedInteger holds a single integer */
	    List<NestedInteger> getList();
	}
	
	public static void main(String[] args) {
		//System.out.println(myPow(2.0, 3));
		//System.out.println(myPow2(2.0, 3));
		//System.out.println(myPow(3.0, 8));
		//System.out.println(myPow2(3.0, 8));
	}

}
