
import java.util.UUID;

public class Transaction {
	public enum TransferCategory {
		DEBIT,
		CREDIT
	}

	final private UUID transactionId;
	final private User recipient;
	final private User sender;
	final private TransferCategory transferCategory;
	final private Integer transferAmount;

    public Transaction(User recipient, User sender, TransferCategory transferCategory, Integer transferAmount) {
		this.transactionId = UUID.randomUUID();
        this.recipient = recipient;
        this.sender = sender;
        this.transferCategory = transferCategory;
        this.transferAmount = transferAmount;
    }

	public UUID getTransactionId() {
		return transactionId;
	}

	public User getRecipient() {
		return recipient;
	}

	public User getSender() {
		return sender;
	}

	public TransferCategory getTransferCategory() {
		return transferCategory;
	}

	public Integer getTransferAmount() {
		return transferAmount;
	}
}
