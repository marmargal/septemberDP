package usecases;

import javax.validation.ConstraintViolationException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import services.AgentService;
import utilities.AbstractTest;
import domain.Agent;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring/junit.xml" })
@Transactional
public class UseaseAnonymous extends AbstractTest {

	

	@Autowired
	private AgentService agentService;

	

	/*
	 * 3. An actor who is not authenticated must be able to: 1. Register to the
	 * system as an agent.
	 */

	@Test
	public void RegisterAsAgentTest() {
		final Object testingData[][] = {

				// positivo,alguien se registra en el sistema como agent

				{ "name1", "surname", "email@email.com", "671910556", "address",
						"https://www.google.com/", "postalAddress", null },

				// negativo, alguien se registra en el sistema como agent pero
				// sin nombre
				{ "", "surname", "email@gmail.com", "671910556", "address",
						"https://www.google.com/", "postalAddress",
						ConstraintViolationException.class },
				// negativo, alguien se registra en el sistema como agent pero
				// con el mismo nombre y contraseña que otro
				{ "agent1", "surname", "email@gmail.com", "671910556",
						"address", "https://www.google.com/", "postalAddress",
						ConstraintViolationException.class },

		};

		for (int i = 0; i < testingData.length; i++) {
			this.templateRegisterAsAgent((String) testingData[i][0],
					(String) testingData[i][1], (String) testingData[i][2],
					(String) testingData[i][3], (String) testingData[i][4],
					(String) testingData[i][5], (String) testingData[i][6],
					(Class<?>) testingData[i][7]);
		}
	}

	private void templateRegisterAsAgent(String name, String surname,
			String email, String phoneNumber, String address, String picture,
			String postalAddress, Class<?> expected) {
		Class<?> caught;
		caught = null;

		try {
			super.unauthenticate();

			Agent user = this.agentService.create();
			user.setAddress(address);
			user.setEmail(email);
			user.setName(name);
			user.setPhoneNumber(phoneNumber);
			user.setSurname(surname);
			user.setPictures(picture);
			user.getUserAccount().setUsername(name);
			if (name.equals("")) {
				user.getUserAccount().setPassword("aaaaaa");

			} else {
				user.getUserAccount().setPassword(name);
			}
			Agent saved = this.agentService.save(user);
			Assert.isTrue(agentService.findAll().contains(saved));
			this.unauthenticate();
			this.agentService.flush();

		} catch (final Throwable oops) {
			caught = oops.getClass();
		}
		super.checkExceptions(expected, caught);

	}

}
