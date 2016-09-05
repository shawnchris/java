package leetcode;

public class A147_Insertion_Sort_List {
    public ListNode insertionSortList(ListNode head) {
        ListNode dummyHead = new ListNode(0);
        while(head != null) {
            ListNode node = head, prev = dummyHead;
            head = head.next;
            while (prev != null) {
                if (prev.next == null || prev.next.val >= node.val) {
                    //insert node after prev
                    node.next = prev.next;
                    prev.next = node;
                    break;
                }
                prev = prev.next;
            }
        }
        
        return dummyHead.next;
    }
}
