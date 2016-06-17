package leetcode;
import java.util.*;

public class A023_Merge_k_Sorted_Lists {
    public ListNode mergeKLists(ListNode[] lists) {
        if (lists == null || lists.length == 0) return null;
		
		class Cmprtr implements Comparator<ListNode> {
		    public int compare(ListNode n1, ListNode n2) {
		        return n1.val - n2.val;
		    }
		}
		Queue<ListNode> pq = new PriorityQueue<>(lists.length, new Cmprtr());
		
		ListNode head = new ListNode(0);
        ListNode tail = head;

        for (ListNode node: lists)
            if (node != null)
                pq.add(node);

        while (!pq.isEmpty()){
            tail.next = pq.poll();
            tail = tail.next;

            if (tail.next!=null)
                pq.add(tail.next);
        }
        
        return head.next;
    }
}
