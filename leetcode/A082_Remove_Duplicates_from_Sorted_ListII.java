package leetcode;

public class A082_Remove_Duplicates_from_Sorted_ListII {
    public ListNode deleteDuplicates(ListNode head) {
        if (head == null) return head;
        ListNode newHead = new ListNode(0);
        newHead.next = head;
        ListNode p1 = newHead, p2 = head;
        
        while (p2 != null) {
            int dup = 0;
            while (p2.next != null && p2.val == p2.next.val) {
                dup++;
                p2 = p2.next;
            }
            if (dup > 0) {
                p1.next = p2.next;
                //p2 = p2.next;
            }
            else {
                p1 = p2;
            }
            if (p2 != null)
                p2 = p2.next;
        }
        
        return newHead.next;
    }
}
