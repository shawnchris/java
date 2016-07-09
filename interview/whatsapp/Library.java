package com.whatsapp;
import java.util.*;

class MyMapListener {
	public void sendNotification(String s) {
		System.out.println(s);
	}
	
}

class MyMap<K, V> extends HashMap<K, V> {
	List<MyMapListener> listeners;
	
	public MyMap() {
		listeners = new ArrayList<MyMapListener>();
	}
	
	@Override
	public V put(K key, V value) {
		for (MyMapListener listener : listeners) {
			listener.sendNotification("Putting K:" + key + " V:" + value);
		}
		return super.put(key, value);
	}
	
	public void addListener(MyMapListener listener) {
		listeners.add(listener);
	}
}

class ListNode {
	int val;
	ListNode next;
	ListNode(int x) { val = x; next = null; }
}

class LinkedList {
	ListNode head;
	ListNode tail;
	int length;
	public void append(ListNode node) {
		
	}
	public void preAppend(ListNode node) {
		
	}
	public LinkedList(ListNode head) {
		
	}
}

class TreeNode {
	int val;
	TreeNode left;
	TreeNode right;
	TreeNode(int x) { val = x; }
}

class ConvertSortedListToBST {
    // If traverse a tree InOrder, that will the same sequence with the list.
    ListNode list = null;
	public TreeNode sortedListToBST(ListNode head) {
		if (head == null) return null;
		list = head;
		ListNode counter = head;
		int size = 0;
		while (counter != null) {
			size++;
			counter = counter.next;
		}
		return helper(size);
	}
	public TreeNode helper(int size) {
		if (size == 0) return null;
		
		TreeNode left = helper(size / 2);
		TreeNode root = new TreeNode(list.val);
		list = list.next;
		TreeNode right = helper(size - size / 2 - 1);
		root.left = left;
		root.right = right;
		
		return root;
	}
}

class ConvertSortedArrayToBST {
    public TreeNode sortedArrayToBST(int[] num) {
		if (num.length == 0) return null;
		return helper(num, 0, num.length-1);
	}
	public TreeNode helper(int[] num, int low, int high) {
		// base case
		if (low > high) return null;
		
		// divide and conquer
		int mid = low + (high - low) / 2;
		TreeNode node = new TreeNode(num[mid]);
		node.left = helper(num, low, mid - 1);
		node.right = helper(num, mid + 1, high);
		
		return node;
	}
}

class LinkedListCycle {
    public ListNode detectCycle(ListNode head) {
        if (head == null) return null;
        
        ListNode slow = head;
        ListNode fast = head;
        boolean isCycle = false;
        
        while ((fast.next != null) && (fast.next.next != null)) {
			fast = fast.next.next;
			slow = slow.next;
			if (fast == slow) { //Meet point
				isCycle = true;
				break;
			}
		}
        if (!isCycle) return null;
        
        fast = head;
        while (fast != slow) {
            fast = fast.next;
            slow = slow.next;
        }
        return fast;
        
    }
}

class TrieNode {
    // Initialize your data structure here.
    private static final int R = 26;
    public TrieNode[] next;
    public boolean isEnd;
    public TrieNode() {
        next = new TrieNode[R];
        isEnd = false;
    }
}

class Trie {
    private TrieNode root;

    public Trie() {
        root = new TrieNode();
    }

    // Inserts a word into the trie.
    public void insert(String word) {
        if (word == null) return;
        int len = word.length();
        if (len == 0) return;
        
        TrieNode node = root;
        for (int i = 0; i < len; i++) {
            char c = word.charAt(i);
            if (node.next[c - 'a'] != null) {
                node = node.next[c - 'a'];
            }
            else {
                TrieNode newNode = new TrieNode();
                node.next[c - 'a'] = newNode;
                node = newNode;
            }
            if (i == len - 1)
                node.isEnd = true;
        }
    }

    // Returns if the word is in the trie.
    public boolean search(String word) {
        if (word == null) return true;
        int len = word.length();
        if (len == 0) return true;
        
        TrieNode node = root;
        for (int i = 0; i < len; i++) {
            char c = word.charAt(i);
            if (node.next[c - 'a'] != null) {
                node = node.next[c - 'a'];
                if (i == len - 1 && node.isEnd == false)
                    return false;
            }
            else
                return false;
        }
        
        return true;
    }

    // Returns if there is any word in the trie
    // that starts with the given prefix.
    public boolean startsWith(String prefix) {
        if (prefix == null) return true;
        int len = prefix.length();
        if (len == 0) return true;
        
        TrieNode node = root;
        for (int i = 0; i < len; i++) {
            char c = prefix.charAt(i);
            if (node.next[c - 'a'] != null) {
                node = node.next[c - 'a'];
            }
            else
                return false;
        }
        
        return true;
    }
    
    // Returns if the word is in the data structure. A word could
    // contain the dot character '.' to represent any one letter.
    public boolean searchWithDot(String word) {
        if (word == null) return true;
        if (word.length() == 0) return true;
        return search(word, root);
    }
    
    private boolean search(String word, TrieNode node) {
        char c = word.charAt(0);
        
        if (c == '.') { // need to search all children
            if (word.length() == 1) { // last level
                for (int i = 0; i < 26; i++) {
                    if (node.next[i] != null && node.next[i].isEnd == true)
                        return true;
                }
                return false;
            }
            else { // not last level, search recursively
                for (int i = 0; i < 26; i++) {
                    if (node.next[i] != null) {
                        if (search(word.substring(1), node.next[i]))
                            return true;
                    }
                }
                return false;
            }
        }
        else { // only need to search one children
            if (word.length() == 1) { // last level
                if (node.next[c - 'a'] != null && node.next[c - 'a'].isEnd == true)
                    return true;
            }
            else { // not last level, search recursively
                if (node.next[c - 'a'] != null)
                    return search(word.substring(1), node.next[c - 'a']);
                else
                    return false;
            }
        }
        
        return false;
    }
    
    public void delete(String key) {
    	/*
    		1.Key may not be there in trie. Delete operation should not modify trie.
    		2.Key present as unique key (no part of key contains another key (prefix), nor the key itself is prefix of another key in trie). Delete all the nodes.
    		3.Key is prefix key of another long key in trie. Unmark the leaf node.
    		4.Key present in trie, having atleast one other key as prefix key. Delete nodes from end of key until first leaf node of longest prefix key.
    	*/
    }
}

public class Library {

	public static void main(String[] args) {
		MyMap<Integer, String> mm = new MyMap<>();
		MyMapListener mml = new MyMapListener();
		mm.addListener(mml);
		mm.put(1, "One");

	}

}
