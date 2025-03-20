package testCases;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import model.User;

class UserTest {

	@Test
	void testCreateUser(){
		User u1 = new User("Andrew", "Password123");
		assertEquals(u1.getUserName(), "Andrew");
	}
	
	@Test
	void testCheckPassword(){
		User u2 = new User("John", "Password123");
		assertTrue(u2.checkPassword("Password123"));
		assertFalse(u2.checkPassword("Password"));
	}

}
