package leetcode;
import java.util.*;

public class A480_Sliding_Window_Median {
	PriorityQueue<Long> minHeap = new PriorityQueue<Long>();
	PriorityQueue<Long> maxHeap = new PriorityQueue<Long>(
        new Comparator<Long>() {
            public int compare(Long i1, Long i2) {
                return i2.compareTo(i1);
            }
        }
    );
	
	private void add(int num) {
		if (num < getMedian()) {
			maxHeap.add((long)num);
		}
		else {
			minHeap.add((long)num);
		}
		while(maxHeap.size() > minHeap.size()) {
            minHeap.add(maxHeap.poll());
		}
        while(minHeap.size() - maxHeap.size() > 1) {
            maxHeap.add(minHeap.poll());
        }
	}
	
	private void remove(int num) {
		if (num < getMedian()) {
			maxHeap.remove((long)num);
		}
		else {
			minHeap.remove((long)num);
		}
		while(maxHeap.size() > minHeap.size()) {
            minHeap.add(maxHeap.poll());
		}
        while(minHeap.size() - maxHeap.size() > 1) {
            maxHeap.add(minHeap.poll());
        }
	}
	
	private double getMedian() {
		if (maxHeap.size() == minHeap.size()) {
			return (maxHeap.peek() + minHeap.peek()) / 2.0;
		}
		else {
			return maxHeap.size() > minHeap.size() ? maxHeap.peek() : minHeap.peek();
		}
	}
	
	public double[] medianSlidingWindow(int[] nums, int k) {
		int n = nums.length - k + 1;
		if (n <= 0) return new double[0];
        double[] result = new double[n];
        
        if (k == 1) {
        	for (int i = 0; i < nums.length; i++) {
        		result[i] = nums[i];
        	}
        	return result;
        }

        int small = Math.min(nums[0], nums[1]);
        int large = Math.max(nums[0], nums[1]);
        minHeap.add((long)large);
        maxHeap.add((long)small);
        
        for (int i = 2; i <= nums.length; i++) {
        	if (i >= k) {
        		result[i - k] = getMedian();
        		remove(nums[i - k]);
        	}
        	if (i < nums.length) {
        		add(nums[i]);
        	}
        }
        
        return result;
    }
	public static void main(String[] args) {
		A480_Sliding_Window_Median sm = new A480_Sliding_Window_Median();
		int[] nums = {1,3,-1,-3,5,3,6,7};
		double[] r = sm.medianSlidingWindow(nums, 3);
		for (double d : r) {
			System.out.print(d + " ");
		}
		int[] nums2 = {Integer.MAX_VALUE, Integer.MAX_VALUE};
		System.out.println();
		double[] r2 = sm.medianSlidingWindow(nums2, 2);
		for (double d : r2) {
			System.out.print((int)d + " ");
		}
	}

}
