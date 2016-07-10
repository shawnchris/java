package interview.tintri;

import java.util.*;

interface MultiPutBlockingBoundedQueue {
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
	private boolean initialized = false;
	private int capacity = 0;
	private int size = 0;
	private Queue<Object> queue;
	
	public void init(int capacity) throws Exception {
		if (initialized)
			throw new Exception("Already initialized!");
		if (capacity <= 0)
			throw new IllegalArgumentException("Illegal capacity!");
		this.capacity = capacity;
		queue = new LinkedList<Object>();
		initialized = true;
	}

	public synchronized Object get() throws Exception {
		if (!initialized)
			throw new Exception("Haven't initialized!");
		
		while (size == 0) {
			wait();
		}
		
		Object obj = queue.poll();
		if (size == capacity)
			notifyAll();
		size--;
		return obj;
	}
	
	public synchronized void put(Object obj) throws Exception {
		if (!initialized)
			throw new Exception("Haven't initialized!");
		
		while (size == capacity) {
			wait();
		}
		
		queue.offer(obj);
		if (size == 0)
			notifyAll();
		size++;
		notifyAll();
	}
	
	/* version 1
	public synchronized Object get() throws Exception {
		if (!initialized)
			throw new Exception("Haven't initialized!");
		
		while (size == 0) {
			wait();
		}
		
		Object obj = queue.poll();
		size--;
		notifyAll();
		return obj;
	}
	
	public synchronized void put(Object obj) throws Exception {
		if (!initialized)
			throw new Exception("Haven't initialized!");
		
		while (size == capacity) {
			wait();
		}
		
		queue.offer(obj);
		size++;
		notifyAll();
	}
	*/
}

class Producer implements Runnable {
	MPBBQImpl queue;
	int count;
	public Producer(MPBBQImpl queue, int count) {
		this.queue = queue;
		this.count = count;
	}
	public void run() {
		for (int i = 0; i < count; i++) {
			try {
				queue.put(new Object());
				System.out.println(Thread.currentThread().getName() + ": produced #" + i);
			}
			catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}

class Consumer implements Runnable {
	MPBBQImpl queue;
	int count;
	public Consumer(MPBBQImpl queue, int count) {
		this.queue = queue;
		this.count = count;
	}
	public void run() {
		for (int i = 0; i < count; i++) {
			try {
				queue.get();
				System.out.println(Thread.currentThread().getName() + ": consumed #" + i);
			}
			catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}

public class Library {
	
	public static void main(String[] args) {
		try {
			MPBBQImpl queue1 = new MPBBQImpl();
			queue1.init(100);
			Thread p1 = new Thread(new Producer(queue1, 200));
			Thread c1 = new Thread(new Consumer(queue1, 200));
			p1.start();
			Thread.sleep(2000);
			c1.start();
			Thread.sleep(2000);
			
			MPBBQImpl queue2 = new MPBBQImpl();
			queue2.init(1000);
			Thread p2 = new Thread(new Producer(queue2, 500));
			Thread p3 = new Thread(new Producer(queue2, 500));
			Thread p4 = new Thread(new Producer(queue2, 500));
			Thread c2 = new Thread(new Consumer(queue2, 500));
			Thread c3 = new Thread(new Consumer(queue2, 1000));
			p2.start();
			p3.start();
			Thread.sleep(2000);
			c2.start();
			c3.start();
			Thread.sleep(2000);
			p4.start();
			Thread.sleep(2000);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
}
