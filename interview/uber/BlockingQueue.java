package interview.uber;
import java.util.*;

interface MultiPutBlockingBoundedQueue
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

public class BlockingQueue implements MultiPutBlockingBoundedQueue {
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
    
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
