import java.util.Scanner;

public class Program {
	public static void main(String[] args) {
		final Scanner scanner = new Scanner(System.in);

		int number = scanner.nextInt();
		if (number <= 1) {
			System.err.println("IllegalArgument");
			System.exit(-1);
		}

		int steps = 1;
		for (int i = 2; i <= number / i; i++) {
			if (number % i == 0) {
				System.out.println(false + " " + steps);
				System.exit(0);
			}
			steps++;
		}

		scanner.close();
		System.out.println(true + " " + steps);
	}
}
