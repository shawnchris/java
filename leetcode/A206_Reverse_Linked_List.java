package leetcode;

public class A206_Reverse_Linked_List {
    public ListNode reverseList(ListNode head) {
        ListNode prev = null;
        ListNode next = null;
        
        while (head != null) {
            next = head.next;
            head.next = prev;
            prev = head;
            head = next;
        }
        
        return prev;
    }
}
