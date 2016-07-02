package leetcode;
import java.util.*;

public class A025_Reverse_Nodes_in_k_Group {
	public ListNode reverseKGroup(ListNode head, int k) {
        if (k<2 || head==null || head.next==null) return head;
        
        Stack<ListNode> stack = new Stack<ListNode>();
        int c=0;
        ListNode first = new ListNode(0);
        ListNode pf=first, tail=head;
        
        while(tail!=null) {
            stack.push(tail);
            c++;
            if (c==k) { //time to pop the stack
                head=tail.next;
                for (;c>0;c--) {
                    pf.next=stack.pop();
                    pf=pf.next;
                }
            }
            if (c==0) //just cleared the stack
                tail=head;
            else
                tail=tail.next;
        }
        
        if (!stack.isEmpty()) //there is still elements in the stack -> nodes is not a multiple of k
            pf.next=head;
        else
            pf.next=null;
        
        return first.next;
    }
}
