package leetcode;
import java.util.*;

public class A382_Linked_List_Random_Node {
    private ListNode head;
    private Random rand;
    /** @param head The linked list's head.
        Note that the head is guaranteed to be not null, so it contains at least one node. */
    public A382_Linked_List_Random_Node(ListNode head) {
        this.head = head;
        this.rand = new Random();
    }
    
    /** Returns a random node's value. */
    public int getRandom() {
        ListNode result = null;
        ListNode current = head;
        
        int n = 0;
        while (current != null) {
            n++;
            if (rand.nextInt(n) == 0) { // 1/i, keep
                result = current;
            }
            // (i - 1)/i, discard
            current = current.next;
        }
        
        return result.val;
    }
}
