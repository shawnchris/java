package leetcode;
import java.util.*;

public class A031_Next_Permutation {
    public void nextPermutation(int[] nums) {
        int len = nums.length;
        if (len <= 1) return;
        
        boolean found = false;
        int idxL = -1, idxR = -1;
        // Search from right to left to see if there is any number has a "right greater"
        for (int i = len - 2; i >= 0 && !found; i--)
            for (int j = i + 1; j < len; j++)
                if (nums[i] < nums[j]) {
                    found = true;
                    if (idxR == -1) {
                        idxR = j;
                        idxL = i;
                    }
                    else if (nums[j] < nums[idxR]) {
                        idxR = j;
                        idxL = i;
                    }
                }
        // If found, idxL points to the left number, idxR points to the "smallest greater" number
        // Or, the array is sorted DESC, then we just need to sort it.
        if (!found)
            Arrays.sort(nums);
        else {
            // Swap the two numbers, and sort elements from idxL+1 to len-1
            swap(nums, idxL, idxR);
            for (int i = idxL + 1; i < len - 1; i++)
                for (int j = i + 1; j < len; j++)
                    if (nums[i] > nums[j])
                        swap(nums, i, j);
        }
    }
    
    private void swap(int[] a, int i, int j) {
        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }
}
