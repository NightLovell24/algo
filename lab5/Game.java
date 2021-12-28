package my.nalab5;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Game {

	private Player blue;
	private Player red; // ходит первый
	public static final int MAX_HORIZONTAL = 19;
	public static final int MAX_VERTICAL = 19;
	private static final int MAX_SCORE = 10;
	private int[][] gameBoard = new int[MAX_HORIZONTAL + 1][MAX_VERTICAL + 1]; // 1 - кр, 2 - синий
	private Game.SIDE realPlayerSide;
	private Game.DIFFICULTY gameDifficulty;

	public Game() {
		blue = new Player();
		red = new Player();
	}

	public void setSide(SIDE side) {

		realPlayerSide = side;
	}

	public void setDifficulty(DIFFICULTY difficulty) {
		gameDifficulty = difficulty;
	}

	private boolean isGameEnded() // игру ведем до 10 очков
	{
		if (blue.getScore() >= MAX_SCORE || red.getScore() >= MAX_SCORE) {
			return true;
		} else {
			if (hasEmptyCell()) {
				return false;
			} else {
				return true;
			}
		}

	}

	private boolean isDotCaptured(int startX, int startY, int color) { // 1 red 2 blue

		if (!(color == 1 || color == 2)) {
			return false;
		}
		if (gameBoard[startX][startY] == 0) {
			return false;
		}

		List<Dot> usedDots = new ArrayList<>();
		Dot dot = getDot(startX, startY, startX, startY, color, usedDots); // получаем стартового соседа
		if (dot.x == -1 || dot.y == -1) {
			return false;
		}
		Dot firstDot = new Dot(dot.x, dot.y); // первый стартовый сосед

		usedDots.add(firstDot);
		Dot tempDot = getDot(startX, startY, firstDot.x, firstDot.y, color, usedDots);
		if (tempDot.x == -1 || tempDot.y == -1) {
			return false;
		}
		usedDots.add(tempDot);

		int counter = 0;
		while (!(tempDot.x == firstDot.x && tempDot.y == firstDot.y)) {

			if (counter == 1) {
				usedDots.remove(firstDot);
			}

			tempDot = getDot(startX, startY, tempDot.x, tempDot.y, color, usedDots);
			usedDots.add(tempDot);
			if (tempDot.x == -1 || tempDot.y == -1) {
				return false;
			}
			counter++;

		}
		if (tempDot.x == firstDot.x && tempDot.y == firstDot.y) {
			return true;
		}

		return false;
	}

	private Dot getDot(int startX, int startY, int currentX, int currentY, int color, List<Dot> usedDots) { // метод
																											// возвращает
																											// координаты
		// соседа с лучшей
		// евристикой
		Dot dot = new Dot();

		int xUp = currentX - 1;
		int yUp = currentY;
		double upDist = -1;

		int xRight = currentX;
		int yRight = currentY + 1;
		double rightDist = -1;

		int xDown = currentX + 1;
		int yDown = currentY;
		double downDist = -1;

		int xLeft = currentX;
		int yLeft = currentY - 1;
		double leftDist = -1;

		int XUpLeft = currentX - 1;
		int YUpLeft = currentY + -1;
		double upLeftDist = -1;

		int XUpRight = currentX - 1;
		int YUpRight = currentY + 1;
		double upRightDist = -1;

		int XDownLeft = currentX + 1;
		int YDownLeft = currentY - 1;
		double downLeftDist = -1;

		int XDownRight = currentX + 1;
		int YDownRight = currentY + 1;
		double downRightDist = -1;

		if (xUp <= MAX_HORIZONTAL && xUp >= 0 && yUp <= MAX_VERTICAL && yUp >= 0) { // если точка валидна€

			if ((color == 1 && gameBoard[xUp][yUp] == 2) || (color == 2 && gameBoard[xUp][yUp] == 1)) {
				boolean contains = false;

				for (Dot doty : usedDots) {
					if (doty.x == xUp && doty.y == yUp) {
						contains = true;
						break;
					}
				}
				if (!contains) {

					upDist = Math.sqrt(((startX - xUp) * (startX - xUp)) + (startY - yUp) * (startY - yUp));
				}
			}

		}
		if (xDown <= MAX_HORIZONTAL && xDown >= 0 && yDown <= MAX_VERTICAL && yDown >= 0) { // если точка валидна€

			if ((color == 1 && gameBoard[xDown][yDown] == 2) || (color == 2 && gameBoard[xDown][yDown] == 1)) {
				boolean contains = false;

				for (Dot doty : usedDots) {
					if (doty.x == xDown && doty.y == yDown) {
						contains = true;
						break;
					}
				}
				if (!contains) {
					downDist = Math.sqrt(((startX - xDown) * (startX - xDown)) + (startY - yDown) * (startY - yDown));
				}
			}

		}
		if (xLeft <= MAX_HORIZONTAL && xLeft >= 0 && yLeft <= MAX_VERTICAL && yLeft >= 0) { // если точка валидна€

			if ((color == 1 && gameBoard[xLeft][yLeft] == 2) || (color == 2 && gameBoard[xLeft][yLeft] == 1)) {
				boolean contains = false;

				for (Dot doty : usedDots) {
					if (doty.x == xLeft && doty.y == yLeft) {
						contains = true;
						break;
					}
				}

				if (!contains) {
					leftDist = Math.sqrt(((startX - xLeft) * (startX - xLeft)) + (startY - yLeft) * (startY - yLeft));
				}
			}
		}
		if (xRight <= MAX_HORIZONTAL && xRight >= 0 && yRight <= MAX_VERTICAL && yRight >= 0) { // если точка валидна€

			if ((color == 1 && gameBoard[xRight][yRight] == 2) || (color == 2 && gameBoard[xRight][yRight] == 1)) {
				boolean contains = false;

				for (Dot doty : usedDots) {
					if (doty.x == xRight && doty.y == yRight) {
						contains = true;
						break;
					}
				}
				if (!contains) {
					rightDist = Math
							.sqrt(((startX - xRight) * (startX - xRight)) + (startY - yRight) * (startY - yRight));
				}
			}

		}
		if (XUpLeft <= MAX_HORIZONTAL && XUpLeft >= 0 && YUpLeft <= MAX_VERTICAL && YUpLeft >= 0) { // если точка
																									// валидна€

			if ((color == 1 && gameBoard[XUpLeft][YUpLeft] == 2) || (color == 2 && gameBoard[XUpLeft][YUpLeft] == 1)) {
				boolean contains = false;

				for (Dot doty : usedDots) {
					if (doty.x == XUpLeft && doty.y == YUpLeft) {
						contains = true;
						break;
					}
				}
				if (!contains) {
					upLeftDist = Math
							.sqrt(((startX - XUpLeft) * (startX - XUpLeft)) + (startY - YUpLeft) * (startY - YUpLeft));
				}
			}

		}
		if (XUpRight <= MAX_HORIZONTAL && XUpRight >= 0 && YUpRight <= MAX_VERTICAL && YUpRight >= 0) { // если точка
																										// валидна€

			if ((color == 1 && gameBoard[XUpRight][YUpRight] == 2)
					|| (color == 2 && gameBoard[XUpRight][YUpRight] == 1)) {
				boolean contains = false;

				for (Dot doty : usedDots) {
					if (doty.x == XUpRight && doty.y == YUpRight) {
						contains = true;
						break;
					}
				}
				if (!contains) {
					upRightDist = Math.sqrt(
							((startX - XUpRight) * (startX - XUpRight)) + (startY - YUpRight) * (startY - YUpRight));
				}
			}

		}
		if (XDownRight <= MAX_HORIZONTAL && XDownRight >= 0 && YDownRight <= MAX_VERTICAL && YDownRight >= 0) { // если
																												// точка
																												// валидна€

			if ((color == 1 && gameBoard[XDownRight][YDownRight] == 2)
					|| (color == 2 && gameBoard[XDownRight][YDownRight] == 1)) {
				boolean contains = false;

				for (Dot doty : usedDots) {
					if (doty.x == XDownRight && doty.y == YDownRight) {
						contains = true;
						break;
					}
				}
				if (!contains) {
					downRightDist = Math.sqrt(((startX - XDownRight) * (startX - XDownRight))
							+ (startY - YDownRight) * (startY - YDownRight));
				}
			}

		}
		if (XDownLeft <= MAX_HORIZONTAL && XDownLeft >= 0 && YDownLeft <= MAX_VERTICAL && YDownLeft >= 0) { // если
																											// точка
																											// валидна€

			if ((color == 1 && gameBoard[XDownLeft][YDownLeft] == 2)
					|| (color == 2 && gameBoard[XDownLeft][YDownLeft] == 1)) {
				boolean contains = false;

				for (Dot doty : usedDots) {
					if (doty.x == XDownLeft && doty.y == YDownLeft) {
						contains = true;
						break;
					}
				}
				if (!contains) {
					downLeftDist = Math.sqrt(((startX - XDownLeft) * (startX - XDownLeft))
							+ (startY - YDownLeft) * (startY - YDownLeft));
				}
			}

		}

		double[] distances = { upDist, downDist, rightDist, leftDist, upLeftDist, upRightDist, downLeftDist,
				downRightDist };

		double minDist = Arrays.stream(distances).filter(el -> {
			return el >= 0;
		}).min().orElse(0);

		if (upDist == minDist) {
			dot.x = xUp;
			dot.y = yUp;
		} else if (downDist == minDist) {
			dot.x = xDown;
			dot.y = yDown;
		} else if (rightDist == minDist) {
			dot.x = xRight;
			dot.y = yRight;
		} else if (leftDist == minDist) {
			dot.x = xLeft;
			dot.y = yLeft;
		} else if (upLeftDist == minDist) {
			dot.x = XUpLeft;
			dot.y = YUpLeft;
		} else if (upRightDist == minDist) {
			dot.x = XUpRight;
			dot.y = YUpRight;
		} else if (downRightDist == minDist) {
			dot.x = XDownRight;
			dot.y = YDownRight;
		} else if (downLeftDist == minDist) {
			dot.x = XDownLeft;
			dot.y = YDownLeft;
		}

		else {
			dot.x = -1;
			dot.y = -1;
		}

		return dot;

	}

	private int[] updateScore(int[][] board) { // 0 - red, 1 - blue
		int blueScore = 0;
		int redScore = 0;
		int[] score = new int[2];
		for (int x = 0; x < board.length; x++) {
			for (int y = 0; y < board[x].length; y++) {

				if (x == 0 || y == 0 || x == MAX_HORIZONTAL || y == MAX_VERTICAL) {
					continue;
				}

				int value = board[x][y];
				if (value == 0) {
					continue;
				}
				if (value == 1) { // кр

					if (isDotCaptured(x, y, 1)) {
						blueScore++;
					}

				}
				if (value == 2) { // синие
					if (isDotCaptured(x, y, 2)) {
						redScore++;
					}
				}
			}
		}
		blue.setScore(blueScore);
		red.setScore(redScore);

		score[0] = redScore;
		score[1] = blueScore;

		return score;

	}

	private void doMove(Player p, SIDE side) {

		if (side == realPlayerSide) {

			System.out.println("’од игрока..");

			int[] coords = Main.tryMove();
			while (!canMove(coords[0], coords[1])) {
				coords = Main.tryMove();
			}
			if (side == SIDE.RED) {
				gameBoard[coords[0]][coords[1]] = 1;
			}
			if (side == SIDE.BLUE) {
				gameBoard[coords[0]][coords[1]] = 2;
			}

			printGameBoard();

		} else {
			System.out.println("’од бота..");
			if (gameDifficulty == DIFFICULTY.EASY) {
				int x = (int) (Math.random() * (MAX_HORIZONTAL + 1));
				int y = (int) (Math.random() * (MAX_VERTICAL + 1));

				while (!canMove(x, y)) {
					x = (int) (Math.random() * (MAX_HORIZONTAL + 1));
					y = (int) (Math.random() * (MAX_VERTICAL + 1));
				}
				if (side == SIDE.RED) {
					gameBoard[x][y] = 1;
				}
				if (side == SIDE.BLUE) {
					gameBoard[x][y] = 2;
				}

			}
			if (gameDifficulty == DIFFICULTY.MIDDLE) {

				int minCol = 0;
				int maxCol = MAX_HORIZONTAL;
				int minCell = 0;
				int maxCell = MAX_VERTICAL;

				int lastI = -1;
				int lastJ = -1;
				for (int i = 0; i < gameBoard.length; i++) {
					for (int j = 0; j < gameBoard[i].length; j++) {

						if (gameBoard[i][j] != 0) {
							minCol = i;
							minCell = j;

							if (i > lastI) {
								lastI = i;
							}

							if (j > lastJ) {
								lastJ = j;
							}

						}

					}
				}
				maxCol = lastI;
				maxCell = lastJ;

				if (minCol >= 1) {
					minCol--;
				}
				if (minCell >= 1) {
					minCell--;
				}
				if (maxCol < MAX_HORIZONTAL) {
					maxCol++;
				}
				if (maxCell < MAX_VERTICAL) {
					maxCell++;
				}
				/////// определение условного пр€мугольника дл€ оптимизации
				if (side == SIDE.RED) {
					tryMove(new GameState(), 1, side, minCol, minCell, maxCol, maxCell);
				}
				if (side == SIDE.BLUE) {
					tryMove(new GameState(), 1, side, minCol, minCell, maxCol, maxCell);
				}

			}
			if (gameDifficulty == DIFFICULTY.HARD) {
				// DEPTH!
				int minCol = 0;
				int maxCol = MAX_HORIZONTAL;
				int minCell = 0;
				int maxCell = MAX_VERTICAL;

				int lastI = -1;
				int lastJ = -1;
				for (int i = 0; i < gameBoard.length; i++) {
					for (int j = 0; j < gameBoard[i].length; j++) {

						if (gameBoard[i][j] != 0) {
							minCol = i;
							minCell = j;

							if (i > lastI) {
								lastI = i;
							}

							if (j > lastJ) {
								lastJ = j;
							}

						}

					}
				}
				maxCol = lastI;
				maxCell = lastJ;

				if (minCol >= 1) {
					minCol--;
				}
				if (minCell >= 1) {
					minCell--;
				}
				if (maxCol < MAX_HORIZONTAL) {
					maxCol++;
				}
				if (maxCell < MAX_VERTICAL) {
					maxCell++;
				}
				/////// определение условного пр€мугольника дл€ оптимизации
				if (side == SIDE.RED) {
					tryMove(new GameState(), 2, side, minCol, minCell, maxCol, maxCell);
				}
				if (side == SIDE.BLUE) {
					tryMove(new GameState(), 2, side, minCol, minCell, maxCol, maxCell);
				}
			}
			printGameBoard();
		}
		updateScore(gameBoard);
		System.out.println("“екущий счет : " + "синие - " + blue.getScore() + " красные - " + red.getScore());

	}

	private void tryMove(GameState currentState, int depth, SIDE side, int minCol, int minCell, int maxCol,
			int maxCell) {

		int alpha = Integer.MIN_VALUE;
		int beta = Integer.MAX_VALUE;
		int rate = miniMax(currentState, depth, alpha, beta, side, minCol, minCell, maxCol, maxCell);
		int bestScore = -Integer.MAX_VALUE;
		GameState gameState = new GameState();
		GameState bestMove = null;
		for (GameState child : getStates(gameState, minCol, maxCol, minCell, maxCell, side)) {
			if (bestMove == null) {
				bestMove = child;
			}
//	        alpha = Math.max(alpha, miniMax(child, plyDepth - 1, alpha, beta));
			if (alpha > bestScore) {
				if (alpha >= beta && rate >= 0) {
					bestMove = child;
					bestScore = alpha;
					rate++;

				}
			}
		}

//		for (int i = minCol; i < maxCol; i++) {
//			for (int j = minCell; j < minCell; j++) {
//				if (side == SIDE.BLUE) {
//					if (gameBoard[i][j] == 1) {
//						List<Dot> listStates = new ArrayList<>();
//
//						if (i + 1 <= MAX_HORIZONTAL && j + 1 <= MAX_VERTICAL) {
//							if (gameBoard[i + 1][j + 1] == 0) {
//								listStates.add(new Dot(i + 1, j + 1));
//							}
//						}
//						if (i - 1 >= 0 && j + 1 <= MAX_VERTICAL) {
//							if (gameBoard[i - 1][j + 1] == 0) {
//
//								listStates.add(new Dot(i - 1, j + 1));
//							}
//						}
//						if (i + 1 <= MAX_HORIZONTAL && j - 1 >= 0) {
//							if (gameBoard[i + 1][j - 1] == 0) {
//
//								listStates.add(new Dot(i + 1, j - 1));
//							}
//						}
//						if (i - 1 >= 0 && j - 1 >= 0) {
//							if (gameBoard[i - 1][j - 1] == 0) {
//								listStates.add(new Dot(i - 1, j - 1));
//							}
//						}
//						if (listStates.size() == 0) {
//							continue;
//						} else {
//
//							int rand = (int) (Math.random() * listStates.size());
//							Dot currentDot = listStates.get(rand);
//							gameBoard[currentDot.x][currentDot.y] = 2;
//
//						}
//					}
//				}
//				if (side == SIDE.RED) {
//					if (gameBoard[i][j] == 2) {
//						List<Dot> listStates = new ArrayList<>();
//
//						if (i + 1 <= MAX_HORIZONTAL && j + 1 <= MAX_VERTICAL) {
//							if (gameBoard[i + 1][j + 1] == 0) {
//								listStates.add(new Dot(i + 1, j + 1));
//							}
//						}
//						if (i - 1 >= 0 && j + 1 <= MAX_VERTICAL) {
//							if (gameBoard[i - 1][j + 1] == 0) {
//
//								listStates.add(new Dot(i - 1, j + 1));
//							}
//						}
//						if (i + 1 <= MAX_HORIZONTAL && j - 1 >= 0) {
//							if (gameBoard[i + 1][j - 1] == 0) {
//
//								listStates.add(new Dot(i + 1, j - 1));
//							}
//						}
//						if (i - 1 >= 0 && j - 1 >= 0) {
//							if (gameBoard[i - 1][j - 1] == 0) {
//								listStates.add(new Dot(i - 1, j - 1));
//							}
//						}
//						if (listStates.size() == 0) {
//							continue;
//						} else {
//
//							int rand = (int) (Math.random() * listStates.size());
//							Dot currentDot = listStates.get(rand);
//							gameBoard[currentDot.x][currentDot.y] = 1;
//
//						}
//					}
//				}
//			}
//		}
		if (side == SIDE.RED) {
			List<Dot> listDot = new ArrayList<>();
			for (int i = 0; i < gameBoard.length; i++) {
				for (int j = 0; j < gameBoard[i].length; j++) {
					if (gameBoard[i][j] == 2) {
						listDot.add(new Dot(i, j));
					}
				}
			}
			for (Dot dot : listDot) {
				if (dot.x > 0) {
					if (gameBoard[dot.x - 1][dot.y] == 0) {
						gameBoard[dot.x - 1][dot.y] = 1;
					}
				}
			}
		}
		if (side == SIDE.BLUE) {
			List<Dot> listDot = new ArrayList<>();
			for (int i = 0; i < gameBoard.length; i++) {
				for (int j = 0; j < gameBoard[i].length; j++) {
					if (gameBoard[i][j] == 1) {
						listDot.add(new Dot(i, j));
					}
				}
			}
			for (Dot dot : listDot) {
				if (dot.x > 0) {
					if (gameBoard[dot.x - 1][dot.y] == 0) {
						gameBoard[dot.x - 1][dot.y] = 2;
					}
				}
			}
		}

	}

	private List<GameState> getStates(GameState state, int minCol, int maxCol, int minCell, int maxCell, SIDE side) {
		List<GameState> statesOfGame = new ArrayList<>();
		for (int i = minCol; i < maxCol; i++) {
			for (int j = minCell; j < maxCell; j++) {

				GameState newState = new GameState();
				newState.copyArray(gameBoard);
				if (state.gameBoard[i][j] == 0) {
					if (side == SIDE.RED) {
						state.gameBoard[i][j] = 1;
					}
					if (side == SIDE.BLUE) {
						state.gameBoard[i][j] = 2;
					}
				}
				statesOfGame.add(newState);

			}
		}
		return statesOfGame;
	}

//	private int minimax(GameState state, SIDE side)
//	{
//		if (side==SIDE.BLUE)
//		{
//			if ()
//		}
//	}
	private boolean terminalNode(GameState state, SIDE side) {
		if (side == SIDE.RED) {
			return state.redScore > state.blueScore;
		} else if (side == SIDE.BLUE) {
			return state.blueScore > state.redScore;
		} else {
			return false;
		}
	}

	private int miniMax(GameState currentState, int depth, int alpha, int beta, SIDE side, int minCol, int minCell,
			int maxCol, int maxCell) {
		if (depth <= 0 || terminalNode(currentState, side)) {
			return currentState.value;
		}

//	    }
		else {
			for (GameState staty : getStates(currentState, minCol, maxCol, minCell, maxCell, side)) {

				beta = Math.min(beta, miniMax(staty, depth - 1, alpha, beta, side, minCol, minCell, maxCol, maxCell));

				if (alpha >= beta) {
					return alpha;
				}
			}
			return beta;
		}
//		if(currentState.value == 1)
//	    {
//	        for(GameState child: getStates((params..)
//	        {
//	            alpha = Math.max(alpha, miniMax(child, depth - 1, alpha, beta));
//
//	            if(alpha >= beta)
//	            {
//	                return beta;
//	            }
//	        }
//	        return alpha;
	}
//	private int minimaxAlgorithm(int gameBoard[][], int minCol, int maxCol, int minCell, int maxCell, SIDE side, int depth) {
//		
//		if (side == SIDE.RED)
//		{
//			if (redUpScore()) return +1;
//			if (blueUpScore()) return -1;
//		}

//		int tempBoard[][] = new int[gameBoard.length][gameBoard[0].length];
//
//		for (int i = 0; i < gameBoard.length; i++) {
//			for (int j = 0; j < gameBoard[i].length; j++) {
//				tempBoard[i][j] = gameBoard[i][j];
//			}
//		}
//   
//		for (int i = minCol; i < maxCol; i++) {
//			for (int j = minCell; j < maxCell; j++) {
//
//				
//				
//				
//			}
//		}
//	}
	private boolean blueUpScore() {

//		int startBlueScore = updateScore(gameBoard)[1];
//
//		int tempBoard[][] = new int[gameBoard.length][gameBoard[0].length];
//
//		for (int i = 0; i < gameBoard.length; i++) {
//			for (int j = 0; j < gameBoard[i].length; j++) {
//				tempBoard[i][j] = gameBoard[i][j];
//			}
//		}
//		tempBoard[x][y] = 2;
//		int currentBlueScore = updateScore(tempBoard)[1];
//
//		return currentBlueScore > startBlueScore;
		return blue.getScore() > red.getScore();

	}

	private boolean redUpScore() {
//		int startRedScore = updateScore(gameBoard)[0];
//
//		int tempBoard[][] = new int[gameBoard.length][gameBoard[0].length];
//
//		for (int i = 0; i < gameBoard.length; i++) {
//			for (int j = 0; j < gameBoard[i].length; j++) {
//				tempBoard[i][j] = gameBoard[i][j];
//			}
//		}
//		tempBoard[x][y] = 1;
//		int currentRedScore = updateScore(tempBoard)[0];
//
//		return currentRedScore > startRedScore;
		return red.getScore() > blue.getScore();
	}

	private boolean canMove(int x, int y) {

		if (x < 0 || y < 0 || x > MAX_HORIZONTAL || y > MAX_VERTICAL) {
			return false;
		}
		if (gameBoard[x][y] == 0) {
			return true;
		} else {
			return false;
		}

	}

	public void startGame() {

//		gameBoard[1][2] = 1;
//		gameBoard[0][2] = 2;
//		gameBoard[2][2] = 2;
//		gameBoard[1][1] = 2;
//		gameBoard[1][3] = 2;
//		
//		printGameBoard();
//		updateScore();
//		System.out.println("“екущий счет : " + "синие - " + blue.getScore() + " красные - " + red.getScore());

		printGameBoard();
		System.out.println("“екущий счет : " + "синие - " + blue.getScore() + " красные - " + red.getScore());
		int ch = 'r';
		System.out.println("Ќачалась игра!");
		while (!isGameEnded()) {

			if (ch == 'r') {
				doMove(red, SIDE.RED);
				ch = 'b';
			}
			if (ch == 'b') {
				doMove(blue, SIDE.BLUE);
				ch = 'r';
			}

		}
		if (blue.getScore() == red.getScore()) {
			System.out.println("Ќичь€!");
		} else if (blue.getScore() > red.getScore()) {
			System.out.println("—иний победил!");
		} else if (red.getScore() > blue.getScore()) {
			System.out.println(" расный победил!");
		}
	}

	private void printGameBoard() {
		for (int i = 0; i < gameBoard.length; i++) {
			for (int j = 0; j < gameBoard[i].length; j++) {
				System.out.print(gameBoard[i][j] + " ");
			}
			System.out.println("");
		}
	}

	private boolean hasEmptyCell() {

		for (int i = 0; i < gameBoard.length; i++) {
			for (int j = 0; j < gameBoard[i].length; j++) {
				if (gameBoard[i][j] == 0) {
					return true;
				}
			}
		}

		return false;
	}

	enum SIDE {
		RED, BLUE, SPECTATOR
	}

	enum DIFFICULTY {
		EASY, MIDDLE, HARD, NONE
	}

}