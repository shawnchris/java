package leetcode;
import java.util.*;

public class A239_Sliding_Window_Maximum {
    // Monoqueue solution
	class MonoQueue {
		Deque<Integer> value = new LinkedList<>();
		Deque<Integer> count = new LinkedList<>();
		
		public void push(int val) {
			int c = 1;
            while(!value.isEmpty() && value.peekLast() < val) {
                c += count.peekLast();
                value.pollLast();
                count.pollLast();
            }
            value.addLast(val);
            count.addLast(c);
		}
		
		public int max() {
            return value.peek();
        }
		
		public void pop() {
		    int c = count.pollFirst() - 1;
		    if (c > 0) {
		        count.addFirst(c);
		    }
		    else {
		        value.pollFirst();
		    }
        }
	}

	public int[] maxSlidingWindow(int[] nums, int k) {
        int len = nums.length;
        if (len == 0)
            return new int[]{};
        if (len <= k) {
            Arrays.sort(nums);
            return new int[]{nums[len-1]};
        }
        
        int[] result = new int[len - k + 1];
        MonoQueue mq = new MonoQueue();
        for (int i = 0; i < k; i++)
            mq.push(nums[i]);

        result[0] = mq.max();
        for (int i = k; i < len; i++) {
        	mq.pop();
            mq.push(nums[i]);
            result[i-k+1] = mq.max();
        }
        
        return result;
    }
}
