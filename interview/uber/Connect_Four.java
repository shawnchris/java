package interview.uber;

public class Connect_Four {
	private int[][] board;
	private int state = 0; //0 - ended, 1 - playing
	private int winner = 0; //1 - player1, 2 - player2
	private int step = 0;
	
	public Connect_Four() {
		board = new int[6][7];
	}
	
	public void reset() {
		for (int i = 0; i < 6; i++) {
			for (int j = 0; j < 7; j++) {
				board[i][j] = 0;
			}
		}
		state = 1;
		winner = 0;
		step = 0;
	}
	
	public boolean play(int player, int col) {
		if (state != 1 || winner != 0) {
			System.out.println("Game is not running!");
			return false;
		}
		if (player != 1 && player != 2) {
			System.out.println("Invalid player number!");
			return false;
		}
		if (col < 0 || col >= 7) {
			System.out.println("Invalid column number!");
			return false;
		}
		if (board[0][col] != 0) {
			System.out.println("This column is full!");
			return false;
		}
		if ((step + 1) % 2 != player % 2) {
			System.out.println("It's not player" + player +"'s turn!");
			return false;
		}
		
		step++;
		for (int row = 5; row >= 0; row--) {
			if (board[row][col] == 0) {
				board[row][col] = player;
				checkWin(player, row, col);
				break;
			}
		}
		
		return true;
	}
	
	private void checkWin(int player, int row, int col) {
		int[] dr = {0, 0, -1, 1, -1, 1, -1, 1};
		int[] dc = {-1, 1, 0, 0, -1, 1, 1, -1};
		
		for (int k = 0; k < dr.length; k += 2) {
			int i = row + dr[k], j = col + dc[k], total = 1;
			while (i > 0 && i < 6 && j > 0 && j < 7 && board[i][j] == player) {
				total++;
				i += dr[k];
				j += dc[k];
			}
			i = row + dr[k + 1]; j = col + dc[k + 1];
			while (i > 0 && i < 6 && j > 0 && j < 7 && board[i][j] == player) {
				total++;
				i += dr[k + 1];
				j += dc[k + 1];
			}
			if (total >= 4) {
				state = 0;
				winner = player;
				System.out.println("Winner is player"+player);
				return;
			}
		}
	}
	
	public void print() {
		for (int i = 0; i < 6; i++) {
			for (int j = 0; j < 7; j++) {
				System.out.print(board[i][j] + " ");
			}
			System.out.println();
		}
		System.out.println("Step: " + step + " State: " + state + " Winner: " + winner);
		System.out.println("---------------------------");
	}
	
	public static void main(String[] args) {
		Connect_Four game = new Connect_Four();
		int[][][] plays = {
				{{1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2},
				{1, 1, 2, 1, 3, 1, 4, 1, 5, 1, 6, 1}},
				{{1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2},
				{1, 2, 2, 3, 2, 4, 2, 5, 1, 6, 2, 1},
				}
		};
		for (int play = 0; play < plays.length; play++) {
			game.reset();
			for (int step = 0; step < plays[play][0].length; step++) {
				game.play(plays[play][0][step], plays[play][1][step]);
				game.print();
			}
		}

	}

}
