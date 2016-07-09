package interview.uber;
import java.util.*;

public class A212_Word_SearchII {

    TrieNode root = new TrieNode();
    boolean[][] used;
    
    private void addToTrie(String[] words){
        for(String word: words){
            TrieNode node = root;
            for(int i = 0; i < word.length(); i++){
                char ch = word.charAt(i);
                if(node.next[ch - 'a'] == null){
                    node.next[ch - 'a'] = new TrieNode();
                }
                node = node.next[ch - 'a'];
            }
            node.isWord = true;
        }
    }

    private void search(char[][] board, int i, int j, TrieNode node, String word, Set<String> result){
        if (i >= board.length || i < 0 || j >= board[i].length || j < 0 || used[i][j]) {
            return;
        }

        if (node.next[board[i][j] - 'a'] == null) {
            return;
        }

        used[i][j] = true;
        word += board[i][j];
        node = node.next[board[i][j] - 'a'];
        if(node.isWord){
            result.add(word);
        }

        search(board, i-1, j, node, word, result);
        search(board, i+1, j, node, word, result);
        search(board, i, j-1, node, word, result);
        search(board, i, j+1, node, word, result);

        used[i][j] = false;
    }
    
    public List<String> findWords(char[][] board, String[] words) {
        Set<String> result = new HashSet<>();
        used = new boolean[board.length][board[0].length];

        addToTrie(words);

        for(int i = 0; i < board.length; i++) {
            for(int j = 0; j < board[0].length; j++) {
                if(root.next[board[i][j] - 'a'] != null){
                    search(board, i, j, root, "", result);
                }
            }
        }

        return new ArrayList<String>(result);
    }
}
