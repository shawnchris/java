package leetcode;

public class A037_Sudoku_Solver {
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
    
    private boolean solve(char[][] board, boolean[][] preset, int level) {
    	if (level == 81) return true;
    	
    	int row = level / 9, col = level % 9;
    	int box = (row / 3) * 3 + (col / 3);
    	
    	if (preset[row][col]) {
    		if (solve(board, preset, level + 1)) return true;
    		return false;
    	}
    	
    	for (int i = 0; i < 9; i++) {
    		board[row][col] = (char)('1' + i);
    		if (!checkRow(board, row) || !checkCol(board, col) 
    				|| !checkBox(board, box)) continue;
    		if (solve(board, preset, level + 1)) return true;
    	}
    	board[row][col] = '.';
    	return false;
    }
    
    public void solveSudoku(char[][] board) {
    	boolean[][] preset = new boolean[9][9];
    	for (int i = 0; i < 9; i++)
    		for (int j = 0; j < 9; j++)
    			if (board[i][j] != '.') preset[i][j] = true;
        solve(board, preset, 0);
    }
    
	public static void main(String[] args) {
		A037_Sudoku_Solver ss = new A037_Sudoku_Solver();
//		String[] input1 = {
//				"..9748...",
//				"7........",
//				".2.1.9...",
//				"..7...24.",
//				".64.1.59.",
//				".98...3..",
//				"...8.3.2.",
//				"........6",
//				"...2759.."};
		String[] input1 = {
				"...25....",
				"......8.5",
				"..31...29",
				"....6..7.",
				"65.....92",
				".8..2....",
				"26...49..",
				"1.9......",
				"....83..."
		};
		char[][] board = new char[9][9];
		for (int i = 0; i < 9; i++)
    		for (int j = 0; j < 9; j++)
    			board[i][j] = input1[i].charAt(j);
		ss.solveSudoku(board);
		
		for (int i = 0; i < 9; i++) {
    		for (int j = 0; j < 9; j++)
    			System.out.print(board[i][j] + " ");
    		System.out.println();
		}
	}

}
