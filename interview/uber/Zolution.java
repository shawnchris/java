package interview.uber;
import java.util.*;

public class Zolution {

	public static void main1(String[] args) {
		System.out.println("Hello World!");
	}
	
	public static void main2(String[] args) {
	    Scanner in = new Scanner(System.in);
	    while (in.hasNextLine()) {
		    System.out.println("Hello " + in.nextLine() + "!");
	    }
	    in.close();

	}

}

/*

- 'sa' -> ['san francisco', 'san francisco airport', 'safeway']
- 'los' -> ['los angeles', 'los gatos', 'lost coast']
- 'se' -> ['seattle', 'sequoia national park']

[
    'san francisco', 
    'san francisco airport', 
    'safeway',
    'los angeles', 
    'los gatos', 
    'lost coast',
    'seattle', 
    'sequoia national park'
]
n words m O(n * m)
Trie - Prefix Tree "sa"
       [] s, l
  [s] a,e         [l]
[a] n,f    [e]                       
[].isWord == true
*/



 class Solution {
	 class TrieNode {
	    //char letter;
	    boolean isWord;
	    //TrieNode[] next;
	    HashMap<Character, TrieNode> children;
	    public TrieNode() {
	        isWord = false;
	        children = new HashMap<Character, TrieNode>();
	    }
	}
	public static void main(String[] args) {
	    System.out.println("Hello World!");
	}
    
    private TrieNode root = null;
    
    public Solution() {
        root = new TrieNode();
    }
    
    public void insert(String word) {
        //Sanity Check
        if (word == null || word.length() == 0)
            return;
        
        TrieNode node = root;
        
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            if (node.children.containsKey(c)) {
                node = node.children.get(c);
            }
            else {
                TrieNode newNode = new TrieNode();
                node.children.put(c, newNode);
                node = newNode;
            }
        }
        node.isWord = true;
    }
    
    public List<String> search(String term) {
        List<String> result = new ArrayList<>();
        if (term == null || term.length() == 0)
            return result;
        
        String current = "";
        TrieNode node = root;
        for (int i = 0; i < term.length(); i++) {
            char c = term.charAt(i);
            if (!node.children.containsKey(c)) {
                return result;
            }
            else {
                node = node.children.get(c);
                current += c;
            }
        }
        
        helper(node, current, result);
        
        return result;
    }
    
    private void helper(TrieNode node, String current, List<String> result) {
        // Base case
        if (node.isWord == true) {
            result.add(current); // "san francisco" "san francisco airport"
        }
        
        for (Character key: node.children.keySet()) {
            helper(node.children.get(key), current + key, result);
        }
        
    }
}

















