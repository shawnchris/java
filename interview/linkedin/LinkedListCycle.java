package interview.linkedin;
import java.util.*;

public class LinkedListCycle {
	// 0. No intersect no cycle
	// 1. Intersect no cycle
	// 2. Has cycle no intersect
	// 3. Has cycle and intersect
	public int detect(ListNode head1, ListNode head2) {
		if (head1 == null || head2 == null) return 0;
		
		boolean hasCycle = false;
		ListNode p1 = head1;
		ListNode p2 = head2;
		
		Set<ListNode> set1 = new HashSet<>();
		Set<ListNode> set2 = new HashSet<>();
		
		while (p1 != null) {
			if (set1.contains(p1)) { hasCycle = true; break; }
			set1.add(p1);
			p1 = p1.next;
		}
		
		while (p2 != null) {
			if (set2.contains(p2)) { hasCycle = true; break; }
			set2.add(p2);
			p2 = p2.next;
		}
		
		Set<ListNode> intersection = new HashSet<>(set1);
		intersection.retainAll(set2);
		
		if (intersection.isEmpty()) {
			if (!hasCycle) return 0;
			else return 1;
		}
		else {
			if (!hasCycle) return 2;
			else return 3;
		}
	}
	
	public int detect2(ListNode head1, ListNode head2) {
		// Detect if there's cycle in two list and get cycle start if yes.
		ListNode cycleStart1 = detectCycle(head1);
		ListNode cycleStart2 = detectCycle(head2);
		
		if (cycleStart1 == null && cycleStart2 == null) {
			// If no cycle then we can detect intersection
			if (detectIntersection(head1, head2) == null) {
				return 0;
			}
			else {
				return 1;
			}
		}
		else {
			// If has cycle and intersect, start of cycle must be the same node.
			if (cycleStart1 == null || cycleStart2 == null || cycleStart1 != cycleStart2) {
				return 3;
			}
			else {
				return 4;
			}
		}
	}
	
	private ListNode detectCycle(ListNode head) {
        if (head == null) return null;
        ListNode fast = head;
        ListNode slow = head;
        
        boolean hasCycle = false;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
            
            if (fast == slow) { hasCycle = true; break; }
        }
        
        if (!hasCycle) return null;
        
        fast = head;
        while (fast != slow) {
            fast = fast.next;
            slow = slow.next;
        }
        
        return fast;
    }
	
	private ListNode detectIntersection(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) return null;
        ListNode p1 = headA, p2 = headB;
        int round = 0;
        
        while (round < 3) {
            if (p1 == p2) return p1;
            
            p1 = p1.next;
            p2 = p2.next;
            
            if (p1 == null) { p1 = headB; round++;}
            if (p2 == null) { p2 = headA; round++;}
        }
        
        return null;
    }
	
	public static void main(String[] args) {
		

	}

}
