package my.nalab5;

public class GameState {

	int[][] gameBoard = new int[Game.MAX_HORIZONTAL +1][Game.MAX_VERTICAL+ 1];
	int value;
	int blueScore;
	int redScore;

	public void copyArray(int[][] gameBoard) {
		for (int i = 0; i < gameBoard.length; i++) {
			for (int j = 0; j < gameBoard[i].length; j++) {
				this.gameBoard[i][j] = gameBoard[i][j];
			}
		}
	}
}
