package leetcode;

public class A002_Add_Two_Numbers {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        if (l1 == null) return l2;
        if (l2 == null) return l1;
        ListNode newhead = new ListNode(0);
        ListNode p = newhead;

        int carry = 0;
        while (l1 != null || l2 != null || carry != 0) {
            if(l1!=null) {
                carry += l1.val;
                l1 = l1.next;
            }
            if(l2!=null) {
                carry += l2.val;
                l2 = l2.next;
            }

            p.next = new ListNode(carry % 10);
            p = p.next;
            carry = carry / 10;
        }
        return newhead.next;
    }
}
