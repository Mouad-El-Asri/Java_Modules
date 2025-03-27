
import java.util.ArrayList;

public class UsersArrayList implements UsersList {
	private int usersListCapacity = 10;
	final private ArrayList<User> usersList = new ArrayList<>(usersListCapacity);

	@Override
	public void addUser(User user) {
		int usersCount = getUsersCount();
		if (usersCount == this.usersListCapacity) {
			this.usersListCapacity += 5;
			this.usersList.ensureCapacity(this.usersListCapacity);
		}
		this.usersList.add(user);
	}


	@Override
	public User getUserById(Integer id) {
		for (User user : this.usersList) {
			if (user.getUserId() == id)
				return user;
		}
		throw new UserNotFoundException("User with id=" + id + " not found!");
	}

	@Override
	public User getUserByIndex(Integer i) {
		return this.usersList.get(i);
	}

	@Override
	public Integer getUsersCount() {
		return this.usersList.size();
	}

	@Override
	public String toString() {
		String users = "";
		for (User user : this.usersList) {
			users += user + "\n";
		}
		return users;
	}
}
