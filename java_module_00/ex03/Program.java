import java.util.Scanner;

public class Program {
	private static int minimalGrades = 0;

	private static int pow(int base, int exponent) {
		int result = 1;
		while (exponent-- != 0)
			result *= base;
		return result;
	}

	private static void storeGrade(int grade) {
		Program.minimalGrades = minimalGrades * 10 + grade;
	}

	private static void displayGrade(int grade) {
		for (int i = 0; i < grade; i++)
			System.out.print("=");
		System.out.println(">");
	}

	public static void main(String[] args) {
		final Scanner scanner = new Scanner(System.in);
		int weeksCount = 1;
		int divideBy;
		String week;
		while (weeksCount <= 18) {
			week = scanner.nextLine();
			if (week.equals("42"))
				break;

			if (!week.equals("Week " + weeksCount)) {
				System.out.println("IllegalArgument");
				System.exit(-1);
			}
			int minGrade = 9;
			for (int i = 0; i < 5; i++) {
				int grade = scanner.nextInt();
				if (grade < 1 || grade > 9) {
					System.err.println("Grade must be between 1 and 9");
					i--;
				} else if (grade < minGrade) {
					minGrade = grade;
				}
			}
			Program.storeGrade(minGrade);
			scanner.nextLine();
			weeksCount++;
		}
		scanner.close();
		divideBy = pow(10, weeksCount - 2);
		for (int i = 0; i < weeksCount - 1; i++) {
			Program.displayGrade(Program.minimalGrades / divideBy);
			Program.minimalGrades %= divideBy;
			divideBy /= 10;
		}
	}
}
