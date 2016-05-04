package leetcode;

public class A160_Intersection_of_Two_Linked_Lists {
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if (headA == null || headB == null)
            return null;
        
        ListNode pA = headA, pB = headB;
        int c = 0;
        
        while (c < 3) {
            if (pA == pB)
                return pA;
            
            pA = pA.next;
            pB = pB.next;
            
            if (pA == null) {
                pA = headB;
                c++;
            }
            if (pB == null) {
                pB = headA;
                c++;
            }
        }
        
        return null;
    }
}
