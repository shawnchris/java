package interview.linkedin;

import java.util.ArrayList;
import java.util.List;

public class MPBBQImpl implements MultiPutBlockingBoundedQueue {
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
