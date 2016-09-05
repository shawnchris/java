package leetcode;
//Election algorithm. O(n) O(1)
public class A169_Majority_Element {
    public int majorityElement(int[] nums) {
        int candidate = nums[0], count = 1;
        
        for (int i = 1; i < nums.length; i++) {
            if (count == 0) {
                candidate = nums[i];
                count++;
            }
            else {
                if (nums[i] == candidate)
                    count++;
                else
                    count--;
            }
        }
        
        return candidate;        
    }
}
