package leetcode;
import java.util.*;
/*
There is a new alien language which uses the latin alphabet. However, the order
among letters are unknown to you. You receive a list of words from the
dictionary, where words are sorted lexicographically by the rules of this new
language. Derive the order of letters in this language.
For example,
Given the following words in dictionary,
[
  "wrt",
  "wrf",
  "er",
  "ett",
  "rftt"
]
The correct order is: "wertf".
Note:
You may assume all letters are in lower case.
If the order is invalid, return an empty string.
There may be multiple valid order of letters, return any one of them is fine.
 */
public class L269_Alien_Dictionary {
	// Topological sort - Kahn's Algorithm
	public String alienOrder(String[] words) {
        if(words == null || words.length == 0) {
            return "";
        }
        Map<Character, Set<Character>> graph = new HashMap<>();
        Set<Character> set = new HashSet<>();
        for (String word : words) {
            for (int i = 0; i < word.length(); i++) {
                set.add(word.charAt(i));
            }
        }
        
        int[] inDegree = new int[26];
        for (int k = 1; k < words.length; k++) {
            String preStr = words[k - 1];
            String curStr = words[k];
            for (int i = 0; i < Math.min(preStr.length(), curStr.length()); i++) {
                char preChar = preStr.charAt(i);
                char curChar = curStr.charAt(i);
                if (preChar != curChar) {
                    if (!graph.containsKey(preChar)) {
                        graph.put(preChar, new HashSet<Character>());
                    }
                    if (!graph.get(preChar).contains(curChar)) {
                        inDegree[curChar - 'a']++;
                    }                    
                    graph.get(preChar).add(curChar);
                    break;
                }
            }
        }
        Queue<Character> queue = new LinkedList<>();
        for (int i = 0; i < inDegree.length; i++) {
            if (inDegree[i] == 0) {
                char c = (char)('a' + i);
                if (set.contains(c)) {
                    queue.offer(c);    
                }
            }
        }
        StringBuilder sb = new StringBuilder();
        while (!queue.isEmpty()) {
            char c = queue.poll();
            sb.append(c);
            if (graph.containsKey(c)) {
                for (char l : graph.get(c)) {
                    inDegree[l - 'a']--;
                    if (inDegree[l - 'a'] == 0) {
                        queue.offer(l);
                    }
                }
            }
        }
        return sb.length() != set.size() ? "" : sb.toString();
    }
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
