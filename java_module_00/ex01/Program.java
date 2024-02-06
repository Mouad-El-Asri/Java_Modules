import java.util.Scanner;

public class Program {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int number = scanner.nextInt();
		if (number <= 0 || number == 1) {
			System.err.println("IllegalArgument");
			System.exit(-1);
		}
		int steps = 0;
		for (int i = 2; i <= number / i; i++) {
			steps++;
			if (number % i == 0) {
				System.out.println(false + " " + steps);
				System.exit(0);
			}
		}
		System.out.println(true + " " + steps);
		scanner.close();
	}
}
