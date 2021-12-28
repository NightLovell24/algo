package my.nalab1;



public class Board {

	public static final int CELLS = 8;
	int[] values = new int[8];
	public int countOfQueens;
	public int collision = 0;

	public Board() {
		for (int i = 0; i < CELLS; i++) {
			values[i] = -1;
		}
		countOfQueens = 0;

	}

	public boolean isSafe(int cell) {
		for (int i = 0; i < countOfQueens; i++) {

			if ((values[i] == cell))
				return false;
		} // 1 - столбы и рядочки, 2 - диагональки

		int tempX = countOfQueens;
		int tempY = cell;

		while (tempX > 0 && tempY > 0 && tempY < CELLS) {
			tempX -= 1;
			tempY -= 1;
			if (values[tempX] == tempY) {
				return false;
			}
		}
		tempX = countOfQueens;
		tempY = cell;

		while (tempX > 0 && tempY > 0 && tempY < CELLS) {
			tempX -= 1;
			tempY += +1;
			if (values[tempX] == tempY) {
				return false;
			}
		}

//		System.out.println("Текущие..");
		for (int i = 0; i < countOfQueens; i++) {
//			System.out.println(i + ";" + values[i]);
		}
//		System.out.println("-----------------");
//		System.out.println("Входной : " + (countOfQueens) + ";" + cell);

		return true;
	}

	public boolean isCorrect() {
		if (countOfQueens == CELLS) {
			return true;
		} else
			return false;
	}

	public void add(int cell) {
		if (cell >= 0 && cell < CELLS) {
			values[countOfQueens] = cell;
			countOfQueens++;
		} else {
			System.out.println("Неправильный диапазон");
		}
	}

	public void remove() {
		if (countOfQueens > 0) {
			countOfQueens--;
		}
	}

	Board getCopy() {
		Board newBoard = new Board();

		newBoard.collision = collision;
		newBoard.countOfQueens = countOfQueens;
		int[] newValues = new int[8];

		for (int i = 0; i < values.length; i++) {
			newValues[i] = values[i];
		}
		newBoard.values = newValues;
		return newBoard;
	}

	
	public void printTable() {
		System.out.println("##########");

		for (int i = 0; i < CELLS; i++) {
			System.out.print("#");
			for (int j = 0; j < CELLS; j++) {
				if (j != values[i]) {
					System.out.print(" ");
				} else {
					System.out.print("Q");
				}
			}
			System.out.println("#");
		}

		System.out.println("##########");
	}

	

	

	

}
