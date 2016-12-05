package leetcode;

public class A346_Moving_Average_from_Data_Stream {
	class MovingAverage {
	    int[] buf;
	    int size, ptr = 0, sum = 0, count = 0;
	    /** Initialize your data structure here. */
	    public MovingAverage(int size) {
	        this.size = size;
	        buf = new int[size];
	    }
	    
	    public double next(int val) {
	        sum -= buf[ptr];
	        sum += val;
	        buf[ptr] = val;
	        ptr++;
	        if (ptr >= size) ptr = 0;
	        count++;
	        if (count > size) count = size;
	        return (double)sum / count;
	    }
	}
}
