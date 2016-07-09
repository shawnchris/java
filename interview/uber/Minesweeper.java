package interview.uber;
import java.util.*;

//=============Cell===============
class Cell {
	private int row;
	private int column;
	private boolean isBomb;
	private int number;
	private boolean isExposed = false;
	private boolean isGuess = false;

	public Cell(int r, int c) {
		isBomb = false;
		number = 0;
		row = r;
		column = c;
	}

	public void setRowAndColumn(int r, int c) {
		row = r;
		column = c;
	}

	public void setBomb(boolean bomb) {
		isBomb = bomb;
		number = -1;
	}

	public void incrementNumber() {
		number++;
	}

	public int getRow() {
		return row;
	}

	public int getColumn() {
		return column;
	}

	public boolean isBomb() {
		return isBomb;
	}

	public boolean isBlank() {
		return number == 0;
	}

	public boolean isExposed() {
		return isExposed;
	}

	public boolean flip() {
		isExposed = true;
		return !isBomb;
	}

	public boolean toggleGuess() {
		if (!isExposed) {
			isGuess = !isGuess;
		}
		return isGuess;
	}

	public boolean isGuess() {
		return isGuess;
	}

	@Override
	public String toString() {
		return getUndersideState();
	}

	public String getSurfaceState() {
		if (isExposed) {
			return getUndersideState();
		} else if (isGuess) {
			return "B ";
		} else {
			return "? ";
		}
	}

	public String getUndersideState() {
		if (isBomb) {
			return "* ";
		} else if (number > 0) {
			return Integer.toString(number) + " ";
		} else {
			return "  ";
		}
	}
}

//=============Board===============
class Board {
	private int nRows;
	private int nColumns;
	private int nBombs = 0;
	private Cell[][] cells; 
	private Cell[] bombs;
	private int numUnexposedRemaining;
	
	
	public Board(int r, int c, int b) {
		nRows = r;
		nColumns = c;
		nBombs = b;
		
		initializeBoard();
		shuffleBoard();
		setNumberedCells();
		
		numUnexposedRemaining = nRows * nColumns - nBombs;
	}
	
	private void initializeBoard() {
		cells = new Cell[nRows][nColumns];
		bombs = new Cell[nBombs];
		for (int r = 0; r < nRows; r++) {
			for (int c = 0; c < nColumns; c++) {
				cells[r][c] = new Cell(r, c);
			}
		}
		
		for (int i = 0; i < nBombs; i++) {
			int r = i / nColumns;
			int c = (i - r * nColumns) % nColumns;
			bombs[i] = cells[r][c];
			bombs[i].setBomb(true);
		}
	}
	
	private void shuffleBoard() {
		int nCells = nRows * nColumns;
		Random random = new Random();
		for (int index1 = 0; index1 < nCells; index1++) {
			int index2 = index1 + random.nextInt(nCells - index1);
			if (index1 != index2) {
				/* Get cell at index1. */
				int row1 = index1 / nColumns;
				int column1 = (index1 - row1 * nColumns) % nColumns;
				Cell cell1 = cells[row1][column1];
				
				/* Get cell at index2. */
				int row2 = index2 / nColumns;
				int column2 = (index2 - row2 * nColumns) % nColumns;
				Cell cell2 = cells[row2][column2];
				
				/* Swap. */
				cells[row1][column1] = cell2;
				cell2.setRowAndColumn(row1, column1);
				cells[row2][column2] = cell1;
				cell1.setRowAndColumn(row2, column2);				
			}
		}
	}
	
	private boolean inBounds(int row, int column) {
		return row >= 0 && row < nRows && column >= 0 && column < nColumns;
	}
	
	/* Set the cells around the bombs to the right number. Although 
	 * the bombs have been shuffled, the reference in the bombs array
	 * is still to same object. */
	private void setNumberedCells() {
		int[][] deltas = { // Offsets of 8 surrounding cells
				{-1, -1}, {-1, 0}, {-1, 1},
				{ 0, -1},          { 0, 1},
				{ 1, -1}, { 1, 0}, { 1, 1}
		};
		for (Cell bomb : bombs) {
			int row = bomb.getRow();
			int col = bomb.getColumn();
			for (int[] delta : deltas) {
				int r = row + delta[0];
				int c = col + delta[1];
				if (inBounds(r, c)) {
					cells[r][c].incrementNumber();
				}
			}
		}
	}
	
	public void printBoard(boolean showUnderside) {
		System.out.println();
		System.out.print("   ");
		for (int i = 0; i < nColumns; i++) {
			System.out.print(i + " ");
		}
		System.out.println();
		for (int i = 0; i < nColumns; i++) {
			System.out.print("--");
		}		
		System.out.println();
		for (int r = 0; r < nRows; r++) {
			System.out.print(r + "| ");
			for (int c = 0; c < nColumns; c++) {
				if (showUnderside) {
					System.out.print(cells[r][c].getUndersideState());
				} else {
					System.out.print(cells[r][c].getSurfaceState());
				}
			}
			System.out.println();
		}
	}
	
	private boolean flipCell(Cell cell) {
		if (!cell.isExposed() && !cell.isGuess()) {
			cell.flip();
			numUnexposedRemaining--;
			return true;
		} 
		return false;
	}
	
	public void expandBlank(Cell cell) {
		int[][] deltas = {
				{-1, -1}, {-1, 0}, {-1, 1},
				{ 0, -1},          { 0, 1},
				{ 1, -1}, { 1, 0}, { 1, 1}
		};		
		
		Queue<Cell> toExplore = new LinkedList<Cell>();
		toExplore.add(cell);
		
		while (!toExplore.isEmpty()) {
			Cell current = toExplore.remove();
			
			for (int[] delta : deltas) {
				int r = current.getRow() + delta[0];
				int c = current.getColumn() + delta[1];
				
				if (inBounds(r, c)) {
					Cell neighbor = cells[r][c];
					if (flipCell(neighbor) && neighbor.isBlank()) {
						toExplore.add(neighbor);
					}
				}
			}			
		}
	}
	
	public UserPlayResult playFlip(UserPlay play) {
		Cell cell = getCellAtLocation(play);
		if (cell == null) {
			return new UserPlayResult(false, Game.GameState.RUNNING);
		}
		
		if (play.isGuess()) {
			boolean guessResult = cell.toggleGuess();
			return new UserPlayResult(guessResult, Game.GameState.RUNNING);
		}
		
		boolean result = flipCell(cell);
		
		if (cell.isBomb()) {
			return new UserPlayResult(result, Game.GameState.LOST);
		}
		
		if (cell.isBlank()) {
			expandBlank(cell);
		}
		
		if (numUnexposedRemaining == 0) {
			return new UserPlayResult(result, Game.GameState.WON);
		} 
		
		return new UserPlayResult(result, Game.GameState.RUNNING);
	}
	
	public Cell getCellAtLocation(UserPlay play) {
		int row = play.getRow();
		int col = play.getColumn();
		if (!inBounds(row, col)) {
			return null;
		}
		return cells[row][col];		
	}
	
	public int getNumRemaining() {
		return numUnexposedRemaining;
	}
}

//============Game=================
class Game {
	public enum GameState {
		WON, LOST, RUNNING
	}
	
	private Board board;
	private int rows;
	private int columns;
	private int bombs;
	private GameState state;
	
	public Game(int r, int c, int b) {
		rows = r;
		columns = c;
		bombs = b;
		state = GameState.RUNNING;
	}	
	
	public boolean initialize() {
		if (board == null) {
			board = new Board(rows, columns, bombs);	
			board.printBoard(true);
			return true;
		} else {
			System.out.println("Game has already been initialized.");
			return false;
		}
	}	
	
	public boolean start() {
		if (board == null) {
			initialize();
		}
		return playGame();
	}
	
	public void printGameState() {
		if (state == GameState.LOST) {
			board.printBoard(true);
			System.out.println("FAIL");
		} else if (state == GameState.WON) {
			board.printBoard(true);
			System.out.println("WIN");
		} else {
			System.out.println("Number remaining: " + board.getNumRemaining());			
			board.printBoard(false);	
		}
	}
	
	private boolean playGame() {
		Scanner scanner = new Scanner(System.in);
		printGameState();
		
		while (state == GameState.RUNNING) {
			String input = scanner.nextLine();
			if (input.equals("exit")) {
				scanner.close();
				return false;
			}
			
			UserPlay play = UserPlay.fromString(input);
			if (play == null) { 
				continue;
			}
			
			UserPlayResult result = board.playFlip(play);
			if (result.successfulMove()) {
				state = result.getResultingState();
			} else {
				System.out.println("Could not flip cell (" + play.getRow() + "," + play.getColumn() + ").");
			} 
			printGameState();
		}	
		scanner.close();
		return true;
	}	
}

//============UserPlay===============
class UserPlay {
	private int row;
	private int column;
	private boolean isGuess;
	
	private UserPlay(int r, int c, boolean guess) {
		setRow(r);
		setColumn(c);
		isGuess = guess;
	}
	
	public static UserPlay fromString(String input) {
		boolean isGuess = false;
		
		if (input.length() > 0 && input.charAt(0) == 'B') {
			isGuess = true;
			input = input.substring(1);
		}
		
		if (!input.matches("\\d* \\d+")) {
			return null;
		}
		
		String[] parts = input.split(" ");
		try {
			int r = Integer.parseInt(parts[0]);
			int c = Integer.parseInt(parts[1]);
			return new UserPlay(r, c, isGuess);
		} catch (NumberFormatException e) {
			return null;
		}
	}

	public boolean isGuess() {
		return isGuess;
	}
	
	public boolean isMove() {
		return !isMove();
	}

	public int getColumn() {
		return column;
	}

	public void setColumn(int column) {
		this.column = column;
	}

	public int getRow() {
		return row;
	}

	public void setRow(int row) {
		this.row = row;
	}
}


//=============UserPlayResult============
class UserPlayResult {
	private boolean successful;
	private Game.GameState resultingState;
	public UserPlayResult(boolean success, Game.GameState state) {
		successful = success;
		resultingState = state;
	}
	
	public boolean successfulMove() {
		return successful;
	}
	
	public Game.GameState getResultingState() {
		return resultingState;
	}
}

public class Minesweeper {
	public static void main(String[] args) {
		Game game = new Game(7, 7, 3);
		game.initialize();
		game.start();
	}
}
