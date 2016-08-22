package leetcode;
import java.util.*;

public class A093_Restore_IP_Addresses {
    List<String> result = null;
    
    private void helper(String leftPart, String rightPart, int level) {
        if (level == 4) {
            if(rightPart.length() == 0)
                //substring here to get rid of last '.'
                result.add(leftPart.substring(0, leftPart.length() - 1));
            return;
        }
        for(int k = 1; k <= 3; k++) {
            if (rightPart.length() < k) continue;
            
            int val = Integer.parseInt(rightPart.substring(0, k));
            
            /*in the case 010, 00 the parseInt will return len=2 where val=10, but k=3, skip this.*/
            if (val > 255 || String.valueOf(val).length() != k) continue;
            
            helper(leftPart + rightPart.substring(0, k) + ".", rightPart.substring(k), level + 1);
        }
    }
    
    public List<String> restoreIpAddresses(String s) {
        result = new ArrayList<String>();
        if (s == null) return result;
        if (s.length() < 4) return result;
        
        helper("", s, 0);
        return result;
    }

}
