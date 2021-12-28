
package my.nalab5;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import my.nalab5.Game.DIFFICULTY;
import my.nalab5.Game.SIDE;

public class Main {

	private static Scanner scanner = new Scanner(System.in);

	public static void main(String[] args) {

		Game game = new Game();
		game.setSide(chooseTeam());
		game.setDifficulty(chooseDifficulty());

		game.startGame();

	}

	private static Game.SIDE chooseTeam() {
		Game.SIDE side;

		String strSide = "";

		while (!(strSide.equals("r") || strSide.equals("b"))) {
			System.out.println("Выберите сторону : r|b");
			strSide = scanner.nextLine();
		}

		if (strSide.equals("r")) {
			System.out.println("Вы выбрали сторону красных");

			side = SIDE.RED;
		} else if (strSide.equals("b")) {
			System.out.println("Вы выбрали сторону синих");
			side = SIDE.BLUE;
		} else {
			side = SIDE.SPECTATOR;
		}
		return side;
	}

	private static Game.DIFFICULTY chooseDifficulty() {
		Game.DIFFICULTY difficulty;

		String strDif = "";

		while (!(strDif.equals("e") || strDif.equals("m") || strDif.equals("h"))) {
			System.out.println("Выберите сложность : e|m|h");
			strDif = scanner.nextLine();
		}
		if (strDif.equals("e")) {
			System.out.println("Вы выбрали сложность : легкая");
			difficulty = DIFFICULTY.EASY;
		} else if (strDif.equals("m")) {
			System.out.println("Вы выбрали сложность : средняя");
			difficulty = DIFFICULTY.MIDDLE;
		} else if (strDif.equals("h")) {
			System.out.println("Вы выбрали сложность : тяжелая");
			difficulty = DIFFICULTY.HARD;
		} else {
			difficulty = DIFFICULTY.NONE;
		}
		return difficulty;

	}

	public static int[] tryMove() {
		int[] coords = new int[2];
		String str = "";

		while (!validStrCoords(str)) {
			System.out.println("Введите координаты в формате {x,y}");
			str = scanner.nextLine();
		}
		String[] parts = str.split(",");
		coords[0] = Integer.parseInt(parts[0]);
		coords[1] = Integer.parseInt(parts[1]);

		return coords;
	}

	private static boolean validStrCoords(String str) {
		String regex = "\\d+,\\d+";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(str);
		return matcher.find();
	}

}
