public class UserIdsGenerator {
	private static UserIdsGenerator instance = null;
	private int id;

	private UserIdsGenerator() {
		this.id = 0;
	}

	public static UserIdsGenerator getInstance() {
		if (instance == null)
			instance = new UserIdsGenerator();
		return instance;
	}

	public int generateId() {
		return this.id++;
	}
}
