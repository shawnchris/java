package leetcode;
import java.util.*;

public class A071_Simplify_Path {
    public String simplifyPath(String path) {
        if (path == null) return null;
        String result = "";
        
        String[] arr = path.split("/");
        Stack<String> stack = new Stack<>();
        
        for (String s : arr) {
            if (s.length() == 0 || s.equals("/") || s.equals(".")) {
                continue;
            }
            if (s.equals("..")) {
            	if (!stack.isEmpty()) {
            		stack.pop();
            	}
            }
            else {
                stack.push(s);
            }
        }
        
        if (stack.isEmpty()) {
            result = "/";
        }
        else {
            while (!stack.isEmpty())
            result = "/" + stack.pop() + result;
        }
        
        return result;
    }
}
