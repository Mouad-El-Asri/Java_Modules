public class Program {
	public static void main(String[] args) {
		User sender = new User(1, "Mouad", 197);
		User recipient = new User(2, "Amine", 52);

		Transaction transaction = new Transaction(sender, recipient, Transaction.TransferCategory.CREDIT, 100);

		System.out.println(sender);
		System.out.println("----------");
		System.out.println(recipient);
		System.out.println("----------");
		System.out.println(transaction);
	}
}
