package leetcode;
import java.util.Stack;

public class A150_Evaluate_Reverse_Polish_Notation {
public int evalRPN(String[] tokens) {
    Stack<Integer> stack = new Stack<Integer>();
    for (int i = 0; i < tokens.length; i++) {
        if (tokens[i].equals("+") || tokens[i].equals("-") || tokens[i].equals("*") || tokens[i].equals("/"))
            stack.push(calc(stack, tokens[i]));
        else
            stack.push(Integer.valueOf(tokens[i]));
    }
    return stack.pop();
}
public int calc(Stack<Integer> st, String op) {
    int j = st.pop();
    int i = st.pop();
    if (op.equals("+")) return i + j;
    if (op.equals("-")) return i - j;
    if (op.equals("*")) return i * j;
    if (op.equals("/")) return i / j;
    return 0;
}
}
