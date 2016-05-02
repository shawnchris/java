package leetcode;

public class A024_Swap_Nodes_in_Pairs {
    public ListNode swapPairs(ListNode head) {
        if (head == null) return null;
        ListNode dummyHead = new ListNode(0);
        ListNode pre = dummyHead, p1 = head, p2 = head.next;
        dummyHead.next = head;
        
        while (p2 != null) {
            pre.next = p2;
            p1.next = p2.next;
            p2.next = p1;
            if (p1.next != null && p1.next.next != null) {
                pre = p1;
                p1 = p1.next;
                p2 = p1.next;
            }
            else
                break;
        }

        return dummyHead.next;
    }
}
