package leetcode;

public class A419_Battleships_in_a_Board {
    public int countBattleships(char[][] board) {
        if (board == null || board.length == 0 || board[0].length == 0) {
            return 0;
        }
        
        int result = 0;
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] == '.') continue;
                if ( (i == 0 || (i > 0 && board[i - 1][j] == '.')) && (j == 0 || (j > 0 && board[i][j - 1] == '.')) ) {
                    result++;
                }
            }
        }
        
        return result;
    }
    
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
