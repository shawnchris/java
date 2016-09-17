package leetcode;

import java.util.*;

public class A377_Combination_Sum_IV {
    public int combinationSum41(int[] nums, int target) {
        if (nums == null || nums.length == 0) return 0;
        return helper1(nums, target, 0, new int[1])[0];
    }
    
    private int[] helper1(int[] nums, int target, int current, int[] result) {
    	// base cases
    	if (current == target) {
    		result[0]++;
    		return result;
    	}
    	if (current > target) return result;
    	
    	// backtrack
    	for (int i = 0; i < nums.length; i++) {
    		helper1(nums, target, current + nums[i], result);
    	}
    	
    	return result;
    }
    
    private int counter;
    
    public int combinationSum42(int[] nums, int target) {
        if (nums == null || nums.length == 0) return 0;
        //Arrays.sort(nums);
        counter = 0;
        helper(nums, target, 0);
        return counter;
    }
    
    private void helper(int[] nums, int target, int current) {
    	// backtrack
    	for (int i = 0; i < nums.length; i++) {
    		if (current + nums[i] > target) break;
    		if (current + nums[i] == target) {
    			counter++;
    			continue;
    		}
    		helper(nums, target, current + nums[i]);
    	}
    }
    
    public int combinationSum43(int[] nums, int target) {
        if (nums == null || nums.length == 0) return 0;
        Arrays.sort(nums);
        counter = 0;
        int[] stack = new int[2000];
        int level = 0, sum = 0;
        // backtrack
        while (level >= 0) {
        	stack[level]++;
        	if (stack[level] > nums.length) { //out of bound
        		stack[level] = 0;
        		level--;
        		if (level >= 0)
        			sum -= nums[stack[level] - 1];
        		continue;
        	}
        	sum += nums[stack[level] - 1];
        	if (sum >= target) { //end of this direction
        		if (sum == target) counter++;
        		sum -= nums[stack[level] - 1];
        		stack[level] = 0;
        		level--;
        		if (level >= 0)
        			sum -= nums[stack[level] - 1];
        		continue;
        	}
        	level++;
        }
        
        return counter;
    }
    
    public int combinationSum4(int[] nums, int target) {
        if (nums == null || nums.length == 0) return 0;
        Arrays.sort(nums);
        fact[0] = 1;
        for (int i = 1; i < 10; i++)
        	fact[i] = fact[i - 1] * i;
        counter = 0;
        helper(nums, 0, target, 0, new ArrayList<Integer>());
        return counter;
    }
    
    private void helper(int[] nums, int start, int target, int current, ArrayList<Integer> history) {
    	// base cases
    	if (current == target) {
    		counter += numPermutation(history);
    	}
    	if (current > target) return;
    	
    	// backtrack
    	for (int i = start; i < nums.length; i++) {
    		history.add(nums[i]);
    		helper(nums, i, target, current + nums[i], history);
    		history.remove(history.size() - 1);
    	}
    }
    
    private int numPermutation(ArrayList<Integer> list) {
    	int size = list.size();
    	int result = 1;
    	int i = 0, j = 0;
    	while (i < list.size() && size > 1) {
    		j = i + 1;
    		while (j < list.size() && list.get(i) == list.get(j)) j++;
    		
    		result *= choose(size, (j - i));
    		
    		size -= (j - i);
    		i = j;
    	}
    	return result;
    }
    private static long[] fact = new long[10];
    
    public static long choose(int x, int y) {
        if (y < 0 || y > x) return 0;
        if (y > x/2) {
            // choose(n,k) == choose(n,n-k), 
            // so this could save a little effort
            y = x - y;
        }

        double answer = 1.0;
        for (int i = 1; i <= y; i++) {
            answer *= (x + 1 - i);
            answer /= i;           // humor 280z80
        }
        return (long)answer;
    }

    
	public static void main(String[] args) {
		A377_Combination_Sum_IV cs = new A377_Combination_Sum_IV();
		int[] nums1 = {1, 2, 3};
		int target1 = 4;
		int[] nums2 = {1, 2, 3};
		int target2 = 32;
		int[] nums3 = {10,20,30,40,50,60,70,80,90,100,110,120,130,140,150,160,170,180,190,200,
				210,220,230,240,250,260,270,280,290,300,310,320,330,340,350,360,370,380,390,400,
				410,420,430,440,450,460,470,480,490,500,510,520,530,540,550,560,570,580,590,600,
				610,620,630,640,650,660,670,680,690,700,710,720,730,740,750,760,770,780,790,800,
				810,820,830,840,850,860,870,880,890,900,910,920,930,940,950,960,970,980,990,111};
		int target3 = 999;
		System.out.println(cs.combinationSum4(nums1, target1));
		Timer.start();
		System.out.println(cs.combinationSum4(nums2, target2));
		Timer.end();
		Timer.start();
		System.out.println(cs.combinationSum4(nums3, target3));
		Timer.end();
		/*fact[0] = 1;
        for (int i = 1; i < 10; i++)
        	fact[i] = fact[i - 1] * (i + 1);
		ArrayList<Integer> list = new ArrayList<>();
		list.add(1);
		list.add(2);
		list.add(3);
		System.out.println(cs.numPermutation(list));
		list.clear();
		list.add(1);
		list.add(1);
		list.add(3);
		System.out.println(cs.numPermutation(list));
		*/
		/*
		System.out.println(cs.combinationSum43(nums1, target1));
		System.out.println(cs.combinationSum41(nums1, target1));
		Timer.start();
		System.out.println(cs.combinationSum41(nums2, target2));
		Timer.end();
		Timer.start();
		System.out.println(cs.combinationSum42(nums2, target2));
		Timer.end();
		Timer.start();
		System.out.println(cs.combinationSum43(nums2, target2));
		Timer.end();
		*/
	}

}
