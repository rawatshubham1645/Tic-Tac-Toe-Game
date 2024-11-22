import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class TicTacToe {
	static List<Integer> playerPosition = new ArrayList<>();
	static List<Integer> cpuPosition = new ArrayList<>();

	public static void main(String[] args) {
		char[][] gameBoard = { { ' ', '|', ' ', '|', ' ' }, { '-', '+', '-', '+', '-' }, { ' ', '|', ' ', '|', ' ' },
				{ '-', '+', '-', '+', '-' }, { ' ', '|', ' ', '|', ' ' } };
		printGameBoard(gameBoard);
		Scanner scn = new Scanner(System.in);
		while (true) {
			System.out.println("Enter your placement (1-9): ");
			int PlayerPos = scn.nextInt();
			System.out.println(PlayerPos);
			while (playerPosition.contains(PlayerPos) || cpuPosition.contains(playerPosition)) {
				System.out.println("Position Already Taken! Enter a correct position");
				PlayerPos = scn.nextInt();
			}
			placePiece(gameBoard, PlayerPos, "player");
			String winner = checkWinner();
			if (winner.length() > 0) {
				System.out.println(winner);
				break;
			}

			Random rand = new Random();
			int cpuPos = rand.nextInt(9) + 1;
			while (playerPosition.contains(cpuPos) || cpuPosition.contains(cpuPos)) {
				cpuPos = rand.nextInt(9) + 1;
			}
			placePiece(gameBoard, cpuPos, "cpu");
			printGameBoard(gameBoard);
			winner = checkWinner();
			if (winner.length() > 0) {
				System.out.println(winner);
				break;
			}
			System.out.println(winner);
		}
	}

	public static void printGameBoard(char[][] gameBoard) {

		for (char[] row : gameBoard) {
			for (char c : row) {
				System.out.print(c);
			}
			System.out.println();
		}
	}

	public static void placePiece(char[][] gameBoard, int pos, String user) {
		char symbol = 'X';
		if (user.equals("player")) {
			symbol = 'X';
			playerPosition.add(pos);
		} else if (user.equals("cpu")) {
			symbol = '0';
			cpuPosition.add(pos);
		}

		switch (pos) {
		case 1:
			gameBoard[0][0] = symbol;
			break;
		case 2:
			gameBoard[0][2] = symbol;
			break;
		case 3:
			gameBoard[0][4] = symbol;
			break;
		case 4:
			gameBoard[2][0] = symbol;
			break;
		case 5:
			gameBoard[2][2] = symbol;
			break;
		case 6:
			gameBoard[2][4] = symbol;
			break;
		case 7:
			gameBoard[4][0] = symbol;
			break;
		case 8:
			gameBoard[4][2] = symbol;
			break;
		case 9:
			gameBoard[4][4] = symbol;
			break;
		default:
			symbol = ' ';
			break;

		}
	}

	public static String checkWinner() {

		// Check winner in all rows
		List topRow = Arrays.asList(1, 2, 3);
		System.out.println("hhhhß");
		List midRow = Arrays.asList(4, 5, 6);
		List botRow = Arrays.asList(7, 8, 9);

		// Check winner in all Columns

		List leftCol = Arrays.asList(1, 4, 7);
		List midCol = Arrays.asList(2, 5, 8);
		List rightCol = Arrays.asList(3, 6, 9);

		// Check winner in both Diagnols
		List leftDiagnol = Arrays.asList(1, 5, 9);
		List rightDiagnol = Arrays.asList(7, 5, 3);

		List<List> winning = new ArrayList<>();
		winning.add(topRow);
		winning.add(midRow);
		winning.add(botRow);
		winning.add(leftCol);
		winning.add(midCol);
		winning.add(rightCol);
		winning.add(leftDiagnol);
		winning.add(rightDiagnol);

		for (List l : winning) {
			if (playerPosition.containsAll(l))
				return "Player Wins.....!";
			else if (cpuPosition.containsAll(l))
				return "Cpu Wins.....!";
			else if (playerPosition.size() + cpuPosition.size() == 9)
				return "--Draw--";
		}
		return "";
	}
}
