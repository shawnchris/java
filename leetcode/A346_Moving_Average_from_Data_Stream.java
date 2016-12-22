package leetcode;

public class A346_Moving_Average_from_Data_Stream {
	public class MovingAverage {
	    int capacity = 0, index = 0, total = 0, sum = 0;
	    int[] array;
	    
	    /** Initialize your data structure here. */
	    public MovingAverage(int size) {
	        capacity = size;
	        array = new int[size];
	    }
	    
	    public double next(int val) {
	        if (total < capacity) total++;
	        sum -= array[index];
	        sum += val;
	        array[index] = val;
	        index = (index + 1) % capacity;
	        
	        return (double)sum / total;
	    }
	}
}
