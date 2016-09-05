package leetcode;
import java.util.*;

public class A143_Reorder_List {
	public void reorderList(ListNode head) {
        
        if(head == null || head.next == null || head.next.next == null)return;

        ListNode a1 = head, a2 = head;

        // 1. find the middle point
        while(a2.next != null){
            // a1 step = 1
            a1 = a1.next;
            // a2 step = 2
            a2 = a2.next;
            if (a2.next == null) break;
            else a2 = a2.next;
        }
        // a1 now points to middle

        // 2. put the first half to a queue
        Queue<ListNode> q = new LinkedList<ListNode>();
        while (head != a1) {
            q.add(head);
            head = head.next;
        }

        // 3. put the 2nd half to a stack
        Stack<ListNode> s = new Stack<ListNode>();
        a2 = a1;
        while(a2 != null){
            s.push(a2);
            a2 = a2.next;
        }
        a1.next =  null;
        
        // 4. Merge queue and stack
        head = q.poll();
        a1 = head;
        while (!q.isEmpty() || !s.isEmpty()) {
            if (!s.isEmpty()) {
                a1.next = s.pop();
                a1 = a1.next;
            }
            if (!q.isEmpty()) {
                a1.next = q.poll();
                a1 = a1.next;
            }
        }
    }
}
