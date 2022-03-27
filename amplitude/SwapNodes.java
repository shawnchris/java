package amplitude;

import java.util.ArrayList;

public class SwapNodes {
  // Return the head of the linked list after swapping the values of the head and the kth node from
  // the end (the list is 1-indexed).
  public static ListNode swapNodes(ListNode head, int k) {
    int n = 0;
    ListNode p = head, p2 = null;
    // Get the length of the linked list.
    while (p != null) {
      p = p.next;
      n++;
    }
    int k2 = n - k + 1;

    int c = 0;
    p = head;
    while (p != null) {
      c++;
      if (c == k2) {
        p2 = p;
      }
      p = p.next;
    }

    int temp = head.val;
    head.val = p2.val;
    p2.val = temp;

    return head;
  }

  public static void main(String[] args) {
    ArrayList<ListNode> list = new ArrayList<>();
    for (int i = 0; i < 10; i++) {
      list.add(new ListNode(i));
    }
    for (int i = 0; i < 9; i++) {
      list.get(i).next = list.get(i + 1);
    }

    ListNode newHead = swapNodes(list.get(0), 2);
    while (newHead != null) {
      System.out.print(newHead.val + "->");
      newHead = newHead.next;
    }
  }
}
