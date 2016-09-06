package leetcode;
import java.util.*;

public class A229_Majority_Element_II {
    public List<Integer> majorityElement(int[] nums) {
        List<Integer> result = new ArrayList<Integer>();
        int len = nums.length;
        if (nums == null || len == 0) return result;
        int candi1 = 1, candi2 = 2, count1 = 0, count2 = 0;
        for (int num : nums) {
            if (num == candi1) count1++;
            else if (num == candi2) count2++;
            else if (count1 == 0) {candi1 = num; count1++;}
            else if (count2 == 0) {candi2 = num; count2++;}
            else {
                count1--;
                count2--;
            }
        }
        count1 = 0; count2 = 0;
        for(int num : nums) {
            if(num == candi1) count1++;
            if(num == candi2) count2++;
        }
        if(count1 > len / 3) result.add(candi1);
        if(count2 > len / 3) result.add(candi2);
        
        return result;
    }
}
