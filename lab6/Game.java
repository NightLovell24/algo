package my.nalab6;

import java.util.HashMap;
import java.util.Map;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;

public class Game {

	public static final int MAX_OF_ROUNDS = 10;

	public static void main(String[] args) {
		new Game().startGame();
	}

	@FXML
	Label FirstP;
	@FXML
	Label SecondP;
	@FXML
	Label ThirthP;
	@FXML
	Label FourthP;
	@FXML
	Label FifthP;

	@FXML
	Label value_p;

	@FXML
	Label value_b;

	@FXML
	Label value_r;

	@FXML
	Button move;

	@FXML
	Button unmove;

	@FXML
	CheckBox one;

	@FXML
	CheckBox two;

	@FXML
	CheckBox three;

	@FXML
	CheckBox four;

	@FXML
	CheckBox five;

	int AI_score;
	int player_score;

	boolean flag = false;
	boolean willMove = false;
	boolean isGameBotEnded = false;
	boolean isGamePlayerEnded = false;
	boolean isGameStarted = false;
	PlayerState botState = new PlayerState("AI");
	PlayerState playerState = new PlayerState("Player");
	int round = 1;
	int playerAttemp = 1;
	int[] points = new int[5];
	boolean playerMoved = false;
	boolean botMoved = false;

	private void flagsFireOff() {
		if (one.isSelected()) {
			one.fire();
		}
		if (two.isSelected()) {
			two.fire();
		}

		if (three.isSelected()) {
			three.fire();
		}
		if (four.isSelected()) {
			four.fire();
		}
		if (five.isSelected()) {
			five.fire();
		}

	}

	private void endGame() {
		AI_score = 0;
		player_score = 0;
		isGameStarted = false;
		round = 1;
		flag = false;
		willMove = false;
		 isGameBotEnded = false;
		 isGamePlayerEnded = false;
		botState = new PlayerState("AI");
		playerState = new PlayerState("Player");
		points = new int[5];
		playerAttemp = 1;
		FirstP.setText("0");
		SecondP.setText("0");
		ThirthP.setText("0");
		FourthP.setText("0");
		FifthP.setText("0");

		value_b.setText("0");
		value_p.setText("0");
		value_r.setText("0");

		playerMoved = false;
		botMoved = false;

		flagsFireOff();
	}

	@FXML
	public void startGame() {

		if (!isGameStarted) {
			isGameStarted = true;
			AI_score = 0;
			player_score = 0;

			new Alert(Alert.AlertType.INFORMATION, "Началась игра").showAndWait();
			int rand = (int) (Math.random() * 2);
//			int rand = 0;
			if (rand == 0) {
				new Alert(Alert.AlertType.INFORMATION, "Первый ходит бот").showAndWait();
			}
			if (rand == 1) {
				new Alert(Alert.AlertType.INFORMATION, "Первый ходит игрок").showAndWait();
			}

			System.out.println("Раунд : " + round);
			System.out.println("------------------------------------------");

			if (rand == 0) {
				moveAI(botState);

			}
			if (rand == 1) {

			}

		} else {

			new Alert(Alert.AlertType.INFORMATION, "Игра уже запущена").showAndWait();
			flagsFireOff();
		}

//			

//			for (int round = 1; round <= MAX_OF_ROUNDS; round++) {
//				if (isGameBotEnded) {
//					System.out.println("Бот победил");
//					new Alert(Alert.AlertType.INFORMATION, "Бот победил").showAndWait();
//					return;
//				}
//				if (isGamePlayerEnded) {
//					System.out.println("Игрок победил");
//					new Alert(Alert.AlertType.INFORMATION, "Игрок победил").showAndWait();
//					return;
//				}
//
//				value_r.setText("" + round);
//
//				System.out.println("Раунд " + round);
//				System.out.println("------------------------------------------");
//				if (rand == 0) {
//					doMove(true, botState);
//					// WAIT FOR PLAYER PRESS BUTTON!!!
//					doMove(false, playerState);
//				}
//				if (rand == 1) {
//					doMove(false, botState);
//
//					doMove(true, playerState);
//				}
//
//				System.out.println("------------------------------------------");
//			}
//
//			if (AI_score > player_score) {
//				new Alert(Alert.AlertType.INFORMATION, "Бот победил").showAndWait();
//			} else if (player_score > AI_score) {
//				new Alert(Alert.AlertType.INFORMATION, "Игрок победил").showAndWait();
//			} else if (player_score == AI_score) {
//				new Alert(Alert.AlertType.INFORMATION, "Ничья").showAndWait();
//			}

	}

	@FXML
	private void doMove() {
		if (isGameStarted) {

			if (playerAttemp == 1) {
				movePlayer(playerState, null);

			} else {
				HashMap<Integer, Integer> map = new HashMap<>();
				if (!one.isSelected()) {
					map.put(1, points[0]);

				}
				if (!two.isSelected()) {
					map.put(2, points[1]);

				}
				if (!three.isSelected()) {
					map.put(3, points[2]);

				}
				if (!four.isSelected()) {
					map.put(4, points[3]);

				}
				if (!five.isSelected()) {
					map.put(5, points[4]);

				}

				movePlayer(playerState, map);

			}
			playerAttemp++;

			flagsFireOff();
			if (playerAttemp == 4) {

				rate(points, playerAttemp - 1, playerState, false);
				value_p.setText("" + player_score);
				value_b.setText("" + AI_score);
				playerAttemp = 1;
				moveAI(botState);
			}
		} else {
			new Alert(Alert.AlertType.INFORMATION, "Игра не началась!").showAndWait();
			flagsFireOff();
		}
	}

	@FXML
	private void stopMove() {
		if (isGameStarted) {
			if (playerAttemp == 1) {
				new Alert(Alert.AlertType.INFORMATION, "Вам необходимо кинуть кости хотя бы одинажды!").showAndWait();
				flagsFireOff();
			} else {

				// calculate

				rate(points, playerAttemp - 1, playerState, false);
				value_p.setText("" + player_score);
				value_b.setText("" + AI_score);

				playerAttemp = 1;
				moveAI(botState);
			}
		} else {
			new Alert(Alert.AlertType.INFORMATION, "Игра не началась!").showAndWait();
			flagsFireOff();
		}
	}

	public void movePlayer(PlayerState playerState, HashMap<Integer, Integer> map) {

		if (map == null) {
			points = throwPoints();
//			System.out.println("Вафля");
		} else {
			points = throwPoints(map);
		}
		playerMoved = true;
		System.out.println(
				"Игроку выпало : " + points[0] + "|" + points[1] + "|" + points[2] + "|" + points[3] + "|" + points[4]);

		FirstP.setText("" + points[0]);
		SecondP.setText("" + points[1]);
		ThirthP.setText("" + points[2]);
		FourthP.setText("" + points[3]);
		FifthP.setText("" + points[4]);

	}

	public void moveAI(PlayerState playerState) {

		if (isGameBotEnded || isGamePlayerEnded) {
			endGame();
			return;
		}
		if (isGameStarted == false) {
			endGame();
			return;
		}
		flagsFireOff();
		System.out.println("------------------------------------");
		botMoved = true;
		int points[] = throwPoints();
		int attemp = 1;
		System.out.println(
				"Боту выпало : " + points[0] + "|" + points[1] + "|" + points[2] + "|" + points[3] + "|" + points[4]);

		boolean isRated = checkAndRate(points);
		int counter = 0;
		while (!isRated) {
			if (counter == 2) {
				break;
			}
			points = otherActions(points, playerState);
			System.out.println("Боту выпало : " + points[0] + "|" + points[1] + "|" + points[2] + "|" + points[3] + "|"
					+ points[4]);
			isRated = checkAndRate(points);
			counter++;
			attemp++;
		}
		rate(points, attemp, playerState, true);
		value_b.setText("" + AI_score);
		value_p.setText("" + player_score);
//		System.out.println("Кол-во очков бота на раунд :" + AI_score);
		System.out.println("------------------------------------");

		FirstP.setText("0");
		SecondP.setText("0");
		ThirthP.setText("0");
		FourthP.setText("0");
		FifthP.setText("0");

		if (isGameBotEnded || isGamePlayerEnded) {
			endGame();
			return;
		}

	}

//	public void doMove(boolean ai, PlayerState playerState) {
//
//		if (ai) {
//
//		} else {
////			System.out.println("Человек пропустил ход..");
//
//			int points[] = throwPoints();
//			int attemp = 1;
//			System.out.println("Игроку выпало : " + points[0] + "|" + points[1] + "|" + points[2] + "|" + points[3]
//					+ "|" + points[4]);
//
//			FirstP.setText("" + points[0]);
//			SecondP.setText("" + points[1]);
//			ThirthP.setText("" + points[2]);
//			FourthP.setText("" + points[3]);
//			FifthP.setText("" + points[4]);
//
//			for (int i = 0; i < 2; i++) {
//
//				if (willMove) {
//					points = throwPoints();
//					attemp++;
//					System.out.println("Игроку выпало : " + points[0] + "|" + points[1] + "|" + points[2] + "|"
//							+ points[3] + "|" + points[4]);
//
//					FirstP.setText("" + points[0]);
//					SecondP.setText("" + points[1]);
//					ThirthP.setText("" + points[2]);
//					FourthP.setText("" + points[3]);
//					FifthP.setText("" + points[4]);
//
//					flag = false;
//				} else {
//					break;
//				}
//			}
//			rate(points, attemp, playerState, false);
//			value_p.setText("" + player_score);
//			System.out.println("Кол-во очков игрока на раунд :" + player_score);
//
//		}
//
//	}

	private void rate(int[] points, int attemp, PlayerState playerState, boolean ai) {
		if (isFour(points[0], points[1], points[2], points[3], points[4])) {
			if (!playerState.isFour) {
				if (attemp == 1) {
					if (ai) {
						AI_score += 45;
						System.out.println("У ботяры каре!");
					} else {
						player_score += 45;
						System.out.println("У игрока каре!");
					}
				} else {
					if (ai) {
						AI_score += 40;
						System.out.println("У ботяры каре!");
					} else {
						player_score += 40;
						System.out.println("У игрока каре!");
					}
				}
				playerState.isFour = true;
			} else {
				if (ai) {
					AI_score += points[2];
					System.out.println("Бот получил " + points[2]);
				} else {
					player_score += points[2];
					System.out.println("Игрок получил " + points[2]);
				}
			}
		} else if (isFullHouse(points[0], points[1], points[2], points[3], points[4])) {
			if (!playerState.isFullHouse) {
				if (attemp == 1) {
					if (ai) {
						AI_score += 35;
						System.out.println("У ботяры фулхаус!");
					} else {
						player_score += 35;
						System.out.println("У игрока фулхаус!");
					}
				} else {
					if (ai) {
						AI_score += 30;
						System.out.println("У ботяры фулхаус!");
					} else {
						player_score += 30;
						System.out.println("У игрока фулхаус!");
					}
				}
				playerState.isFullHouse = true;
			} else {
				if (ai) {
					AI_score += points[2];
					System.out.println("Бот получил " + points[2]);
				} else {
					player_score += points[2];
					System.out.println("Игрок получил " + points[2]);
				}
			}

		} else if (isStreet(points[0], points[1], points[2], points[3], points[4])) {
			if (!playerState.isStreet) {
				if (attemp == 1) {
					if (ai) {
						AI_score += 25;
						System.out.println("У ботяры стрит!");
					} else {
						player_score += 25;
						System.out.println("У игрока стрит!");
					}
				} else {
					if (ai) {
						AI_score += 20;
						System.out.println("У ботяры стрит!");
					} else {
						player_score += 20;
						System.out.println("У игрока стрит!");
					}
				}
				playerState.isStreet = true;
			} else {
				if (ai) {
					AI_score += points[2];
					System.out.println("Бот получил " + points[2]);
				} else {
					player_score += points[2];
					System.out.println("Игрок получил " + points[2]);
				}
			}
		} else if (isGeneral(points[0], points[1], points[2], points[3], points[4])) {
			if (!playerState.isGeneral) {
				if (attemp == 1) {
					if (ai) {
						isGameBotEnded = true;
						System.out.println("У ботяры генерал!");
						value_p.setText("" + player_score);
						value_b.setText("" + AI_score);
						new Alert(Alert.AlertType.INFORMATION, "Бот победил!").showAndWait();

					} else {
						isGamePlayerEnded = true;
						System.out.println("У игрока генерал!");
						value_p.setText("" + player_score);
						value_b.setText("" + AI_score);
						new Alert(Alert.AlertType.INFORMATION, "Вы победили!").showAndWait();

					}
				} else {
					if (ai) {
						AI_score += 60;
						System.out.println("У ботяры малый генерал!");
					} else {
						player_score += 60;
						System.out.println("У игрока малый генерал!");
					}
				}
				playerState.isGeneral = true;
			} else {
				if (ai) {
					AI_score += points[2];
					System.out.println("Бот получил " + points[2]);
				} else {
					player_score += points[2];
					System.out.println("Игрок получил " + points[2]);
				}
			}
		} else {
			if (ai) {
				AI_score += points[2];
				System.out.println("Бот получил " + points[2]);
			} else {
				player_score += points[2];
				System.out.println("Игрок получил " + points[2]);
			}
		}

		if (botMoved && playerMoved) {

			round++;
			if (round > MAX_OF_ROUNDS) {
				value_p.setText("" + player_score);
				value_b.setText("" + AI_score);

				new Alert(Alert.AlertType.INFORMATION, "Игра закончилась!").showAndWait();
				System.out.println("Игра закончилась!");
				if (player_score > AI_score) {
					new Alert(Alert.AlertType.INFORMATION, "Игрок победил!").showAndWait();
					System.out.println("Игрок победил!");
				} else if (AI_score > player_score) {
					new Alert(Alert.AlertType.INFORMATION, "Бот победил!").showAndWait();
					System.out.println("Бот победил!");
				} else if (AI_score == player_score) {
					new Alert(Alert.AlertType.INFORMATION, "Ничья!").showAndWait();
					System.out.println("Ничья!");
				}

				endGame();

			} else {

				System.out.println("Раунд : " + round);
				System.out.println("------------------------------------------");
				value_r.setText("" + round);
				botMoved = false;
				playerMoved = false;
			}
		}

	}

	private boolean checkAndRate(int[] points) {
		if (isFour(points[0], points[1], points[2], points[3], points[4])) {

			return true;

		} else if (isFullHouse(points[0], points[1], points[2], points[3], points[4])) {

			return true;

		} else if (isStreet(points[0], points[1], points[2], points[3], points[4])) {

			return true;

		} else if (isGeneral(points[0], points[1], points[2], points[3], points[4])) {

			return true;

		} else {
			if (points[2] == 6 || points[2] == 5) {
				return true;
			} else {
				return false;
			}

		}
	}

	private int[] otherActions(int[] points, PlayerState playerState) {
		if (isAIGeneral(points[0], points[1], points[2], points[3], points[4], playerState)
				|| isAIFullHouse(points[0], points[1], points[2], points[3], points[4], playerState)) {
			int number = getNumberAIGeneral(points);
			HashMap<Integer, Integer> map = new HashMap<>();
			for (int i = 0; i < 5; i++) {
				if (points[i] == number) {
					map.put(i + 1, points[i]);
				}
			}
			int[] newPoints = throwPoints(map);

			return newPoints;

		}

		else if (isAIFour(points[0], points[1], points[2], points[3], points[4], playerState)) {

			int number = getNumberAIFour(points);
			HashMap<Integer, Integer> map = new HashMap<>();
			for (int i = 0; i < 5; i++) {
				if (points[i] == number) {
					map.put(i + 1, points[i]);
				}
			}
			int[] newPoints = throwPoints(map);
			return newPoints;

		} else if (isAIStreet(points[0], points[1], points[2], points[3], points[4], playerState)) {

			HashMap<Integer, Integer> map = new HashMap<>();
			map.put(1, points[1]);
			map.put(2, points[2]);
			map.put(3, points[3]);

			int[] newPoints = throwPoints(map);
			return newPoints;

		} else {

			int[] newPoints = throwPoints();
			return newPoints;
		}
	}

	public int[] throwPoints() {
		int point1 = ((int) (Math.random() * 6)) + 1;
		int point2 = ((int) (Math.random() * 6)) + 1;
		int point3 = ((int) (Math.random() * 6)) + 1;
		int point4 = ((int) (Math.random() * 6)) + 1;
		int point5 = ((int) (Math.random() * 6)) + 1;

//		int point1 = 2;
//		int point2 = 2;
//		int point3 = 3;
//		int point4 = 3;
//		int point5 = 3;

//		System.out.println(point1 + "|" + point2 + "|" + point3 + "|" + point4 + "|" + point5 + "|");

//		if (isGeneral(point1, point2, point3, point4, point5)) {
//			if (attemp == 0) {
//				// PLAYER IS WIN
//			} else {
//				return 60;
//			}
//		}
//		if (isFour(point1, point2, point3, point4, point5)) {
//			if (attemp == 0) {
//				return 45;
//			} else {
//				return 40;
//			}
//		}
//		if (isFullHouse(point1, point2, point3, point4, point5)) {
//			if (attemp == 0) {
//				return 35;
//			} else {
//				return 30;
//			}
//		}
//		if (isStreet(point1, point2, point3, point4, point5)) {
//			if (attemp == 0) {
//				return 25;
//			} else {
//				return 20;
//			}
//		}
		int[] array = { point1, point2, point3, point4, point5 };
		return array;
	}

	public int[] throwPoints(HashMap<Integer, Integer> numbers) {

		int point1 = ((int) (Math.random() * 6)) + 1;
		int point2 = ((int) (Math.random() * 6)) + 1;
		int point3 = ((int) (Math.random() * 6)) + 1;
		int point4 = ((int) (Math.random() * 6)) + 1;
		int point5 = ((int) (Math.random() * 6)) + 1;

		for (Map.Entry<Integer, Integer> enty : numbers.entrySet()) {

			if (enty.getKey() == 1) {
				point1 = enty.getValue();
			}
			if (enty.getKey() == 2) {
				point2 = enty.getValue();
			}
			if (enty.getKey() == 3) {
				point3 = enty.getValue();
			}
			if (enty.getKey() == 4) {
				point4 = enty.getValue();
			}
			if (enty.getKey() == 5) {
				point5 = enty.getValue();
			}

		}

//		int point1 = 2;
//		int point2 = 2;
//		int point3 = 3;
//		int point4 = 3;
//		int point5 = 3;

//		System.out.println(point1 + "|" + point2 + "|" + point3 + "|" + point4 + "|" + point5 + "|");

//		if (isGeneral(point1, point2, point3, point4, point5)) {
//			if (attemp == 0) {
//				// PLAYER IS WIN
//			} else {
//				return 60;
//			}
//		}
//		if (isFour(point1, point2, point3, point4, point5)) {
//			if (attemp == 0) {
//				return 45;
//			} else {
//				return 40;
//			}
//		}
//		if (isFullHouse(point1, point2, point3, point4, point5)) {
//			if (attemp == 0) {
//				return 35;
//			} else {
//				return 30;
//			}
//		}
//		if (isStreet(point1, point2, point3, point4, point5)) {
//			if (attemp == 0) {
//				return 25;
//			} else {
//				return 20;
//			}
//		}
		int[] array = { point1, point2, point3, point4, point5 };
		return array;

	}

	public boolean isGeneral(int point1, int point2, int point3, int point4, int point5) {
		if (point1 == point2 && point2 == point3 && point3 == point4 && point4 == point5)

		{
			return true;
		} else
			return false;
	}

	public boolean isFour(int point1, int point2, int point3, int point4, int point5) {

		if (point1 == point2 && point2 == point3 && point3 == point4 && point4 == point5) {

			return false;
		}

		int same = 0;
		int[] points = { point1, point2, point3, point4, point5 };

		for (int i = 0; i < 5; i++) {
			same = 0;

			for (int j = 0; j < 5; j++) {
				if (i == j) {
					continue;
				}
				if (points[i] == points[j]) {
					same++;
				}
			}
			if (same == 3) {
				return true;
			}
		}
		return false;

	}

	public boolean isFullHouse(int point1, int point2, int point3, int point4, int point5) {

		boolean isFull = false;
		int full1 = -1;
		int full2 = -1;
		boolean isHouse = false;
		int same = 0;
		int[] points = { point1, point2, point3, point4, point5 };

		for (int i = 0; i < 5; i++) {
			same = 0;
			int temp1 = -1;
			int temp2 = -1;
			for (int j = 0; j < 5; j++) {

				if (i == j) {
					continue;
				}
				if (points[i] == points[j]) {
					temp1 = points[i];
					temp2 = points[j];
					same++;
				}
			}
			if (same == 1) {
				isFull = true;
				full1 = temp1;
				full2 = temp2;
				break;
			}
		}
		for (int i = 0; i < 5; i++) {
			same = 0;

			if (points[i] == full1 || points[i] == full2) {
				continue;
			}
			for (int j = 0; j < 5; j++) {

				if (i == j) {
					continue;
				}
				if (points[j] == full1 || points[j] == full2) {
					continue;
				}
				if (points[i] == points[j]) {

					same++;
				}
			}
			if (same == 2) {
				isHouse = true;

				break;
			}
		}
		if (isFull && isHouse) {
			return true;
		}
		return false;
	}

	public boolean isStreet(int point1, int point2, int point3, int point4, int point5) {
		int[] points = { point1, point2, point3, point4, point5 };
		int lastPoint = point1;
		for (int i = 1; i < 5; i++) {
			if (!(points[i] - 1 == lastPoint)) {
				return false;
			}
			lastPoint = points[i];
		}

		return true;
	}

	public boolean isAIGeneral(int point1, int point2, int point3, int point4, int point5, PlayerState playerState) { // 3
																														// одинаковых

		if (playerState.isGeneral) {
			return false;
		}

		int[] points = { point1, point2, point3, point4, point5 };

		int same = 0;
		for (int i = 0; i < 5; i++) {
			same = 0;

			for (int j = 0; j < 5; j++) {
				if (i == j) {
					continue;
				}
				if (points[i] == points[j]) {
					same++;
				}
			}
			if (same == 2) {
				return true;
			}
		}

		return false;
	}

	public boolean isAIFour(int point1, int point2, int point3, int point4, int point5, PlayerState playerState) { // 2
																													// одинаковых

		if (playerState.isFour) {
			return false;
		}

		int[] points = { point1, point2, point3, point4, point5 };

		int same = 0;
		for (int i = 0; i < 5; i++) {
			same = 0;

			for (int j = 0; j < 5; j++) {
				if (i == j) {
					continue;
				}
				if (points[i] == points[j]) {
					same++;
				}
			}
			if (same == 1) {
				return true;
			}
		}

		return false;
	}

	public boolean isAIFullHouse(int point1, int point2, int point3, int point4, int point5, PlayerState playerState) { // 3
																														// одинаковых

		if (playerState.isFullHouse) {
			return false;
		}

		int[] points = { point1, point2, point3, point4, point5 };

		int same = 0;
		for (int i = 0; i < 5; i++) {
			same = 0;

			for (int j = 0; j < 5; j++) {
				if (i == j) {
					continue;
				}
				if (points[i] == points[j]) {
					same++;
				}
			}
			if (same == 2) {
				return true;
			}
		}

		return false;
	}

	public boolean isAIStreet(int point1, int point2, int point3, int point4, int point5, PlayerState playerState) { // 1
																														// и
																														// 5
																														// в
																														// соответствии
		if (playerState.isStreet) {
			return false;
		} else {
			return point5 - 4 == point1;
		}
	}

	public int getNumberAIGeneral(int[] points) {
		int number = 0;
		int same = 0;
		for (int i = 0; i < 5; i++) {
			same = 0;
			int tempNumber = 0;
			for (int j = 0; j < 5; j++) {
				if (i == j) {
					continue;
				}
				if (points[i] == points[j]) {
					tempNumber = points[i];
					same++;
				}
			}
			if (same == 2) {
				number = tempNumber;
			}
		}

		return number;
	}

	public int getNumberAIFour(int[] points) {
		int number = 0;
		int same = 0;
		for (int i = 0; i < 5; i++) {
			same = 0;
			int tempNumber = 0;
			for (int j = 0; j < 5; j++) {
				if (i == j) {
					continue;
				}
				if (points[i] == points[j]) {
					tempNumber = points[i];
					same++;
				}
			}
			if (same == 1) {
				number = tempNumber;
			}
		}
		return number;
	}

}
