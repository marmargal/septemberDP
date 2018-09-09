package usecases;

import java.util.ArrayList;
import java.util.Collection;

import javax.validation.ConstraintViolationException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import services.UserService;
import utilities.AbstractTest;
import domain.User;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring/junit.xml" })
@Transactional
public class UseaseAnonymous extends AbstractTest {

	@Autowired
	private UserService userService;

	/*
	 * 3. An actor who is not authenticated must be able to: 1. Register to the
	 * system as a user.
	 */

	@Test
	public void RegisterAsUserTest() {
		final Object testingData[][] = {
				// positivo,alguien se registra en el sistema como user
				{ "name", "surname", "email@email.com", "671910556", "address",
						"https://www.google.com/", "postalAddress", null },

				// negativo, alguien se registra en el sistema como user pero
				// con el email mal
				{ "name", "surname", "emailcom", "671910556", "address",
						"https://www.google.com/", "postalAddress",
						ConstraintViolationException.class },

		};

		for (int i = 0; i < testingData.length; i++) {
			this.templateRegisterAsUser((String) testingData[i][0],
					(String) testingData[i][1], (String) testingData[i][2],
					(String) testingData[i][3], (String) testingData[i][4],
					(String) testingData[i][5], (String) testingData[i][6],
					(Class<?>) testingData[i][7]);
		}
	}

	private void templateRegisterAsUser(String name, String surname,
			String email, String phoneNumber, String address, String picture,
			String postalAddress, Class<?> expected) {
		Class<?> caught;
		caught = null;

		try {

			User user = this.userService.create();
			user.setAddress(address);
			user.setEmail(email);
			user.setName(name);
			user.setPhoneNumber(phoneNumber);
			user.setSurname(surname);
			user.setPictures(picture);
			user.getUserAccount().setPassword(name);

			User saved = this.userService.save(user);
			Assert.isTrue(userService.findAll().contains(saved));
			this.userService.flush();

		} catch (final Throwable oops) {
			System.out.println(oops.getMessage());
			this.userService.flush();

			caught = oops.getClass();
		}
		super.checkExceptions(expected, caught);

	}
}

/*
 * 3. An actor who is not authenticated must be able to: 2. Browse the routes.
 */

/*
 * 3. Search for routes using a single key word that must appear somewhere in
 * their names, their descriptions, or their hikes. 4. Search for routes whose
 * length is in a user-provided range. 5. Search for routes that have a minimum
 * or a maximum number of hikes. 6. Browse the users of the system and their
 * profiles, which must include their personal data and the list of routes that
 * they have registered.
 */

