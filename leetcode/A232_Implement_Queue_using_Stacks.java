package leetcode;
import java.util.*;

public class A232_Implement_Queue_using_Stacks {
    Stack<Integer> s = new Stack<>();
    Stack<Integer> t = new Stack<>();
    private void ship() {
        while(!s.isEmpty())
            t.push(s.pop());
    }
    // Push element x to the back of queue.
    public void push(int x) {
        s.push(x);
    }

    // Removes the element from in front of queue.
    public void pop() {
        if (t.isEmpty())
            ship();
        t.pop();
    }

    // Get the front element.
    public int peek() {
        if (t.isEmpty())
            ship();
        return t.peek();
    }

    // Return whether the queue is empty.
    public boolean empty() {
        return s.isEmpty() && t.isEmpty();
    }
}
