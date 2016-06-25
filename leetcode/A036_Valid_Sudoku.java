package leetcode;

public class A036_Valid_Sudoku {

    private boolean checkRow(char[][] board, int row) {
        boolean[] used = new boolean[9];
        for (int i = 0; i < 9; i++) {
            if (board[row][i] == '.') continue;
            if (used[board[row][i] - '1'])
                return false;
            used[board[row][i] - '1'] = true;
        }
        return true;
    }
    private boolean checkCol(char[][] board, int col) {
    	boolean[] used = new boolean[9];
        for (int i = 0; i < 9; i++) {
            if (board[i][col] == '.') continue;
            if (used[board[i][col] - '1'])
                return false;
            used[board[i][col] - '1'] = true;
        }
        return true;
    }
    private boolean checkBox(char[][] board, int box) {
    	boolean[] used = new boolean[9];
        int startRow = (box / 3) * 3, startCol = (box % 3) * 3;
        for (int i = startRow; i < startRow + 3; i++) {
            for (int j = startCol; j < startCol + 3; j++) {
                if (board[i][j] == '.') continue;
                if (used[board[i][j] - '1'])
                    return false;
                used[board[i][j] - '1'] = true;
            }
        }
        return true;
    }
    
    public boolean isValidSudoku(char[][] board) {
        for (int i = 0; i < 9; i++) {
            if (!checkRow(board, i) || !checkCol(board, i) || !checkBox(board, i))
                return false;
        }
        
        return true;
    }
}
