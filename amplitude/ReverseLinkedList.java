package amplitude;

import java.util.ArrayList;

public class ReverseLinkedList {

  // Given the head of a singly linked list, reverse the list, and return the reversed list.
  public static ListNode reverseList(ListNode head) {
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

  public static void main(String[] args) {
    ArrayList<ListNode> list = new ArrayList<>();
    for (int i = 0; i < 10; i++) {
      list.add(new ListNode(i));
    }
    for (int i = 0; i < 9; i++) {
      list.get(i).next = list.get(i + 1);
    }

    ListNode newHead = reverseList(list.get(0));
    while (newHead != null) {
      System.out.print(newHead.val + "->");
      newHead = newHead.next;
    }
  }

}
