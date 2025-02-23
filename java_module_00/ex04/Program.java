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

	public static void swap(int[] arr, int i, int j) {
		int temp = arr[i];
		arr[i] = arr[j];
		arr[j] = temp;
	}

	public static void bubbleSort(int[] charsCountArr, int[] indexArr) {
		boolean swapped;
		for (int i = 0; i < charsCountArr.length - 1; i++) {
			swapped = false;
			for (int j = 0; j < charsCountArr.length - i - 1; j++) {
				if (charsCountArr[j] < charsCountArr[j + 1]) {
					swap(charsCountArr, j, j + 1);
					swap(indexArr, j, j + 1);
					swapped = true;
				}
			}

			if (swapped == false)
				break;
		}
	}

	public static void storeGraphRow(int[] graphRow, int[] currentMaxOccurence, float scaleFactor) {
		int symbolNumber = (int)(currentMaxOccurence[0] / scaleFactor);
		int spaces = 10 - symbolNumber;
		for (int i = 0; i < 12; i++) {
			if (i == spaces)
				graphRow[i] = currentMaxOccurence[0];
			else if (i == 11)
				graphRow[i] = currentMaxOccurence[1];
			else if (i < spaces)
				graphRow[i] = ' ';
			else
				graphRow[i] = '#';
		}
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
			storeGraphRow(graph[i], currentMaxOccurence, scaleFactor);
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
				if (graph[i][j] != ' ' && (j == 0 || graph[i][j - 1] == ' ')) {
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
