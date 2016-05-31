package leetcode;
import java.util.*;

public class A022_Generate_Parentheses {
    public List<String> generateParenthesis(int n) {
        List<String> result = new ArrayList<>();
        if (n == 0) return result;
        helper("", n, n, result);
        return result;
    }
    
    private void helper(String currentStr, int left, int right, List<String> result) {
        if (left > right) return; // No way to get a solution
        if (left == 0 && right == 0) { // find a solution
            result.add(currentStr);
            return;
        }
        
        if (left > 0) // Generate more left parenthesis
            helper(currentStr + "(", left - 1, right, result);
        if (right > 0) // Generate more right parenthesis
            helper(currentStr + ")", left, right - 1, result);
    }
}
