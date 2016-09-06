package leetcode;

public class A203_Remove_Linked_List_Elements {
    public ListNode removeElements(ListNode head, int val) {
        ListNode newhead = new ListNode(0);
        ListNode previous = newhead;
        previous.next =  head;
        while (previous.next != null) {
            if (previous.next.val == val)
                previous.next = previous.next.next;
            else
                previous = previous.next;
        }
        
        return newhead.next;
    }
}
