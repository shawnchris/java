package leetcode;
import java.util.*;

public class A032_Longest_Valid_Parentheses {
    public int longestValidParentheses(String s) {
        Stack<Integer> stack = new Stack<Integer>();
        int maxLen = 0;
        for (int i=0; i<s.length(); i++)
        {
            if (s.charAt(i) == '(')
                stack.push(i);
            else {
                if (!stack.empty() && s.charAt(stack.peek()) == '(') {
                    stack.pop();
                    int lastPos = -1;
                    if (!stack.isEmpty())
                        lastPos = stack.peek();
                    int curLen = i - lastPos;
                    maxLen = (maxLen < curLen) ? curLen : maxLen;
                }
                else stack.push(i);
            }
        }
        return maxLen;
    }
}
