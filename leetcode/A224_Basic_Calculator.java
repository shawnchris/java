package leetcode;
import java.util.*;

public class A224_Basic_Calculator {
    public int calculate(String s) {
        int len = s.length(), p = 0, q = 0;
        Stack<String> stack = new Stack<String>();
        stack.push("+");
        
        while (p < len) {
            char c = s.charAt(p);
            if (c == ' ') {
                p++;
                continue;
            }
            if (c == ')') {
                int n1 = 0, n2 = 0;
                String ss = "";
                do {
                    ss = stack.pop();
                    if (!ss.equals("(")) {
                        if (ss.equals("+") || ss.equals("-")) {
                            if (ss.equals("-"))
                                n2 *= -1;
                            n1 += n2;
                        }
                        else {
                            n2 = Integer.valueOf(ss);
                        }
                    }
                } while (!ss.equals("("));
                stack.push(n1+"");
                p++;
            }
            else if (c == '+' || c == '-' || c == '(') {
                stack.push(c+"");
                if (c == '(')
                    stack.push("+");
                p++;
            }
            else {
            	q = p;
                do
                	q++;
                while (q < len && Character.isDigit(s.charAt(q)));
                stack.push(s.substring(p, q));
                p = q;
            }
        }
        
        int n1 = 0, n2 = 0;
        String ss = "";
        do {
            ss = stack.pop();
            if (ss.equals("+") || ss.equals("-")) {
                if (ss.equals("-"))
                    n2 *= -1;
                n1 += n2;
            }
            else {
                n2 = Integer.valueOf(ss);
            }
        } while (!stack.isEmpty());
        
        return n1;
        
    }
}
