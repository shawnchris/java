package leetcode;

public class A019_Remove_Nth_Node_From_End_of_List {
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode newhead = new ListNode(0);
        newhead.next = head;
        ListNode p1 = newhead, p2 = newhead;
        // let p1 run n steps first
        for (int i = 0; i < n; i++)
            p1 = p1.next;

        // p1 p2 go together, when p1 points to the last node, 
        // p2 points to the parent node of the node to be removed
        while (p1.next != null) {
            p1 = p1.next;
            p2 = p2.next;
        }
        p2.next = p2.next.next;
        
        return newhead.next;
    }
}
