package leetcode;
import java.util.*;

//Two heaps, one min-heap, one max-heap
public class A295_Find_Median_from_Data_Stream {
	class MedianFinder {

	    Queue<Integer> maxHeap;
	    Queue<Integer> minHeap;
	    
	    // Constructer
	    MedianFinder() {
	        minHeap = new PriorityQueue<Integer>();
	        maxHeap = new PriorityQueue<Integer>(
	            new Comparator<Integer>() {
	                public int compare(Integer i1, Integer i2) {
	                    return i2 - i1;
	                }
	            }
	        );
	    }
	    
	    // Adds a number into the data structure.
	    public void addNum(int num) {
	        double median = findMedian();
	        if (num <= median)
	            maxHeap.add(num);
	        else
	            minHeap.add(num);
	        
	        while(maxHeap.size() - minHeap.size() > 1)
	            minHeap.add(maxHeap.poll());

	        while(minHeap.size() - maxHeap.size() > 1)
	            maxHeap.add(minHeap.poll());
	    }

	    // Returns the median of current data stream
	    public double findMedian() {
	        if (maxHeap.isEmpty() && minHeap.isEmpty()) return 0;
	        
	        int maxLen = maxHeap.size(), minLen = minHeap.size();
	        if (maxLen == minLen)
	            return (maxHeap.peek() + minHeap.peek()) / 2.0;
	        if (maxLen < minLen) 
	            return minHeap.peek();
	        else
	            return maxHeap.peek();
	    }
	}
}
