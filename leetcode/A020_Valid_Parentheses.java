package leetcode;
import java.util.*;

public class A020_Valid_Parentheses {
    public boolean isValid(String s) {
        if (s == null) return true;
        int len = s.length();
        
        Stack<Character> stack = new Stack<Character>();
        for (int i = 0; i < len; i++) {
            char c = s.charAt(i);
            if (stack.isEmpty())
                stack.push(c);
            else {
                char d = stack.peek();
                if (d == '(' && c == ')' || d == '{' && c == '}' || d == '[' && c == ']')
                    stack.pop();
                else
                    stack.push(c);
            }
        }
        
        if (stack.isEmpty()) return true;
        return false;
    }
}
