package leetcode;

public class A348_Design_Tic_Tac_Toe {
	public class TicTacToe {
	    int[][] board;
	    int winner;
	    
	    /** Initialize your data structure here. */
	    public TicTacToe(int n) {
	        board = new int[n][n];
	        winner = 0;
	    }
	    
	    /** Player {player} makes a move at ({row}, {col}).
	        @param row The row of the board.
	        @param col The column of the board.
	        @param player The player, can be either 1 or 2.
	        @return The current winning condition, can be either:
	                0: No one wins.
	                1: Player 1 wins.
	                2: Player 2 wins. */
	    public int move(int row, int col, int player) {
	        if (winner == 0) {
	            board[row][col] = player;
	            if (checkRow(row, player) || checkCol(col, player) || checkDiag(player)) {
	                winner = player;
	            }
	        }
	        return winner;
	    }
	    
	    private boolean checkRow(int r, int p) {
	        for (int c = 0; c < board[0].length; c++) {
	            if (board[r][c] != p) return false;
	        }
	        return true;
	    }
	    
	    private boolean checkCol(int c, int p) {
	        for (int r = 0; r < board.length; r++) {
	            if (board[r][c] != p) return false;
	        }
	        return true;
	    }
	    
	    private boolean checkDiag(int p) {
	        boolean win = true;
	        for (int i = 0; i < board.length; i++) {
	            if (board[i][i] != p) {
	                win = false;
	                break;
	            }
	        }
	        if (win) return true;
	        win = true;
	        for (int i = 0; i < board.length; i++) {
	            if (board[i][board.length - i - 1] != p) {
	                win = false;
	                break;
	            }
	        }
	        return win;
	    }
	}

	/**
	 * Your TicTacToe object will be instantiated and called as such:
	 * TicTacToe obj = new TicTacToe(n);
	 * int param_1 = obj.move(row,col,player);
	 */
}
