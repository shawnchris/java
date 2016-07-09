package interview.uber;
import java.util.*;
/*
 * The same as Word Search I but need to print the path at last.
 */
public class WordSearch {
	List<String> path = new ArrayList<>();
	
	public void search(char[][] board, String word) {
        if (board == null || word == null) return;
        int m = board.length;
        if (m == 0) return;
        int n = board[0].length;
        if (n == 0) return;
        
        boolean[][] used = new boolean[m][n];
        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++){
                if(helper(board, i, j, word, 0, used)) {
                    return;
                }
            }
        }
        
        System.out.println("Not found.");
    }
	
    private boolean helper(char[][] board, int i, int j, String word, int level, boolean[][] used){
        if(level == word.length()) {
        	for (String s: path)
        		System.out.println(s);
        	return true;
        }
        if(i > board.length-1 || i < 0 || j < 0 || j > board[0].length-1 || 
            used[i][j] || board[i][j] != word.charAt(level))
            return false;
        used[i][j] = true;
        path.add("(" + i + ", " + j + "):" + board[i][j]);
        boolean result = helper(board, i - 1, j, word, level + 1, used) ||
                helper(board, i, j - 1, word, level + 1, used) ||
                helper(board, i, j + 1, word, level + 1, used) ||
                helper(board, i + 1, j, word, level + 1, used);
        used[i][j] = false;
        path.remove(path.size() - 1);
        return result;
    }
    
	public static void main(String[] args) {
		WordSearch ws = new WordSearch();
		char[][] board = {
				{'A','B','C','E'},
				{'S','F','C','S'},
				{'A','D','E','E'} };
		ws.search(board, "ABCCED");
		System.out.println();
		ws.search(board, "SEE");
		System.out.println();
		ws.search(board, "ABCB");
	}

}
