import java.util.UUID;

public class Transaction {
    public enum TransferCategory {
        DEBIT,
        CREDIT
    }

    final private UUID transactionId;
    final private User sender;
    final private User recipient;
    final private TransferCategory transferCategory;
    final private Integer transferAmount;

    public Transaction(User sender, User recipient, TransferCategory transferCategory, Integer transferAmount) {
        this.transactionId = UUID.randomUUID();
        this.sender = sender;
        this.recipient = recipient;
        this.transferCategory = transferCategory;
		this.transferAmount = transferAmount;
		if (this.transferCategory == TransferCategory.CREDIT) {
			if (this.transferAmount < 0) {
				System.err.println("Error: Transfer amount cannot be negative for a CREDIT transaction!");
				System.exit(-1);
			}
			this.sender.setBalance(this.sender.getbalance() - this.transferAmount);
			this.recipient.setBalance(this.recipient.getbalance() + this.transferAmount);
		} else if (this.transferCategory == TransferCategory.DEBIT) {
			if (this.transferAmount > 0) {
				System.err.println("Error: Transfer amount must be negative for a DEBIT transaction!");
				System.exit(-1);
			}
			this.sender.setBalance(this.sender.getbalance() + this.transferAmount);
			this.recipient.setBalance(this.recipient.getbalance() - this.transferAmount);
		}
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

    @Override
    public String toString() {
        return "Transaction{ TransactionId=" + this.transactionId +
				", Sender=" + this.sender.getName() +
				", Recipient=" + this.recipient.getName() +
				", TransferCategory=" + this.transferCategory +
				", amount=" + this.transferAmount +
				" }";
    }
}
