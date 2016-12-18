package leetcode;
import java.util.*;

public class A460_LFU_Cache {
	static class Node {
		Node prev;
		Node next;
		int freq;
		LinkedHashSet<Integer> keys;
		public Node(int frequency) {
			this.freq = frequency;
			prev = next = null;
			keys = new LinkedHashSet<Integer>();
		}
	}
	static class LFUCache {
		int capacity = 0;
		int size = 0;
		Map<Integer, Integer> keyToValue;
		Map<Integer, Node> keyToNode;
		Node head;
		
	    public LFUCache(int capacity) {
	        this.capacity = capacity;
	        this.size = 0;
	        keyToValue = new HashMap<Integer, Integer>();
	        keyToNode = new HashMap<Integer, Node>();
	        head = new Node(0);
	    }
	    
	    public int get(int key) {
	    	// Check if the key exists
	    	if (keyToValue.containsKey(key)) {
	    	//   yes - 1. increase level of key 2. return value
	    		promoteKey(key);
	    		return (keyToValue.get(key));
	    	}
	    	else {
	    	//   no - return -1
	    		return -1;
	    	}
	    }
	    
	    public void set(int key, int value) {
	    	// Corner case
	    	if (capacity <= 0) return;
	    	
	        // Check if the key is already there
	    	if (keyToValue.containsKey(key)) {
	    	//   yes - update value
	    		keyToValue.put(key, value);
	    	}
	    	else {
	    	//   no - 1. if size == capacity then remove LFU entry 2. create new entry in level 0
	    		if (size == capacity) {
	    			removeLFU();
	    			size--;
	    		}
	    		keyToValue.put(key, value);
	    		head.keys.add(key);
	    		keyToNode.put(key, head);
	    		size++;
	    	}
	    	// Increase level of key
	    	promoteKey(key);
	    }
	    
	    private void promoteKey(int key) {
	    	Node node = keyToNode.get(key);
	    	// Get next level node
	    	Node nextNode = node.next;
	    	if (nextNode == null || nextNode.freq != node.freq + 1) {
	    		nextNode = new Node(node.freq + 1);
	    		if (node.next != null) {
	    			node.next.prev = nextNode;
	    			nextNode.next = node.next;
	    		}
	    		node.next = nextNode;
    			nextNode.prev = node;
	    	}
	    	// Move key to next level node
	    	node.keys.remove(key);
	    	nextNode.keys.add(key);
	    	keyToNode.put(key, nextNode);
	    	// Remove node if it is empty
	    	if (node.keys.isEmpty() && node != head) {
	    		removeNode(node);
	    	}
	    }
	    
	    private void removeLFU() {
	    	Node node = head.next;
	    	if (node == null || node.keys.isEmpty()) return;
	    	
	    	int key = node.keys.iterator().next();
	    	keyToValue.remove(key);
	    	keyToNode.remove(key);
	    	
	    	node.keys.remove(key);
	    	// Remove node if it is empty
	    	if (node.keys.isEmpty() && node != head) {
	    		removeNode(node);
	    	}
	    }
	    
	    private void removeNode(Node node) {
	    	Node prev = node.prev;
    		prev.next = node.next;
    		if (node.next != null) {
    			node.next.prev = prev;
    		}
	    }
	}
	public static void main(String[] args) {
		LFUCache cache = new LFUCache( 2 /* capacity */ );
		cache.set(1, 1);
		cache.set(2, 2);
		System.out.println(cache.get(1));       // returns 1
		cache.set(3, 3);    // evicts key 2
		System.out.println(cache.get(2));       // returns -1 (not found)
		System.out.println(cache.get(3));       // returns 3.
		cache.set(4, 4);    // evicts key 1.
		System.out.println(cache.get(1));       // returns -1 (not found)
		System.out.println(cache.get(3));       // returns 3
		System.out.println(cache.get(4));       // returns 4
	}

}
