package my.nalab1;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

public class Algo {

	private static int steps = 0;
	private static int noWay = 0;
	private static int states = 0;
	@SuppressWarnings("unused")
	private static int statesInMemory = 0;

	public static Board RBFS(Board board) {

		if (board.collision > 0) {

			return null;
		}


		if (board.isCorrect() == true) {

			System.out.println("ÊÎÍÅ×ÍÛÉ ÐÅÇÓËÜÒÀÒ");
			System.out.println("---------------------------");
			System.out.println("Èòåðàöèé " + steps);
			System.out.println("Áåçûõîñäíîñòè " + noWay);
			System.out.println("Ñîñòîÿíèé  " + states);
			System.out.println(board.collision);

			return board;
		}

		PriorityQueue<Board> queue = getChildrens(board);



		for (Board boardy : queue) {

			steps++;

			boardy.printTable();
			System.out.println("------------------------");
			Board res = RBFS(boardy);
			if (res != null) {
				return res;
			}

			boardy.remove();
			noWay++;
		}

		return null;
	}

	private static Comparator<Board> collisionComparator = new Comparator<Board>() {

		@Override
		public int compare(Board o1, Board o2) {
			return +(o1.collision - o2.collision);
		}
	};

	private static PriorityQueue<Board> getChildrens(Board state) {
		PriorityQueue<Board> queue = new PriorityQueue<>(collisionComparator);
		List<Board> tempList = new ArrayList<>();

		for (int j = 0; j < Board.CELLS; j++) {
			steps++;
			Board tempBoard = state.getCopy();
			states++;

			tempBoard.add(j);
			tempBoard.collision = F1(tempBoard);
			tempList.add(tempBoard);


		}
		Collections.shuffle(tempList);

		for (Board board : tempList) {
			queue.add(board);
		}

		return queue;
	}



	public static int F1(Board board) {
		int currentCollision = 0;
		for (int i = 0; i < board.countOfQueens; i++) {
			int tempValue = board.values[i];
			for (int j = 0; j < board.countOfQueens; j++) {

				if (i == j) {
					continue;
				}

				if (board.values[j] == tempValue) {
					currentCollision++;
				}
			}

		}

		

		for (int i = 0; i < board.countOfQueens; i++) {
			int tempCell = board.values[i];
			int tempRow = i;

			while (tempRow > 0 && tempRow < (board.countOfQueens) && tempCell > 0 && (tempCell < Board.CELLS)) {

				tempRow++;
				tempCell++;

				if (tempRow == -1 || tempRow == 8 || tempCell == -1 || tempCell == 8) {
					break;
				}

				if (board.values[tempRow] == tempCell) {
					currentCollision++;
				}

			}
			tempCell = board.values[i];
			tempRow = i;
			while (tempRow > 0 && tempRow < (board.countOfQueens) && tempCell > 0 && (tempCell < Board.CELLS)) {

				tempRow++;
				tempCell--;

				if (tempRow == -1 || tempRow == 8 || tempCell == -1 || tempCell == 8) {
					break;
				}

				if (board.values[tempRow] == tempCell) {
					currentCollision++;
				}

				if (board.values[tempRow] == tempCell) {
					currentCollision++;
				}

			}
			tempCell = board.values[i];
			tempRow = i;
			while (tempRow > 0 && tempRow < (board.countOfQueens) && tempCell > 0 && (tempCell < Board.CELLS)) {

				tempRow--;
				tempCell--;

				if (tempRow == -1 || tempRow == 8 || tempCell == -1 || tempCell == 8) {
					break;
				}

				if (board.values[tempRow] == tempCell) {
					currentCollision++;
				}

				if (board.values[tempRow] == tempCell) {
					currentCollision++;
				}

			}
			tempCell = board.values[i];
			tempRow = i;
			while (tempRow > 0 && tempRow < (board.countOfQueens) && tempCell > 0 && (tempCell < Board.CELLS)) {

				tempRow--;
				tempCell++;

				if (tempRow == -1 || tempRow == 8 || tempCell == -1 || tempCell == 8) {
					break;
				}

				if (board.values[tempRow] == tempCell) {
					currentCollision++;
				}

				if (board.values[tempRow] == tempCell) {
					currentCollision++;
				}

			}
		}

		

		return currentCollision;

	}

	public static Board LDFS(Board board) {
		states++;
		if (board.isCorrect() == true) {
			System.out.println("ÊÎÍÅ×ÍÛÉ ÐÅÇÓËÜÒÀÒ");
			System.out.println("---------------------------");
			System.out.println("Èòåðàöèé " + steps);
			System.out.println("Áåçûõîñäíîñòè " + noWay);
			System.out.println("Ñîñòîÿíèé  " + states);

			return board;
		} else {
			board.printTable();
			for (int i = 0; i < Board.CELLS; i++) {
				steps++;
				if (board.isSafe(i)) {
					board.add(i);
					Board res = LDFS(board);
					if (res != null) {

						return res;
					}
					board.remove();
				}
			}

		}
		noWay++;
		return null;
	}

}
