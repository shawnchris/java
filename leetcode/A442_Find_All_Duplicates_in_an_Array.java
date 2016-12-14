package leetcode;
import java.util.*;

public class A442_Find_All_Duplicates_in_an_Array {
    public List<Integer> findDuplicates(int[] nums) {
    	List<Integer> result = new ArrayList<Integer>();
    	
    	for (int i = 0; i < nums.length; i++) {
    		while (nums[i] != i + 1 && nums[i] != nums[nums[i] - 1]) {
    			swap(nums, i, nums[i] - 1);
    		}
    	}
    	
    	for (int i = 0; i < nums.length; i++) {
    		if (nums[i] - 1 != i) {
    			result.add(nums[i]);
    		}
    	}
    	
    	return result;
    }
    
    private void swap(int[] nums, int i, int j) {
    	int temp = nums[i];
    	nums[i] = nums[j];
    	nums[j] = temp;
    }
    
    public List<Integer> findDuplicates2(int[] nums) {
    	List<Integer> result = new ArrayList<Integer>();
    	
    	for (int i = 0; i < nums.length; i++) {
    		int index = Math.abs(nums[i]) - 1;
    		if (nums[index] < 0) {
    			result.add(index + 1);
    		}
    		nums[index] = -nums[index];
    	}
    	
    	return result;
    }
    
	public static void main(String[] args) {
		A442_Find_All_Duplicates_in_an_Array fa = new A442_Find_All_Duplicates_in_an_Array();
		System.out.println(fa.findDuplicates(new int[] {4,3,2,7,8,2,3,1}));
		System.out.println(fa.findDuplicates2(new int[] {4,3,2,7,8,2,3,1}));
	}

}
