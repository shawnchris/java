package datastructure;
import java.util.*;

class TrieNode {
	Map<Character, TrieNode> children;
	List<String> startWith;
	boolean isWord;
	
	public TrieNode() {
		children = new HashMap<Character, TrieNode>();
		startWith = new ArrayList<String>();
		isWord = false;
	}
}

public class Trie {
	TrieNode root;
	
	public Trie(String[] words) {
		root = new TrieNode();
		
		for (String word : words) {
			TrieNode curr = root;
			for (char c : word.toCharArray()) {
				TrieNode next = curr.children.get(c);
				if (next == null) {
					next = new TrieNode();
					curr.children.put(c, next);
				}
				next.startWith.add(word);
				curr = next;
			}
			curr.isWord = true;
		}
	}
	
	public List<String> findByPrefix(String prefix) {
		TrieNode curr = root;
		for (char c : prefix.toCharArray()) {
			TrieNode next = curr.children.get(c);
			if (next == null) {
				return new ArrayList<String>();
			}
			curr = next;
		}
		return curr.startWith;
	}
	
	public boolean contains(String pattern) {
		if (pattern == null || pattern.length() == 0) return true;
		return search(root, pattern.toCharArray(), 0);
	}
	
	private boolean search(TrieNode curr, char[] pattern, int index) {
		if (index == pattern.length) {
			if (curr.isWord) return true;
			return false;
		}
		
		char c = pattern[index];
		
		if (c == '.') {
			for (TrieNode next : curr.children.values()) {
				if (search(next, pattern, index + 1))
					return true;
			}
			return false;
		}
		
		TrieNode next = curr.children.get(c);
		if (next == null) return false;
		return search(next, pattern, index + 1);
	}
	
	public static void main(String[] args) {
		String[] words = {"area","lead","wall","lady","ball"};
		Trie t = new Trie(words);
		System.out.println(t.findByPrefix("a"));
		System.out.println(t.findByPrefix("la"));
		System.out.println(t.findByPrefix("bal"));
		System.out.println(t.contains("area"));
		System.out.println(t.contains("ar.a"));
		System.out.println(t.contains("...a"));
		System.out.println(t.contains("a..."));
		System.out.println(t.contains("..a"));
		System.out.println(t.contains("area."));
	}

}
