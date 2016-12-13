package leetcode;
import java.util.*;

public class A456_132_Pattern {
    public static boolean find132pattern(int[] nums) {
        if (nums == null || nums.length < 3) return false;
        
        TreeMap<Integer, Integer> map = new TreeMap<>();
        for (int i = 1; i < nums.length; i++) {
            if (map.containsKey(nums[i])) {
                map.put(nums[i], map.get(nums[i]) + 1);
            }
            else {
                map.put(nums[i], 1);
            }
        }
        
        int[] minLeft = new int[nums.length];
        int min = nums[0];
        for (int i = 1; i < nums.length; i++) {
            minLeft[i] = Math.min(min, nums[i - 1]);
        }
        
        for (int i = 1; i < nums.length - 1; i++) {
        	Integer count = map.get(nums[i]) - 1;
            if (count <= 0) {
                map.remove(nums[i]);
            }
            else {
                map.put(nums[i], count);
            }
            if (nums[i] > minLeft[i] && map.higherKey(minLeft[i]) != null && nums[i] > map.higherKey(minLeft[i]))
                return true;
        }
        
        return false;
    }
    
    // Stack solution
    /*  If stack is empty:
            push a new Pair of num into stack
        If stack is not empty:
            if num < stack.peek().min, push a new Pair of num into stack
            if num > stack.peek().min, we first pop() out the peek element, denoted as last
                if num < last.max, we are done, return true;
                if num >= last.max, we merge num into last, which means last.max = num.
                Once we update last, if stack is empty, we just push back last.
                If stack is not empty, the updated last might:
                    Entirely covered stack.peek(), i.e. last.min < stack.peek().min (which is always true) && 
                    last.max >= stack.peek().max, in which case we keep popping out stack.peek().
                    Form a 1-3-2 pattern, we are done ,return true
    */
    static class Pair {
        int max, min;
        public Pair(int max, int min) {
            this.max = max;
            this.min = min;
        }
    }
    public static boolean find132pattern2(int[] nums) {
        if (nums == null || nums.length < 3) return false;
        Stack<Pair> stack = new Stack<>();
        
        for (int num : nums) {
            if (stack.isEmpty()) {
                stack.push(new Pair(num, num));
            }
            else {
                if (num < stack.peek().min) {
                    stack.push(new Pair(num, num));
                }
                else if (num > stack.peek().min) {
                    Pair last = stack.pop();
                    if (last.max > num) {
                        return true;
                    }
                    else {
                        last.max = num;
                        if (stack.isEmpty()) {
                            stack.push(last);
                        }
                        else {
                            while (!stack.isEmpty() && stack.peek().max <= num) {
                                stack.pop();
                            }
                            if (!stack.isEmpty() && stack.peek().min < num) {
                                return true;
                            }
                            stack.push(last);
                        }
                    }
                }
            }
        }
        
        return false;
    }
    
	public static void main(String[] args) {
		System.out.println(find132pattern(new int[] {1, 2, 3, 4}));
		System.out.println(find132pattern(new int[] {3, 1, 4, 2}));
		System.out.println(find132pattern(new int[] {-1, 3, 2, 0}));
		System.out.println(find132pattern2(new int[] {1,0,1,-4,-3}));
		System.out.println(find132pattern2(new int[] {-2,1,-2}));
	}

}
