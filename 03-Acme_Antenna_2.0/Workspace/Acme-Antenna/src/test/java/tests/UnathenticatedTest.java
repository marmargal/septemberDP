package tests;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import security.Authority;
import security.UserAccount;
import services.UserService;
import utilities.AbstractTest;
import domain.User;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring/junit.xml" })
@Transactional
public class UnathenticatedTest extends AbstractTest {
	
	@Autowired
	private UserService userService;

	@Test
	public void registerUserTest() {

		final Object testingData[][] = {
			{	// Positivo
				null
			}	// No pueden existir tests negativos, ya que puede hacerlo cualquier rol y persona sin autenticar
		};

		for (int i = 0; i < testingData.length; i++)
			this.templateRegisterUser((Class<?>) testingData[i][0]);
	}
	
	protected void templateRegisterUser(final Class<?> expected) {
		Class<?> caught;
		caught = null;
		try {
			final UserAccount userAccount = new UserAccount();
			userAccount.setUsername("userTest");
			userAccount.setPassword("userTest");
			final List<Authority> authorities = new ArrayList<>();
			final Authority aut = new Authority();
			aut.setAuthority("USER");
			authorities.add(aut);
			userAccount.setAuthorities(authorities);

			final User userTest = this.userService.create();
			userTest.setEmail("user@gmail.com");
			userTest.setName("user");
			userTest.setPhoneNumber("626253077");
			userTest.setPostalAddress("41009");
			userTest.setSurname("user");
			userTest.setPictures("http://www.google.es");
			userTest.setUserAccount(userAccount);

			this.userService.save(userTest);
			this.unauthenticate();
			this.userService.flush();
			this.authenticate(userTest.getUserAccount().getUsername());
			this.unauthenticate();
		} catch (final Throwable oops) {
			caught = oops.getClass();
		}
		this.checkExceptions(expected, caught);
	}
}
