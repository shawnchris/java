package leetcode;
import java.util.*;

public class A398_Random_Pick_Index {
	class ArrayIndexComparator implements Comparator<Integer> {
	    private final int[] array;

	    public ArrayIndexComparator(int[] array)
	    {
	        this.array = array;
	    }

	    public Integer[] createIndexArray()
	    {
	        Integer[] indexes = new Integer[array.length];
	        for (int i = 0; i < array.length; i++)
	        {
	            indexes[i] = i; // Autoboxing
	        }
	        return indexes;
	    }

	    @Override
	    public int compare(Integer index1, Integer index2)
	    {
	         // Autounbox from Integer to int to use as array indexes
	        return Integer.valueOf(array[index1]).compareTo(Integer.valueOf(array[index2]));
	    }
	}
	
	private int[] nums;
	private Integer[] indexes;
	private Random rnd = new Random();
	private boolean sorted = true;
	
    public A398_Random_Pick_Index(int[] nums) {
        if (nums.length > 10000) {
            this.nums = nums;
            sorted = false;
            return;
        }
    	ArrayIndexComparator comparator = new ArrayIndexComparator(nums);
    	indexes = comparator.createIndexArray();
    	Arrays.sort(indexes, comparator);
        Arrays.sort(nums);
        this.nums = nums;
    }
    
    public int pick(int target) {
        if (sorted) {
        	int l = findStart(nums, target);
        	int r = findEnd(nums, target);
    	    return indexes[l + rnd.nextInt(r - l + 1)];
        }
        else {
            List<Integer> list = new ArrayList<>();
            for (int i = 0; i < nums.length; i++) {
                if (nums[i] == target) {
                    list.add(i);
                }
            }
            return list.get(rnd.nextInt(list.size()));
        }
    }
    
    private int findStart(int[] nums, int target) {
        int start = 0, end = nums.length - 1, mid = 0;
        while (start + 1 < end) {
            mid = start + (end - start) / 2;
            if (nums[mid] < target) {
                start = mid;
            }
            else {
                end = mid;
            }
        }
        
        if (nums[start] == target)
            return start;
        if (nums[end] == target)
            return end;
        return -1;
    }
    
    private int findEnd(int[] nums, int target) {
        int start = 0, end = nums.length - 1, mid = 0;
        while (start + 1 < end) {
            mid = start + (end - start) / 2;
            if (nums[mid] <= target) {
                start = mid;
            }
            else {
                end = mid;
            }
        }
        
        if (nums[end] == target)
            return end;
        if (nums[start] == target)
            return start;
        return -1;
    }
    public static void main(String[] args) {
    	int[] nums1 = {1,2,3,3,3};
    	A398_Random_Pick_Index rpi = new A398_Random_Pick_Index(nums1);
    	System.out.println(rpi.pick(3));
    	System.out.println(rpi.pick(3));
    	System.out.println(rpi.pick(3));
    	System.out.println(rpi.pick(3));
    	System.out.println(rpi.pick(1));
    	System.out.println(rpi.pick(1));
    	System.out.println(rpi.pick(1));
    	System.out.println(rpi.pick(2));
    	
    }
}

