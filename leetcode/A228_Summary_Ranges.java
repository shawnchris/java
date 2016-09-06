package leetcode;
import java.util.*;

public class A228_Summary_Ranges {
    public List<String> summaryRanges(int[] nums) {
        List<String> result = new ArrayList<String>();
        int len = nums.length;
        if (nums == null || len == 0) return result;
        
        int i=0, j=0;
        String r = "";
        while (i < len) {
            j++;
            if (j >= len || (long)nums[j] - (long)nums[j-1] > 1) {
                if (j - i > 1)
                    r = nums[i] + "->" + nums[j-1];
                else
                    r = "" + nums[i] + "";
                result.add(r);
                i = j;
            }
        }
        
        return result;
    }
}
