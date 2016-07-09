package interview.uber;
import java.util.*;

public class MyHashMap<K, V> {
	public MyHashMap(int capacity) {
		storage = new ArrayList<Node>(capacity);
		for (int i = 0; i < capacity; i++) {
			storage.add(null);
		}
	}
	
	public V get(K key) {
		if (key == null) return null;
		Node node = getNodeForKey(key);
		return node == null ? null : node.value;
	}
	
	public V put(K key, V value) {
		Node node = getNodeForKey(key);
		if (node != null) {
			V oldValue = node.value;
			node.value = value; // just update the value.
			return oldValue;
		}
		
		node = new Node(key, value);
		int index = getIndexForKey(key);
		if (storage.get(index) != null) {
			node.next = storage.get(index);
			node.next.prev = node;
		}
		storage.set(index, node);
		return null;
	}
	
	public V remove(K key) {
		Node node = getNodeForKey(key);
		if (node == null) {
			return null;
		}
		
		if (node.prev != null) {
			node.prev.next = node.next;
		} else {
			/* Removing head - update. */
			int hashKey = getIndexForKey(key);
			storage.set(hashKey, node.next);
		}
		
		if (node.next != null) {
			node.next.prev = node.prev;
		}
		return node.value;
	}
	
	class Node {
		K key;
		V value;
		Node prev;
		Node next;
		public Node(K key, V value) {
			this.key = key;
			this.value = value;
			prev = next = null;
		}
	}
	
	private ArrayList<Node> storage = null;
	
	private int getIndexForKey(K key) {
		return Math.abs(key.hashCode() % storage.size());
	}
	
	private Node getNodeForKey(K key) {
		int index = getIndexForKey(key);
		Node current = storage.get(index);
		while (current != null) {
			if (current.key == key) {
				return current;
			}
			current = current.next;
		}
		return null;		
	}
	
	
	
	public static void main(String[] args) {
		MyHashMap<Integer, String> map = new MyHashMap<>(10);
		map.put(0, "Hello");
		map.put(1, " ");
		map.put(2, "World!");
		for (int i = 0; i < 3; i++)
			System.out.print(map.get(i));
		System.out.println();
		
		map.put(1, "_");
		for (int i = 0; i < 3; i++)
			System.out.print(map.get(i));
		
		System.out.println();
		map.remove(2);
		for (int i = 0; i < 3; i++)
			System.out.print(map.get(i));
	}

}
