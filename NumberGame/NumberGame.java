package Week1;

import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

public class NumberGame {
	static Scanner sc = new Scanner(System.in);

	public static void main(String[] args) {
		displayMenu();
		int num = getInput("Enter your option:  ");
		System.out.println("---------------------------------------------------------");
		if (num == 1) {
			int randomNum = generateRandomNum();
			int userNum = getInput("Enter your answer:   ");
			if (randomNum == userNum) {
				printWinner();
				System.out.println("********** YOU HAVE SCORED 100 POINTS ***********");
				endGame();
			} else {
				printWrong();
				endGame();
			}
		} else if (num == 2) {
			int count = 5;
			int randomNumber = generateRandomNum();
			for (int i = 0; i < 5; i++) {
				int points = 100;
				int updatedPoints = points-(20*i);
				System.out.println("---------------------------------------------------------");
				System.out.println("*********** YOU HAVE " + count + " ATTEMPTS LEFT ***********");
				System.out.println("---------------------------------------------------------");
				int userNum = getInput("Enter the Number:    ");
				if (randomNumber == userNum) {
					printWinner();
					System.out.println("************** YOU HAVE SCORED "+updatedPoints+" POINTS **************");
					endGame();
				} else {
					count--;
					printWrong();
					System.out.println("---------------------------------------------------------");
					System.out.println(feedbackNumber(randomNumber, userNum, count));
					if (count == 0) {
						endGame();
					}
				}
			}
		} else {
			System.err.println("INVALID");
		}
		sc.close();

	}

	public static void printWinner() {
		System.out.println("---------------------------------------------------------");
		System.out.println("***************** YOUR GUESS WAS RIGHT!!!!!*************");
		System.out.println("---------------------------------------------------------");
		System.out.println("************************ WINNER!!!!*********************");
		System.out.println("---------------------------------------------------------");
	}

	public static void endGame() {
		System.out.println("---------------------------------------------------------");
		System.out.println("********************* GAME ENDED!!! *********************");
		System.out.println("--------------------------------------------------------");
		System.exit(0);
	}

	public static void printWrong() {
		System.out.println("---------------------------------------------------------");
		System.out.println("********** NUMBER YOU HAVE GUESSED IS WRONG!!! **********");
	}

	public static int getInput(String str) {
		System.out.println(str);
		int num = sc.nextInt();
		return num;
	}

	public static int generateRandomNum() {
		return ThreadLocalRandom.current().nextInt(1, 100);
	}

	public static String feedbackNumber(int randomNum, int userNum, int count) {
		if (count != 0) {
			if (userNum > 100 || userNum <= 0) {
				return "************* Enter Number between 1 - 100 *************";
			} else if (userNum > randomNum) {
				return "You have entered big number ";
			} else if (userNum < randomNum) {
				return "You have entered small number";
			}
		}
		return null;
	}

	public static void displayMenu() {
		System.out.println("----------------------------------------------------------");
		System.out.println("***************** WELCOME TO NUMBER GAME *****************");
		System.out.println("----------------------------------------------------------");
		System.out.println("This is the number generating game in this game your system \n"
				+ "generates a random number and you have to guess it, the range \n" + "of number will be (1-100) \n");
		System.out.println("----------------------------------------------------------");
		System.out.println("1. Play for Single time. \n2. Play for Multiple times.");
		System.out.println("----------------------------------------------------------");
	}

}
