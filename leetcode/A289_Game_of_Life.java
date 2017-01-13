package leetcode;

//https://leetcode.com/discuss/68352/easiest-java-solution-with-explanation
public class A289_Game_of_Life {
	public void gameOfLife2(int[][] board) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                int count = countLife(board, i, j);
                if (board[i][j] == 1) { //live cell
                    if (count >= 2 && count <= 3) { //going to live
                        board[i][j] = 3;
                    }
                    //otherwise going to die
                }
                else { //dead cell
                    if (count == 3) { //going to live
                        board[i][j] = 2;
                    }
                }
            }
        }
        
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                board[i][j] >>= 1;
            }
        }
    }
    
    private int countLife(int[][] board, int row, int col) {
        int result = 0;
        for (int i = -1; i < 2; i++) {
            for (int j = -1; j < 2; j++) {
                int r = row + i, c = col + j;
                if (r < 0 || r >= board.length || c < 0 || c >= board[0].length
                    || (r == row && c == col)) continue;
                if ((board[r][c] & 1) == 1) result++;
            }
        }
        return result;
    }
	
    public void gameOfLife(int[][] board) {
        if(board == null || board.length == 0) return;
        int m = board.length, n = board[0].length;
    
        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {
                int lives = liveNeighbors(board, m, n, i, j);
    
                // In the beginning, every 2nd bit is 0;
                // So we only need to care about when 2nd bit will become 1.
                if((board[i][j] & 1) == 1 && (lives >= 2 && lives <= 3)) {
                    board[i][j] = 3; // Make the 2nd bit 1: 01 ---> 11
                }
                if((board[i][j] & 1) == 0 && lives == 3) {
                    board[i][j] = 2; // Make the 2nd bit 1: 00 ---> 10
                }
            }
        }
    
        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {
                board[i][j] >>= 1;  // Get the 2nd state.
            }
        }
    }
    
    public int liveNeighbors(int[][] board, int m, int n, int i, int j) {
        int lives = 0;
        for(int p = Math.max(i - 1, 0); p <= Math.min(i + 1, m - 1); p++) {
            for(int q = Math.max(j - 1, 0); q <= Math.min(j + 1, n - 1); q++) {
                lives += board[p][q] & 1;
            }
        }
        lives -= board[i][j] & 1;
        return lives;
    }
}
