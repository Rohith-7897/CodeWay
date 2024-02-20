import java.util.InputMismatchException;
import java.util.Scanner;

public class StudentGradeCalculator {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		// Input
		System.out.println("Enter marks obtained (out of 100) in each subject:");
		int subject1 = getValidMarks(scanner, "Subject 1");
		int subject2 = getValidMarks(scanner, "Subject 2");
		int subject3 = getValidMarks(scanner, "Subject 3");

		// Calculation
		int totalMarks = subject1 + subject2 + subject3;
		double averagePercentage = totalMarks / 3.0;
		char grade = calculateGrade(averagePercentage);

		// Display Results
		System.out.println("\nTotal Marks: " + totalMarks);
		System.out.println("Average Percentage: " + averagePercentage);
		System.out.println("Grade: " + grade);

		scanner.close();
	}

	// Validate marks input
	private static int getValidMarks(Scanner scanner, String subjectName) {
		int marks = 0;
		boolean validInput = false;
		while (!validInput) {
			try {
				System.out.print(subjectName + ": ");
				marks = scanner.nextInt();
				if (marks < 0 || marks > 100) {
					System.out.println("Marks should be between 0 and 100. Please re-enter.");
				} else {
					validInput = true;
				}
			} catch (InputMismatchException e) {
				System.out.println("Invalid input. Please enter an integer value.");
				scanner.next(); // Clear the invalid input from the scanner buffer
			}
		}
		return marks;
	}

	// Calculate grade based on average percentage
	private static char calculateGrade(double averagePercentage) {
		if (averagePercentage >= 90) {
			return 'A';
		} else if (averagePercentage >= 80) {
			return 'B';
		} else if (averagePercentage >= 70) {
			return 'C';
		} else if (averagePercentage >= 60) {
			return 'D';
		} else if (averagePercentage >= 50) {
			return 'E';
		}else {
			return 'F';
		}
	}
}
