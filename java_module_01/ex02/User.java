public class User {
	final private Integer userId;
	final private String name;
	private Integer balance;

    public User(String name, Integer balance) {
		this.userId = UserIdsGenerator.getInstance().generateId();
        this.name = name;
		if (balance < 0) {
			System.err.println("Error: Balance is negative!");
			System.exit(-1);
		}
        this.balance = balance;
    }

	public Integer getUserId() {
		return this.userId;
	}

	public String getName() {
		return this.name;
	}

	public Integer getbalance() {
		return this.balance;
	}

	public void setBalance(Integer newBalance) {
		if (newBalance < 0) {
			System.err.println("Error: New balance is negative!");
			System.exit(-1);
		}
		this.balance = newBalance;
	}

	@Override
	public String toString() {
		return "User{ Id=" + this.userId +
				", Name=" + this.name +
				", Balance=" + this.balance +
				" }";
	}
}
