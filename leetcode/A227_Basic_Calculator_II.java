package leetcode;
import java.util.*;

public class A227_Basic_Calculator_II {
    public int calculate(String s) {
        int len = s.length(), p = 0, q = 0, result = 0;
        Stack<String> stack = new Stack<String>();
        
        while (p < len) {
            char c = s.charAt(p);
            if (c == ' ') {
                p++;
                continue;
            }
            if (c == '*' || c == '/') {
                int n1 = Integer.valueOf(stack.pop());
                do
                    p++;
                while (s.charAt(p) == ' ');
                q = p;
                do
                	q++;
                while (q < len && Character.isDigit(s.charAt(q)));
                int n2 = Integer.valueOf(s.substring(p, q));
                if (c == '*')
                    stack.push((n1 * n2) + "");
                else
                    stack.push((n1 / n2) + "");
                p = q;
            }
            else if (c == '+' || c == '-') {
                stack.push(c+"");
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

        while (!stack.isEmpty()) {
            int temp = Integer.valueOf(stack.pop());
            if (!stack.isEmpty()) {
                String sign = stack.pop();
                if (sign.equals("-"))
                    temp *= -1;
            }
            result += temp;
        }
        
        return result;
    }
}
