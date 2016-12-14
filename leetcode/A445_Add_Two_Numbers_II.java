package leetcode;
import java.util.*;

public class A445_Add_Two_Numbers_II {
	public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        if (l1 == null) return l2;
        if (l2 == null) return l1;
        
        Stack<Integer> s1 = new Stack<>();
        Stack<Integer> s2 = new Stack<>();
        ListNode head = null, tail = null;
        
        while (l1 != null) {
            s1.push(l1.val);
            l1 = l1.next;
        }
        while (l2 != null) {
            s2.push(l2.val);
            l2 = l2.next;
        }
        
        int sum = 0;
        while (!s1.isEmpty() || !s2.isEmpty() || sum != 0) {
            if (!s1.isEmpty()) sum += s1.pop();
            if (!s2.isEmpty()) sum += s2.pop();
            head = new ListNode(sum % 10);
            head.next = tail;
            tail = head;
            sum = sum / 10;
        }
        
        return head;
    }
}
