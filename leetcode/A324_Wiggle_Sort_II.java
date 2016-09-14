package leetcode;
import java.util.*;

public class A324_Wiggle_Sort_II {
    public void wiggleSort(int[] nums) {
    	int[] n = new int[nums.length];
    	for (int i = 0; i < nums.length; i++) n[i] = nums[i];
        Arrays.sort(n);
        int i = n.length - 1, j = 0, k = (n.length - 1) / 2 + 1;
        if (n.length % 2 != 0) {
        	nums[i] = n[j];
        	i--;
        	j++;
        }
        while (i >= 0) {
        	nums[i] = n[k];
        	i--;
        	k++;
        	nums[i] = n[j];
        	i--;
        	j++;
        }
    }
    
    private void print(int[] nums) {
    	for (int i = 0; i < nums.length; i++) {
    		System.out.print(nums[i] + " ");
    	}
    	System.out.println();
    }
    
    public static void main(String[] args) {
    	int[] nums1 = {1, 5, 1, 1, 6, 4};
    	int[] nums2 = {1, 3, 2, 2, 3, 1};
    	int[] nums3 = {4, 5, 5, 6};
    	A324_Wiggle_Sort_II ws = new A324_Wiggle_Sort_II();
    	ws.wiggleSort(nums1);
    	ws.wiggleSort(nums2);
    	ws.wiggleSort(nums3);
    	ws.print(nums1);
    	ws.print(nums2);
    	ws.print(nums3);
    }
}
