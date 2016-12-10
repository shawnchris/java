package leetcode;
import java.util.*;

public class A432_All_O_one_Data_Structure {
	class Node {
		int count;
		Set<String> keySet;
		Node prev;
		Node next;
		public Node(int count) {
			this.count = count;
			keySet = new HashSet<String>();
			prev = null;
			next = null;
		}
	}
	
	class AllOne {
		
		Map<String, Integer> keyToCount;
		Map<Integer, Node> countToNode;
		Node head;
		Node tail;
		
	    /** Initialize your data structure here. */
	    public AllOne() {
	    	keyToCount = new HashMap<String, Integer>();
	    	countToNode = new HashMap<Integer, Node>();
	    	head = new Node(Integer.MIN_VALUE);
	    	tail = new Node(Integer.MAX_VALUE);
	    	head.next = tail;
	    	tail.prev = head;
	    }
	    
	    /** Inserts a new key <Key> with value 1. Or increments an existing key by 1. */
	    public void inc(String key) {
	        if (keyToCount.containsKey(key)) { // Key exists
	        	updateKey(key, 1);
	        }
	        else { // Key doesn't exist:
	        	// 1. Create a new [key = 1] entry in keyToCount map
	        	keyToCount.put(key, 1);
	        	// 2. Get the node 1 in list. If not exist, create a new node.
	        	Node node1 = null;
	        	if (head.next.count != 1) {
	        		node1 = new Node(1);
	        		addNodeToList(node1, head);
	        	}
	        	else {
	        		node1 = head.next;
	        	}
	        	// 3. Add key to node 1's keySet
	        	node1.keySet.add(key);
	        	// 4. Put [1 = node1] entry in countToNode map
	        	countToNode.put(1, node1);
	        }
	    }
	    
	    /** Decrements an existing key by 1. If Key's value is 1, remove it from the data structure. */
	    public void dec(String key) {
	        if (keyToCount.containsKey(key)) {
	        	int count = keyToCount.get(key);
	        	if (count == 1) { // Count == 1
	        		// Remove key from keyToCount map
	        		keyToCount.remove(key);
	        		// Remove key from Node
	        		removeKeyFromNode(key, countToNode.get(count));
	        	}
	        	else { // Count > 1
	        		updateKey(key, -1);
	        	}
	        }
	    }
	    
	    private void updateKey(String key, int delta) {
	    	int curCount = keyToCount.get(key);
	    	int newCount = curCount + delta;
	    	Node curNode = countToNode.get(curCount);
	    	Node newNode = countToNode.get(newCount);
	    	if (newNode == null) {
	    		newNode = new Node(newCount);
	    		if (delta > 0) {
	    			addNodeToList(newNode, curNode);
	    		}
	    		else {
	    			addNodeToList(newNode, curNode.prev);
	    		}
	    		countToNode.put(newCount, newNode);
	    	}
	    	
	    	removeKeyFromNode(key, curNode);
	    	newNode.keySet.add(key);
	    	keyToCount.put(key, newCount);
	    }
	    
	    private void removeKeyFromNode(String key, Node node) {
	    	node.keySet.remove(key);
	    	if (node.keySet.isEmpty()) {
	    		removeNodeFromList(node);
	    		countToNode.remove(node.count);
	    	}
	    }
	    
	    private void removeNodeFromList(Node node) {
	    	node.prev.next = node.next;
	    	node.next.prev = node.prev;
	    	node.prev = null;
	    	node.next = null;
	    }
	    
	    private void addNodeToList(Node newNode, Node prevNode) {
	    	newNode.next = prevNode.next;
	    	newNode.prev = prevNode;
	    	prevNode.next = newNode;
	    	newNode.next.prev = newNode;
	    }
	    
	    /** Returns one of the keys with maximal value. */
	    public String getMaxKey() {
	        if (tail.prev == head) return "";
	        return tail.prev.keySet.iterator().next();
	    }
	    
	    /** Returns one of the keys with Minimal value. */
	    public String getMinKey() {
	        if (head.next == tail) return "";
	        return head.next.keySet.iterator().next();
	    }
	}
	
	public static void main(String[] args) {
		A432_All_O_one_Data_Structure ao = new A432_All_O_one_Data_Structure();
		AllOne obj = ao.new AllOne();
		obj.inc("hello");
		obj.inc("goodbye");
		obj.inc("hello");
		obj.inc("hello");
		System.out.println(obj.getMaxKey());
		obj.inc("leet");
		obj.inc("code");
		obj.inc("leet");
		obj.dec("hello");
		obj.inc("leet");
		obj.inc("code");
		System.out.println(obj.getMaxKey());
		obj.inc("leet");
		System.out.println(obj.getMaxKey());
		System.out.println(obj.getMinKey());
		obj.dec("leet");
		obj.dec("leet");
		obj.dec("leet");
		obj.dec("leet");
		System.out.println(obj.getMaxKey());
		System.out.println(obj.getMinKey());
		obj.dec("goodbye");
		System.out.println(obj.getMaxKey());
		System.out.println(obj.getMinKey());
		obj.dec("code");
		System.out.println(obj.getMaxKey());
		System.out.println(obj.getMinKey());
		obj.dec("code");
		System.out.println(obj.getMaxKey());
		System.out.println(obj.getMinKey());
		obj.dec("hello");
		obj.dec("hello");
		System.out.println(obj.getMaxKey());
		System.out.println(obj.getMinKey());
		
	}

}
