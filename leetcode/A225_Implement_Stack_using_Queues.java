package leetcode;
import java.util.*;

public class A225_Implement_Stack_using_Queues {
    Queue<Integer> q1 = new LinkedList<Integer>();
    Queue<Integer> q2 = null;
    
    // Push element x onto stack.
    public void push(int x) {
        q2 = new LinkedList<Integer>();
        q2.add(x);
        while (!q1.isEmpty())
            q2.add(q1.poll());
        q1 = q2;
    }

    // Removes the element on top of the stack.
    public void pop() {
        q1.poll();
    }

    // Get the top element.
    public int top() {
        return q1.peek();
    }

    // Return whether the stack is empty.
    public boolean empty() {
        return q1.isEmpty();
    }
}
