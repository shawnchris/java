package leetcode;

public class A208_Implement_Trie {
	private TrieNode root;

    public A208_Implement_Trie() {
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
                node.isWord = true;
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
                if (i == len - 1 && node.isWord == false)
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
}
