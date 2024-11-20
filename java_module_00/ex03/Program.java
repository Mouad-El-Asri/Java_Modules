import java.util.Scanner;

public class Program {
	private static int pow(int exponent) {
		int result = 1;
		while (exponent-- != 0)
			result *= 10;
		return result;
	}

	private static void displayGrade(int grade) {
		System.out.print(' ');
		for (int i = 0; i < grade; i++)
			System.out.print('=');
		System.out.println('>');
	}

	public static void main(String[] args) {
		final Scanner scanner = new Scanner(System.in);

		int minimalGrades = 0, weeksCount = 0, divideBy;
		String week;

		while (weeksCount < 18) {
			week = scanner.next();
			
			if (week.equals("42"))
				break;
			else if (!week.equals("Week") || scanner.nextInt() != weeksCount + 1) {
				System.err.println("IllegalArgument");
				System.exit(-1);
			}
			weeksCount++;

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
			scanner.nextLine();
			minimalGrades = minimalGrades * 10 + minGrade;
		}

		scanner.close();

		divideBy = Program.pow(weeksCount - 1);
		for (int i = 0; i < weeksCount; i++) {
			System.out.print("Week ");
			System.out.print(i + 1);
			Program.displayGrade(minimalGrades / divideBy);
			minimalGrades %= divideBy;
			divideBy /= 10;
		}
	}
}
