package leetcode;

public class A061_Rotate_List {
    public ListNode rotateRight(ListNode head, int k) {
        int len = 0;
        ListNode p1 = head, p2 = head;
        while (p1 != null) {
            p1 = p1.next;
            len++;
        }
        if (len <= 1) return head;
        k = k % len;
        if (k == 0) return head;
        
        p1 = head;
        for (int i = 0; i < k ; i++)
            p1 = p1.next;
        while (p1.next != null) {
            p1 = p1.next;
            p2 = p2.next;
        }
        
        ListNode newhead = p2.next;
        p2.next = null;
        p1.next = head;
        
        return newhead;
    }
}
