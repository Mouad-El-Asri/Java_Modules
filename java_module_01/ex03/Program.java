
import java.util.Arrays;


public class Program {
	public static void main(String[] args) {
		User user_1 = new User("Amine", 4087);
		User user_2 = new User("Mouad", 0);
		User user_3 = new User("Anass", 7000);

		TransactionsLinkedList transactionsList = new TransactionsLinkedList();
		Transaction transaction_1 = new Transaction(user_1, user_2, Transaction.TransferCategory.CREDIT, 1000);
		transactionsList.addTransaction(transaction_1);
		user_1.setTransaction(transaction_1);
		user_2.setTransaction(transaction_1);
		Transaction transaction_2 = new Transaction(user_3, user_2, Transaction.TransferCategory.DEBIT, -1000);
		transactionsList.addTransaction(transaction_2);
		user_3.setTransaction(transaction_2);
		user_2.setTransaction(transaction_2);
		Transaction transaction_3 = new Transaction(user_3, user_1, Transaction.TransferCategory.CREDIT, 1000);
		transactionsList.addTransaction(transaction_3);
		user_3.setTransaction(transaction_3);
		user_1.setTransaction(transaction_3);

		transactionsList.removeTransactionById(transaction_1.getTransactionId());
		System.err.println(Arrays.toString(transactionsList.toArray()));
		System.out.println("----------");

		System.out.println(Arrays.toString(user_1.getTransactions()));
		System.out.println("----------");
		System.out.println(Arrays.toString(user_2.getTransactions()));
		System.out.println("----------");
		System.out.println(Arrays.toString(user_3.getTransactions()));
	}
}
