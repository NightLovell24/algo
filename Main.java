package my.nalab1;

public class Main {

	public static void main(String[] args) {
		Board board = new Board();
		System.out.println("Õ¿◊¿À‹Õ€… –≈«”À‹“¿“");
		System.out.println("---------------------------");
		board.printTable();

		Board newBoard = Algo.RBFS(board);
//Board newBoard = Algo.LDFS(board);

		
		newBoard.printTable();
		
//		Board board = new Board();
//		board.add(0);
//		board.add(1);
//		board.printTable();
//		System.out.println(Algo.F1(board));

	}

}
