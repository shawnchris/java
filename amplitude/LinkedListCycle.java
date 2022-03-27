package amplitude;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class LinkedListCycle {

  // Given head, the head of a linked list, determine if the linked list has a cycle in it.
  // HashSet solution.
  public static boolean hasCycle1(ListNode head) {
    Set<ListNode> visited = new HashSet<>();

    while (head != null) {
      if (visited.contains(head)) {
        return true;
      }
      visited.add(head);
      head = head.next;
    }

    return false;
  }

  // Given head, the head of a linked list, determine if the linked list has a cycle in it.
  // Fast, slow pointer solution.
  public static boolean hasCycle2(ListNode head) {
    if (head == null) return false;
    ListNode slow = head;
    ListNode fast = head;

    while ((fast.next != null) && (fast.next.next != null)) {
      fast = fast.next.next;
      slow = slow.next;
      if (fast == slow) { //Meet point
        return true;
      }
    }
    return false;
  }

  public static void main(String[] args) {
    ArrayList<ListNode> list = new ArrayList<>();
    for (int i = 0; i < 10; i++) {
      list.add(new ListNode(i));
    }
    for (int i = 0; i < 9; i++) {
      list.get(i).next = list.get(i + 1);
    }

    System.out.println(hasCycle1(list.get(0)));
    System.out.println(hasCycle2(list.get(0)));

    list.get(9).next = list.get(6);
    System.out.println(hasCycle1(list.get(0)));
    System.out.println(hasCycle2(list.get(0)));
  }

}
