package testCases;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import model.User;
import model.UserManager;

class UserManagerTest {

	@Test
	void createUserTest() {
		UserManager users = new UserManager();
		users.createUser("Andrew", "Password123");
		users.createUser("Andrew", "Password123");
		User u1 = users.loginUser("Andrew", "Password123");
		assertEquals(u1.getUserName(), "Andrew");
	}
}
