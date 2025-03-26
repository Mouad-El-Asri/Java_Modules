
import java.util.UUID;

public class User {
	final private UUID userId;
	final private String username;
	private Integer balance;

    public User(String name, Integer balance) {
        this.userId = UUID.randomUUID();
        this.username = name;
        this.balance = balance;
    }

	public UUID getuserId() {
		return userId;
	}

	public String getUsername() {
		return username;
	}

	public Integer getbalance() {
		return balance;
	}

	public void setBalance(Integer newBalance) {
		this.balance = newBalance;
	}
}
