package leetcode;

public class A086_Partition_List {
    public ListNode partition(ListNode head, int x) {
        if (head == null || head.next == null)
        	return head;
        
        ListNode left = new ListNode(0);
        ListNode right = new ListNode(0);
        ListNode pl = left, pr = right;
        
        while(head != null) {
            if (head.val < x) {
                pl.next = head;
                pl = head;
            }
            else {
                pr.next = head;
                pr = head;
            }
            head = head.next;
        }
        
        pl.next = right.next;
        pr.next = null;
        
        return left.next;
    }
}
