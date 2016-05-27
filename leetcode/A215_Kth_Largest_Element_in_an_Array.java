package leetcode;
import java.util.*;

public class A215_Kth_Largest_Element_in_an_Array {
    public int findKthLargest(int[] nums, int k) {
        // Min heap
        Queue<Integer> pq = new PriorityQueue<>();
        for (int i = 0; i < k; i++)
            pq.add(nums[i]);
        for (int i = k; i < nums.length; i++) {
            if (nums[i] > pq.peek()) {
                pq.poll();
                pq.add(nums[i]);
            }
        }
        return pq.peek();
    }
}
