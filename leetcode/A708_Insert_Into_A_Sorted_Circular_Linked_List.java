package leetcode;

/**
 * Given a Circular Linked List node, which is sorted in non-descending order, write a function to insert a value
 * insertVal into the list such that it remains a sorted circular list. The given node can be a reference to any single
 * node in the list and may not necessarily be the smallest value in the circular list.
 *
 * If there are multiple suitable places for insertion, you may choose any place to insert the new value. After the
 * insertion, the circular list should remain sorted.
 *
 * If the list is empty (i.e., the given node is null), you should create a new single circular list and return the
 * reference to that single node. Otherwise, you should return the originally given node.
 */
public class A708_Insert_Into_A_Sorted_Circular_Linked_List {
    public Node insert(Node head, int insertVal) {
        Node newNode = new Node(insertVal);
        newNode.next = newNode;
        if (head == null) return newNode;

        Node curr = head, next, tail = head;
        boolean inserted = false;
        do {
            next = curr.next;
            if (next.val < curr.val) tail = curr;
            if (curr.val == insertVal || next.val == insertVal
                    || curr.val < insertVal && next.val > insertVal) {
                curr.next = newNode;
                newNode.next = next;
                inserted = true;
                break;
            }
            curr = next;
        } while (curr != head);

        if (!inserted) {
            newNode.next = tail.next;
            tail.next = newNode;
        }

        return head;
    }

    static class Node {
        public int val;
        public Node next;
        public Node() {}
        public Node(int _val) {
            val = _val;
        }
        public Node(int _val, Node _next) {
            val = _val;
            next = _next;
        }
    }
}
