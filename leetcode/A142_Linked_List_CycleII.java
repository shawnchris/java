package leetcode;

public class A142_Linked_List_CycleII {
    public ListNode detectCycle(ListNode head) {
        if (head == null) return null;
        
        ListNode slow = head;
        ListNode fast = head;
        boolean isCycle = false;
        
        while ((fast.next != null) && (fast.next.next != null)) {
			fast = fast.next.next;
			slow = slow.next;
			if (fast == slow) {
				isCycle = true;
				break;
			}
		}
        if (!isCycle) return null;
        
        fast = head;
        while (fast != slow) {
            fast = fast.next;
            slow = slow.next;
        }
        return fast;
        
    }
}
