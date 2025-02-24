import java.util.Scanner;

public class Program {
	public static final String[] workingDays = {"MO", "TU", "WE", "TH", "FR"};

	public enum AttendanceStatus {
		HERE(1),
		NOT_HERE(-1);

		private final int isHere;

		AttendanceStatus(int isHere) {
			this.isHere = isHere;
		}

		@Override
		public String toString() {
			return this.isHere + "";
		}
	}

	public enum Error {
		EMPTY_NAME("Name input cannot be empty."),
		TOO_LONG("Name input must be max 10 characters."),
		CONTAINS_SPACE("Name input must not conatain spaces."),
		INVALID_CHARCTERS("Name input must contain only letters."),
		NOT_AN_INTEGER("Time input must be an integer."),
		INVALID_TIME("Time input must be between 1pm and 6pm."),
		INVALID_WORKING_DAY("Day must be a valid working day."),
		SYNTAX_ERROR("Attendance recording statement is invalid.");

		private final String errorMessage;

		Error(String errorMessage) {
			this.errorMessage = errorMessage;
		}
		
		@Override
		public String toString() {
			return this.errorMessage;
		}
	}

	public static String[] split(String s) {
		s = trim(s);
		char[] stringArray = s.toCharArray();
		int i = 0;
		int j = 0;
		String[] words = new String[4];
		while (i < stringArray.length) {
			if (j == 4) {
				handleError(Error.SYNTAX_ERROR);
				return new String[]{};
			}
			String word = "";
			while (i < stringArray.length && stringArray[i] != ' ')
				word += stringArray[i++];
			words[j++] = word;
			i++;
		}
		return words;
	}

	public static String trim(String s) {
		char[] stringArray = s.toCharArray();
		String trimmedString = "";

		int j = stringArray.length - 1;
		while (j > 0 && (stringArray[j] == 32 || (stringArray[j] >= 9 && stringArray[j] <= 13)))
			j--;
		int i = 0;
		while (j > 0 && (stringArray[i] == 32 || (stringArray[i] >= 9 && stringArray[i] <= 13)))
			i++;
		while (i <= j)
			trimmedString +=  stringArray[i++];
		return trimmedString;
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
		for (String workingDay : workingDays) {
			if (trim(day).equals(workingDay))
				return true;
		}
		return handleError(Error.INVALID_WORKING_DAY);
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
						isValidInput = true;
						String[] attendanceRecording = split(input);
						if (attendanceRecording.length == 0)
							isValidInput = false;
				}
				if (isValidInput) {
					schoolData[i][j++] = input;
					isValidInput = false;
					if (j == 10) {
						System.out.println("-> .");
						break;
					}
				}
				System.out.print("-> ");
				input = scanner.nextLine();
			}
		}
		scanner.close();
	}
}
