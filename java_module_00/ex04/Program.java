import java.util.Scanner;

public class Program {
	public static void checkEof(char[] inputCharArr) {
		if (!(inputCharArr.length >= 2
			&& inputCharArr[inputCharArr.length - 2] == '4'
			&& inputCharArr[inputCharArr.length - 1] == '2')) {
				System.err.println("IllegalArgument");
				System.exit(-1);
		}
	}

	public static void countCharOccurences(char[] inputCharArr, int[] charsCountArr) {
		for (int i = 0; i < inputCharArr.length - 2; i++) {
			if ((inputCharArr[i] < 65 || inputCharArr[i] > 90)
				&& (inputCharArr[i] < 97 || inputCharArr[i] > 122)) {
					System.err.println("IllegalArgument");
					System.exit(-1);			}
			charsCountArr[inputCharArr[i]]++;

			if (charsCountArr[inputCharArr[i]] > 999) {
				System.err.println("IllegalArgument");
				System.exit(-1);
			}
		}
	}

	public static int[] getCurrentMaxOccurence(int[] charsCountArr) {
		int maxOccurence = 0;
		int maxOccurenceIndex = 0;
		for (int i = 0; i < charsCountArr.length; i++) {
			if (charsCountArr[i] > maxOccurence) {
				maxOccurence = charsCountArr[i];
				maxOccurenceIndex = i;
			}
		}
		charsCountArr[maxOccurenceIndex] = 0;
		return new int[] {maxOccurence, maxOccurenceIndex};
	}

	public static int[] storeGraph(int[] arr, float scaleFactor) {
		int symbloNumber = (int)(arr[0] / scaleFactor);
		int spaces = 10 - symbloNumber;
		int[] charArr = new int[12];
		for (int i = 0; i < 12; i++) {
			if (i == spaces)
				charArr[i] = arr[0];
			else if (i == 11)
				charArr[i] = arr[1];
			else if (i < spaces)
				charArr[i] = ' ';
			else
				charArr[i] = '#';
		}
		return charArr;
	}

	public static void main(String[] args) {
		final Scanner scanner = new Scanner(System.in);
		final String input = scanner.nextLine();
		System.out.println();
		scanner.close();

		final char[] inputCharArr = input.toCharArray();
		final int[] charsCountArr = new int[127];

		// Check that end of input is equal to 42
		checkEof(inputCharArr);

		// Count each character occurences
		countCharOccurences(inputCharArr, charsCountArr);

		// Calculate the scaling scaleFactor
		int[] currentMaxOccurence = getCurrentMaxOccurence(charsCountArr);
		float scaleFactor = (float)currentMaxOccurence[0] / 10;

		// Store the graph
		int[][] graph = new int[10][12];
		int lastColumn = 10;
		for (int i = 0; i < 10; i++) {
			int[] row = storeGraph(currentMaxOccurence, scaleFactor);
			for (int j = 0; j < row.length; j++)
				graph[i][j] = row[j];
			currentMaxOccurence = getCurrentMaxOccurence(charsCountArr);
			if (currentMaxOccurence[0] == 0) {
				lastColumn = i + 1;
				break ;
			}
		}

		// Display the graph
		for (int j = 0; j < 12; j++) {
			for (int i = 0; i < lastColumn; i++) {
				if (j != 11 && graph[i][j] != ' ' && (j == 0 || graph[i][j - 1] == ' ')) {
					System.out.print(graph[i][j]);
					if (graph[i][j] >= 10)
						System.out.print(' ');
					else
						System.out.print("  ");
				} else {
					System.out.print((char)graph[i][j]);
					System.out.print("  ");
				}
			}
			System.out.println();
		}
	}
}
