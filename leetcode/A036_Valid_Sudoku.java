package leetcode;
import java.util.*;

public class A036_Valid_Sudoku {
private Set<Character> set = new HashSet<>();
    
    private boolean checkRow(char[][] board, int row) {
        set.clear();
        for (int i = 0; i < 9; i++) {
            if (board[row][i] == '.') continue;
            if (set.contains(board[row][i]))
                return false;
            set.add(board[row][i]);
        }
        return true;
    }
    private boolean checkCol(char[][] board, int col) {
        set.clear();
        for (int i = 0; i < 9; i++) {
            if (board[i][col] == '.') continue;
            if (set.contains(board[i][col]))
                return false;
            set.add(board[i][col]);
        }
        return true;
    }
    private boolean checkBox(char[][] board, int box) {
        set.clear();
        int startRow = (box / 3) * 3, startCol = (box % 3) * 3;
        for (int i = startRow; i < startRow + 3; i++) {
            for (int j = startCol; j < startCol + 3; j++) {
                if (board[i][j] == '.') continue;
                if (set.contains(board[i][j]))
                    return false;
                set.add(board[i][j]);
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
