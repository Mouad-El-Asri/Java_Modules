public class Program {
	public static void validateNumber(int number) {
		if (number > 999999 || number < 100000) {
			System.out.print("The number is not a six-digit integer.");
			System.exit(1);
		}
	}

    public static void main(String[] args) {
        int number = 479598;
		int sum = 0;
		Program.validateNumber(number);
		while (number != 0) {
			sum += number % 10;
			number /= 10;
		}
		System.out.println(sum);
    }
}
