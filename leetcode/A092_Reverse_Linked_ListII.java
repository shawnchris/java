package leetcode;

public class A092_Reverse_Linked_ListII {
    public ListNode reverseBetween(ListNode head, int m, int n) {
        if (head.next == null ||  m == n) 
			return head;

		ListNode dummyHead = new ListNode(0);
		dummyHead.next = head;
		ListNode p1 = dummyHead, p2, p3, prev, post, pm, pn;
		int count = 0;
		p2 = p3 = prev = post = pm = pn = null;
		
		// find nodes m - 1, m, n, n + 1
		while (p1 != null) {
		    count++;
		    if (count == m) {
		        prev = p1;
		        pm = prev.next;
		    }
		    if (count == n) {
		        pn = p1.next;
		        post = pn.next;
		    }
		    p1 = p1.next;
		}
		
		// reverse nodes m ~ n
		p1 = prev; p2 = pm; p3 = p2.next;
		for (int i = 0; i <= (n - m); i++) {
		    p2.next = p1;
		    p1 = p2;
		    p2 = p3;
		    if (p3.next != null)
		        p3 = p3.next;
		}
		
		// reconnect them
		prev.next = pn;
		pm.next = post;
		
		return dummyHead.next;
    }
}
