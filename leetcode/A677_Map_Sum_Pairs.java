package leetcode;

import java.util.HashMap;
import java.util.Map;

public class A677_Map_Sum_Pairs {
    class MapSum {
        class TrieNode {
            Map<Character, TrieNode> children;
            boolean isWord;
            int value;

            public TrieNode() {
                children = new HashMap<Character, TrieNode>();
                isWord = false;
                value = 0;
            }
        }

        TrieNode root;

        /** Initialize your data structure here. */
        public MapSum() {
            root = new TrieNode();
        }

        public void insert(String key, int val) {
            TrieNode curr = root;
            for (char c : key.toCharArray()) {
                TrieNode next = curr.children.get(c);
                if (next == null) {
                    next = new TrieNode();
                    curr.children.put(c, next);
                }
                curr = next;
            }
            curr.isWord = true;
            curr.value = val;
        }

        public int sum(String prefix) {
            TrieNode curr = root;
            for (char c : prefix.toCharArray()) {
                TrieNode next = curr.children.get(c);
                if (next == null) {
                    return 0;
                }
                curr = next;
            }

            return dfs(curr);
        }

        private int dfs(TrieNode root) {
            int sum = 0;
            for (char c : root.children.keySet()) {
                sum += dfs(root.children.get(c));
            }
            return sum + root.value;
        }
    }

/**
 * Your MapSum object will be instantiated and called as such:
 * MapSum obj = new MapSum();
 * obj.insert(key,val);
 * int param_2 = obj.sum(prefix);
 */
}
