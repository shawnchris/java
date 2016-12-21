package leetcode;
import java.util.*;

public class A425_Word_Squares {
	class TrieNode {
        List<String> startWith;
        TrieNode[] children;

        TrieNode() {
            startWith = new ArrayList<>();
            children = new TrieNode[26];
        }
    }

    class Trie {
        TrieNode root;

        Trie(String[] words) {
            root = new TrieNode();
            for (String w : words) {
                TrieNode cur = root;
                for (char ch : w.toCharArray()) {
                    int idx = ch - 'a';
                    if (cur.children[idx] == null)
                        cur.children[idx] = new TrieNode();
                    cur.children[idx].startWith.add(w);
                    cur = cur.children[idx];
                }
            }
        }

        List<String> findByPrefix(String prefix) {
            List<String> ans = new ArrayList<>();
            TrieNode cur = root;
            for (char ch : prefix.toCharArray()) {
                int idx = ch - 'a';
                if (cur.children[idx] == null)
                    return ans;

                cur = cur.children[idx];
            }
            ans.addAll(cur.startWith);
            return ans;
        }
    }

    public List<List<String>> wordSquares(String[] words) {
        List<List<String>> ans = new ArrayList<>();
        if (words == null || words.length == 0)
            return ans;
        int len = words[0].length();
        Trie trie = new Trie(words);
        List<String> ansBuilder = new ArrayList<>();
        for (String w : words) {
            ansBuilder.add(w);
            search(len, trie, ans, ansBuilder);
            ansBuilder.remove(ansBuilder.size() - 1);
        }

        return ans;
    }

    private void search(int len, Trie tr, List<List<String>> ans,
            List<String> ansBuilder) {
        if (ansBuilder.size() == len) {
            ans.add(new ArrayList<>(ansBuilder));
            return;
        }

        int idx = ansBuilder.size();
        StringBuilder prefixBuilder = new StringBuilder();
        for (String s : ansBuilder)
            prefixBuilder.append(s.charAt(idx));
        List<String> startWith = tr.findByPrefix(prefixBuilder.toString());
        for (String sw : startWith) {
            ansBuilder.add(sw);
            search(len, tr, ans, ansBuilder);
            ansBuilder.remove(ansBuilder.size() - 1);
        }
    }
    
    public List<List<String>> wordSquares2(String[] words) {
    	List<List<String>> result = new ArrayList<List<String>>();
    	if (words == null || words.length == 0) return result;
    	
    	int len = words[0].length();
    	Map<String, List<String>> map = new HashMap<>();
    	for (String word : words) {
    		for (int i = 1; i <= len; i++) {
    			String key = word.substring(0, i);
    			List<String> list = map.get(key);
    			if (list == null) list = new ArrayList<String>();
    			list.add(word);
    			map.put(key, list);
    		}
    	}
    	
    	for (String word : words) {
    		List<String> current = new ArrayList<>();
    		current.add(word);
    		dfs(len, 1, current, map, result);
    	}
    	
    	return result;
    }
    
    private void dfs(int len, int level, List<String> current, Map<String, List<String>> map, List<List<String>> result) {
    	if (len == level) {
    		result.add(new ArrayList<String>(current));
    		return;
    	}
    	
    	String prefix = "";
    	for (String s : current) {
    		prefix = prefix + s.charAt(level);
    	}
    	List<String> candidates = map.get(prefix);
    	if (candidates == null) return;
    	for (String s : candidates) {
    		current.add(s);
    		dfs(len, level + 1, current, map, result);
    		current.remove(current.size() - 1);
    	}
    }
    
    public static void main(String[] args) {
    	A425_Word_Squares ws = new A425_Word_Squares();
    	String[] words1 = {"area","lead","wall","lady","ball"};
    	String[] words2 = {"abat","baba","atan","atal"};
    	System.out.println(ws.wordSquares(words1));
    	System.out.println(ws.wordSquares(words2));
    	System.out.println(ws.wordSquares2(words1));
    	System.out.println(ws.wordSquares2(words2));
    }
}
