
import java.util.LinkedList;
import java.util.UUID;

public class TransactionsLinkedList implements TransactionsList {
	final private LinkedList<Transaction> transactions = new LinkedList<>();

	@Override
	public void addTransaction(Transaction transaction) {
		this.transactions.addLast(transaction);
	}

	@Override
	public Transaction removeTransactionById(UUID id) throws TransactionNotFoundException {
		for (Transaction transaction : transactions) {
			if (transaction.getTransactionId() == id) {
				this.transactions.removeFirstOccurrence(transaction);
				return transaction;
			}
		}
		throw new TransactionNotFoundException("Error: Transaction with id=" + id + " not found!");
	}

	@Override
	public Transaction[ ] toArray() {
		return transactions.toArray(Transaction[]::new);
	}
}
