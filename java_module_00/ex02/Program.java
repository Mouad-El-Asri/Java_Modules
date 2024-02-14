import java.util.Scanner;

public class Program {
	public static int sumOfDigits(int number) {
		if (number <= 1) {
			System.err.println("Invalid input number! Try again!");
			return -1;
		}

		int sum = 0;
		while (number != 0) {
			sum += number % 10;
			number /= 10;
		}

		return sum;
	}

	public static boolean isPrime(int number) {
		for (int i = 2; i <= number / i; i++) {
			if (number % i == 0)
				return false;
		}
		return true;
	}

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		int coffeeRequestCount = 0;
		int sumOfDigits = 0;
		int query = 0;

		while (query != 42) {
			query = scanner.nextInt();
			scanner.nextLine();
			sumOfDigits = sumOfDigits(query);

			if (sumOfDigits == -1)
				continue ;
			else if (isPrime(sumOfDigits))
				coffeeRequestCount++;
		}

		scanner.close();
		System.out.println("Count of coffee-request : " + coffeeRequestCount);
	}
}
