package leetcode;

public class A079_Word_Search {
    public boolean exist(char[][] board, String word) {
        if (board == null || word == null) return false;
        int m = board.length;
        if (m == 0) return false;
        int n = board[0].length;
        if (n == 0) return false;
        
        boolean[][] used = new boolean[m][n];
        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++){
                if(helper(board, i, j, word, 0, used)) {
                    return true;
                }
            }
        }
        return false;
    }
    private boolean helper(char[][] board, int i, int j, String word, int level, boolean[][] used){
        if(level == word.length()) return true;
        if(i > board.length-1 || i < 0 || j < 0 || j > board[0].length-1 || 
            used[i][j] || board[i][j] != word.charAt(level))
            return false;
        used[i][j] = true;
        boolean result = helper(board, i - 1, j, word, level + 1, used) ||
                helper(board, i, j - 1, word, level + 1, used) ||
                helper(board, i, j + 1, word, level + 1, used) ||
                helper(board, i + 1, j, word, level + 1, used);
        used[i][j] = false;
        return result;
    }
}
