package interview.uber;

public class Reverse_Copy_Linked_List {
	public static ListNode reverseCopy(ListNode head) {
		ListNode newHead = null, newTail = null;
		while (head != null) {
			newHead = new ListNode(head.val);
			newHead.next = newTail;
			newTail = newHead;
			head = head.next;
		}
		return newHead;
	}
	public static void main(String[] args) {
		ListNode head = new ListNode(0);
		ListNode p = head;
		for (int i = 1; i < 5; i++) {
			p.next = new ListNode(i);
			p = p.next;
		}
		head = reverseCopy(head);
		while (head != null) {
			System.out.print(head.val + " ");
			head = head.next;
		}
	}

}
