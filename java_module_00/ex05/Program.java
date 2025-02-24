import java.util.Scanner;

public class Program {
	public static final String[] workingDays = {"MO", "TU", "WE", "TH", "FR"};

	public enum AttendanceStatus {
		HERE,
		NOT_HERE;
	}

	public enum Error {
		EMPTY_NAME("Name input cannot be empty."),
		TOO_LONG("Name input must be max 10 characters."),
		CONTAINS_SPACE("Name input must not conatain spaces."),
		INVALID_CHARCTERS("Name input must contain only letters."),
		NOT_AN_INTEGER("Time input must be an integer."),
		INVALID_TIME("Time input must be between 1pm and 6pm");

		private final String errorMessage;

		Error(String errorMessage) {
			this.errorMessage = errorMessage;
		}
		
		@Override
		public String toString() {
			return this.errorMessage;
		}
	}

	public static boolean handleError(Error err) {
		System.err.println("Error: " + err);
		return false;
	}

	public static boolean isAlpha(char[] chars) {
		for (char c : chars) {
			if ((c < 65 || c > 90) &&
				(c < 97 || c > 122))
				return false;
		}
		return true;
	}

	public static boolean containsSpace(char[] chars) {
		for (char c : chars)
			if (c == ' ')
				return true;
		return false;
	}

	public static boolean parseName(String name) {
		char[] nameChars = name.toCharArray();
		if (nameChars.length == 0)
			return handleError(Error.EMPTY_NAME);
		if (nameChars.length > 10)
			return handleError(Error.TOO_LONG);
		if (containsSpace(nameChars))
			return handleError(Error.CONTAINS_SPACE);
		if (!isAlpha(nameChars))
			return handleError(Error.INVALID_CHARCTERS);
		return true;
	}

	public static boolean parseClass(String input) {
		int time;
		String day;
		final Scanner scanner = new Scanner(input);
		if (!scanner.hasNextInt()) {
			scanner.close();
			return handleError(Error.NOT_AN_INTEGER);
		}
		time = scanner.nextInt();
		if (time < 1 || time > 6) {
			scanner.close();
			return handleError(Error.INVALID_TIME);
		}
		day = scanner.nextLine();
		scanner.close();
		for (String workingDay : workingDays)
			if (day == workingDay)
				return true;
		return false;
	}

	public static void main(String[] args) {
		final Scanner scanner = new Scanner(System.in);
		String[][] schoolData = new String[3][10];
		int j;
		boolean isValidInput;
		for (int i = 0; i < 3; i++) {
			j = 0;
			System.out.print("-> ");
			String input = scanner.nextLine();
			while (!input.equals(".")) {
				switch (i) {
					case 0:
						isValidInput = parseName(input);
						break;
					case 1:
						isValidInput = parseClass(input);
						break;
					default:
						isValidInput = false;
						break;
				}
				if (isValidInput)
					schoolData[i][j++] = input;
				System.out.print("-> ");
				input = scanner.nextLine();
				isValidInput = false;
			}
		}
		scanner.close();
	}
}
