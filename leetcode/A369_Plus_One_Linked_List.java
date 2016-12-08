package leetcode;

public class A369_Plus_One_Linked_List {
    public ListNode plusOne(ListNode head) {
        ListNode newHead = new ListNode(0);
        newHead.next = head;
        helper(newHead);
        if (newHead.val != 0) {
            return newHead;
        }
        return head;
    }
    
    private boolean helper(ListNode node) {
        if (node.next == null) {
            if (node.val == 9) {
                node.val = 0;
                return true;
            }
            node.val += 1;
            return false;
        }
        
        if (helper(node.next)) {
            if (node.val == 9) {
                node.val = 0;
                return true;
            }
            node.val += 1;
        }
        
        return false;
    }
}
