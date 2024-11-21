import java.util.Scanner;

public class Program {
	public static void countCharOccurences(char[] inputCharArr, int[] charsCountArr) {
		for (int i = 0; i < inputCharArr.length; i++) {
			charsCountArr[inputCharArr[i]]++;
			if (charsCountArr[inputCharArr[i]] > 999) {
				System.err.println("IllegalArgument");
				System.exit(-1);
			}
		}
	}

	public static void bubbleSort(int[] charsCountArr, int[] indexArr) {
		int temp;
		boolean swapped;
		for (int i = 0; i < charsCountArr.length - 1; i++) {
			swapped = false;
			for (int j = 0; j < charsCountArr.length - i - 1; j++) {
				if (charsCountArr[j] < charsCountArr[j + 1]) {
					temp = charsCountArr[j];
					charsCountArr[j] = charsCountArr[j + 1];
					charsCountArr[j + 1] = temp;

					temp = indexArr[j];
					indexArr[j] = indexArr[j + 1];
					indexArr[j + 1] = temp;

					swapped = true;
				}
			}

			if (swapped == false)
				break;
		}
	}

	public static int[] storeGraph(int[] arr, float scaleFactor) {
		int symbolNumber = (int)(arr[0] / scaleFactor);
		int spaces = 10 - symbolNumber;
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
		System.out.print("-> ");
		final String input = scanner.nextLine();
		System.out.println();
		scanner.close();

		final char[] inputCharArr = input.toCharArray();
		final int[] charsCountArr = new int[65535];
		final int[] indexArr = new int[65535];
		for (int i = 0; i < 65535; i++) {
			indexArr[i] = i;
		}

		// Count each character occurrences
		countCharOccurences(inputCharArr, charsCountArr);

		// Sort the charsCountArr in descending order
		bubbleSort(charsCountArr, indexArr);

		// Calculate the scaling scaleFactor
		int maxOccurenceIndex = 0;
		int[] currentMaxOccurence = {charsCountArr[maxOccurenceIndex], indexArr[maxOccurenceIndex]};
		maxOccurenceIndex++;
		float scaleFactor = (float)currentMaxOccurence[0] / 10;

		// Store the graph
		int[][] graph = new int[10][12];
		int lastColumn = 10;
		for (int i = 0; i < 10; i++) {
			int[] row = storeGraph(currentMaxOccurence, scaleFactor);
			for (int j = 0; j < row.length; j++)
				graph[i][j] = row[j];
			currentMaxOccurence = new int[]{charsCountArr[maxOccurenceIndex], indexArr[maxOccurenceIndex]};
			maxOccurenceIndex++;
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
