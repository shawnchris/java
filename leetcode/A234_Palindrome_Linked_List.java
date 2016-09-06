package leetcode;

public class A234_Palindrome_Linked_List {
    ListNode reverse(ListNode head) {
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
    
    public boolean isPalindrome(ListNode head) {
        if (head==null) return true;
    
        //Find middle node: use slow, fast pointers.
        ListNode fast = head;
        ListNode mid = head;
        while(fast.next !=null && fast.next.next != null) {
            fast = fast.next.next;
            mid = mid.next;
        }
        
        //Reverse: mid..end
        ListNode tail = reverse(mid.next);
        mid.next = null;
    
        //Compare tail to mid and start to mid.
        while (tail != null && head != null) {
            if (tail.val != head.val) {
                return false;
            }
            tail = tail.next;
            head = head.next;
        }
        return true;
    }
}
