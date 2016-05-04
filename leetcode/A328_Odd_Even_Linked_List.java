package leetcode;

public class A328_Odd_Even_Linked_List {
    public ListNode oddEvenList(ListNode head) {
        if (head == null || head.next == null) return head;
        ListNode oddHead = head, oddTail = head;
        head = head.next;
        ListNode evenHead = head, evenTail = head;
        boolean isOdd = true;
        while (head.next != null) {
            head = head.next;
            if (isOdd) {
                oddTail.next = head;
                oddTail = oddTail.next;
            }
            else {
                evenTail.next = head;
                evenTail = evenTail.next;
            }
            isOdd = !isOdd;
        }
        
        oddTail.next = evenHead;
        evenTail.next = null;
        
        return oddHead;
    }
}
