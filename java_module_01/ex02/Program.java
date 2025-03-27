public class Program {
	public static void main(String[] args) {
		User user_1 = new User("Amine", 4087);
		User user_2 = new User("Mouad", 0);
		User user_3 = new User("Anass", 7000);

		UsersArrayList usersList = new UsersArrayList();
		usersList.addUser(user_1);
		usersList.addUser(user_2);
		usersList.addUser(user_3);

		System.out.println(usersList.getUserById(2));
		System.out.println(usersList.getUserByIndex(0));
		System.out.println(usersList.getUsersCount());

		System.out.println(usersList);
	}
}