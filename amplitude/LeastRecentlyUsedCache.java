package amplitude;

import java.util.HashMap;

public class LeastRecentlyUsedCache {
  static class Node {
    public int key;
    public int value;
    public Node prev;
    public Node next;
    public Node (int k, int v) {
      key = k;
      value = v;
    }
  }

  int capacity;
  int size = 0;
  HashMap<Integer, Node> map;
  Node head = new Node(Integer.MIN_VALUE, Integer.MIN_VALUE);
  Node tail = new Node(Integer.MAX_VALUE, Integer.MAX_VALUE);

  public LeastRecentlyUsedCache(int capacity) {
    map = new HashMap<>(capacity+1);
    this.capacity = capacity;
    head.next = tail;
    tail.prev = head;
  }

  public int get(int key) {
    Node node = map.get(key);
    if (node == null) {
      return -1;
    }
    else {
      move_to_head(node);
      return node.value;
    }
  }

  public void put(int key, int value) {
    Node node = map.get(key);
    if(node == null) {
      if(size >= capacity)
        remove_from_tail();
      Node newNode = new Node(key, value);
      map.put(key, newNode);
      put_to_head(newNode);
      size++;
    }
    else {
      node.value = value;
      move_to_head(node);
    }
  }

  void remove_from_tail() {
    Node to_be_removed = tail.prev;
    int key = to_be_removed.key;
    tail.prev = to_be_removed.prev;
    to_be_removed.prev.next = tail;
    map.remove(key);
    size--;
  }

  void move_to_head(Node node) {
    if (node.prev != head) {
      node.prev.next = node.next;
      node.next.prev = node.prev;
      put_to_head(node);
    }
  }

  void put_to_head(Node node) {
    node.prev = head;
    node.next = head.next;
    head.next.prev = node;
    head.next = node;
  }
}
