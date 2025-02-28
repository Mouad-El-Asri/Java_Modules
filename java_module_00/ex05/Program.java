import java.util.Scanner;

public class Program {
	private static String[] students = new String[10];
	private static int[][] classesSchedule = new int[5][6];
	private static final String[] weekDays = {"MO", "TU", "WE", "TH", "FR"};
	private static final int[] classDays = {1, 2, 3, 4, 7, 8, 9, 10, 11, 14, 15, 16, 17, 18, 21, 22, 23, 24, 25, 28, 29, 30};

	private enum Error {
		EMPTY_NAME("Name input cannot be empty."),
		TOO_LONG("Name input must be max 10 characters."),
		CONTAINS_SPACE("Name input must not conatain spaces."),
		INVALID_CHARCTERS("Name input must contain only letters."),
		NOT_AN_INTEGER("Time input must be an integer."),
		INVALID_TIME("Time input must be between 1pm and 6pm."),
		INVALID_WORKING_DAY("Day must be a valid working day."),
		SYNTAX_ERROR("Invalid statement."),
		NAME_ALREADY_EXIST("Name already exists."),
		CLASS_ALREADY_EXIST("Class already exists."),
		CLASS_NOT_FOUND("Class not found."),
		DAY_NOT_FOUND("Day not found."),
		NAME_NOT_FOUND("Student name not found.");

		private final String errorMessage;

		Error(String errorMessage) {
			this.errorMessage = errorMessage;
		}
		
		@Override
		public String toString() {
			return this.errorMessage;
		}
	}

	public static int getDayIndex(int day) {
		for (int i = 0; i < classDays.length; i++)
			if (classDays[i] == day)
				return ((i + 1) % 5);
		return -1;
	}

	private static boolean contains(String[] array, String toFind) {
		for (String s : array)
			if (s != null && s.equals(toFind))
				return true;
		return false;
	}

	private static String trim(String s) {
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

	private static boolean handleError(Error err) {
		System.err.println("Error: " + err);
		return false;
	}

	private static boolean isAlpha(char[] chars) {
		for (char c : chars) {
			if ((c < 65 || c > 90) &&
				(c < 97 || c > 122))
				return false;
		}
		return true;
	}

	private static boolean containsSpace(char[] chars) {
		for (char c : chars)
			if (c == ' ')
				return true;
		return false;
	}

	private static boolean parseName(String name) {
		char[] nameChars = name.toCharArray();
		if (contains(students, name))
			return handleError(Error.NAME_ALREADY_EXIST);
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

	private static int getIndex(String[] array, String value) {
		for (int i = 0; i < array.length; i++)
			if (array[i].equals(value))
				return i;
		return -1;
	}

	private static boolean parseClass(String input) {
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
		day = trim(scanner.nextLine());
		scanner.close();
		for (String weekDay : weekDays) {
			if (day.equals(weekDay)) {
				if (classesSchedule[getIndex(weekDays, day)][time - 1] == 1)
					return handleError(Error.CLASS_ALREADY_EXIST);
				classesSchedule[getIndex(weekDays, day)][time - 1] = 1;
				return true;
			}
		}
		return handleError(Error.INVALID_WORKING_DAY);
	}

	private static boolean parseAttendanceRecording(String input) {
		// Split the statement
		input = trim(input);
		char[] stringArray = input.toCharArray();
		int i = 0;
		int j = 0;
		String[] inputWords = new String[4];
		while (i < stringArray.length) {
			if (j == 4)
				return handleError(Error.SYNTAX_ERROR);
			String word = "";
			while (i < stringArray.length && stringArray[i] != ' ')
				word += stringArray[i++];
			inputWords[j++] = word;
			i++;
		}
		if (j != 4)
			return handleError(Error.SYNTAX_ERROR);

		// Parse the statement
		if (!contains(students, inputWords[0]))
			return handleError(Error.NAME_NOT_FOUND);

		if (!inputWords[3].equals("HERE") &&
			!inputWords[3].equals("NOT_HERE"))
			return handleError(Error.SYNTAX_ERROR);


		final Scanner scanner1 = new Scanner(inputWords[1]);
		if (!scanner1.hasNextInt()) {
			scanner1.close();
			return handleError(Error.NOT_AN_INTEGER);
		}
		int classTime = scanner1.nextInt();
		scanner1.close();

		if (classTime < 1 || classTime > 6)
			return handleError(Error.INVALID_TIME);

		final Scanner scanner2 = new Scanner(inputWords[2]);
		if (!scanner2.hasNextInt()) {
			scanner2.close();
			return handleError(Error.NOT_AN_INTEGER);
		}
		int classDay = scanner2.nextInt();
		scanner2.close();

		int dayIndex = getDayIndex(classDay);
		if (dayIndex == -1)
			return handleError(Error.DAY_NOT_FOUND);
		if (classesSchedule[dayIndex][classTime - 1] != 1)
			return handleError(Error.CLASS_NOT_FOUND);
		return true;
	}

	public static void main(String[] args) {
		final Scanner scanner = new Scanner(System.in);
		int j;
		for (int i = 0; i < 3; i++) {
			j = 0;
			System.out.print("-> ");
			String input = scanner.nextLine();
			while (!input.equals(".")) {
				switch (i) {
					case 0:
						input = trim(input);
						if (parseName(input))
							students[j] = input;
						break;
					case 1:
						parseClass(input);
						break;
					default:
						parseAttendanceRecording(input);
				}
				if (++j == 10) {
					System.out.println("-> .");
					break;
				}
				System.out.print("-> ");
				input = scanner.nextLine();
			}
		}
		// for (int i = 0; i < 5; i++) {
		// 	for (int k = 0; k < 6; k++) {
		// 		if (classesSchedule[i][k] == 1)
		// 			System.out.println(k + 1 + ":00");
		// 			// System.out.print("|");
		// 	}
		// }
		for (int i = 0; i < classDays.length; i++) {
			for (int k = 0; k < 6; k++) {
				if (classesSchedule[(i + 1) % 5][k] == 1) {
					System.out.print("  ");
					System.out.print(k + 1 + ":00 ");
					System.out.print(weekDays[(i + 1) % 5] + " ");
					System.out.print(classDays[i]);
					System.out.print(" |");
				}
			}
		}
		System.out.println();
		scanner.close();
	}
}
