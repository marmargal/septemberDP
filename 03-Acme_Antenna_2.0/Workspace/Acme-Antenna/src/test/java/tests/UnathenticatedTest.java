package tests;

import javax.transaction.Transactional;
import javax.validation.ConstraintViolationException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Assert;

import services.AgentService;
import services.HandyworkerService;
import utilities.AbstractTest;
import domain.Agent;
import domain.Handyworker;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring/junit.xml" })
@Transactional
public class UnathenticatedTest extends AbstractTest {
	
	@Autowired
	private HandyworkerService handyworkerService;

	// Antenna 2.0 ---------------------------------------------------------------------------------------------------------------
	
	@Autowired
	private AgentService agentService;
	
	//3.1 Register to the system as a handyworker.
	@Test
	public void registerAsHandyworkerTest() {
		final Object testingData[][] = {

			// positivo,alguien se registra en el sistema como handyworker
			{ "name1", "surname1", "email1@email.com", "671910556",
					"https://www.google1.com/", "postalAddress1", "username1", "pass1", null },
			
			// positivo,alguien se registra en el sistema como handyworker
			{ "name2", "surname2", "email2@email.com", "671910556",
					"https://www.google2.com/", "postalAddress2", "username2", "pass2", null },
			
			// positivo,alguien se registra en el sistema como handyworker
			{ "name3", "surname3", "email3@email.com", "671910556",
					"https://www.google3.com/", "postalAddress3", "username3", "pass3", null },	
			
			// positivo,alguien se registra en el sistema como handyworker
			{ "name4", "surname4", "email4@email.com", "671910556",
					"https://www.google4.com/", "postalAddress4", "username4", "pass4", null },
					
			// positivo,alguien se registra en el sistema como handyworker
			{ "name5", "surname5", "email5@email.com", "671910556",
					"https://www.google5.com/", "postalAddress5",  "username5", "pass5", null },
					
			// negativo, alguien se registra en el sistema como handyworker pero sin nombre
			{ "", "surname", "email@gmail.com", "671910556","https://www.google.com/", "postalAddress", "prueba1", "pass1", ConstraintViolationException.class },
			
			// negativo, alguien se registra en el sistema como handyworker pero sin apellido
			{ "name2", "", "email@gmail.com", "123456789","https://www.gefbvdf.com/", "username2", "pass2", "VSDVSVSV", DataIntegrityViolationException.class },
						
			// negativo, alguien se registra en el sistema como handyworker pero sin mail
			{ "name3", "surname", "", "671910556","https://www.google.com/", "postalAddress", "username3", "pass3", DataIntegrityViolationException.class },
			
			// negativo, alguien se registra en el sistema como handyworker pero sin teléfono
			{ "name4", "surname", "email@gmail.com", "","https://www.google.com/", "postalAddress", "username4", "pass4", DataIntegrityViolationException.class },
			
			// negativo, alguien se registra en el sistema como handyworker pero sin imagenes
			{ "name5", "surname", "email@gmail.com", "671910556","", "postalAddress", "username5", "pass5", DataIntegrityViolationException.class }

		};

		for (int i = 0; i < testingData.length; i++) {
			this.templateRegisterAsHandyworker((String) testingData[i][0],
					(String) testingData[i][1], (String) testingData[i][2],
					(String) testingData[i][3], (String) testingData[i][4],
					(String) testingData[i][5], (String) testingData[i][6], 
					(String) testingData[i][7], (Class<?>) testingData[i][8]);
		}
	}

	private void templateRegisterAsHandyworker(String name, String surname,
			String email, String phoneNumber, String picture,
			String postalAddress, String username, String password, Class<?> expected) {
		Class<?> caught;
		caught = null;

		try {
			super.unauthenticate();
			
			Handyworker handyworker = this.handyworkerService.create();
			handyworker.setEmail(email);
			handyworker.setName(name);
			handyworker.setPhoneNumber(phoneNumber);
			handyworker.setSurname(surname);
			handyworker.setPictures(picture);
			handyworker.setPostalAddress(postalAddress);
			handyworker.getUserAccount().setPassword(password);
			handyworker.getUserAccount().setUsername(username);

			Handyworker saved = this.handyworkerService.save(handyworker);
			Assert.isTrue(handyworkerService.findAll().contains(saved));
			
			this.handyworkerService.flush();
			this.unauthenticate();

		} catch (final Throwable oops) {

			caught = oops.getClass();
		}
		super.checkExceptions(expected, caught);

	}
	
	//3.2 Browse the catalogue of handyworkers.
	
	@Test
	public void browseCatalogueHandyworkersTest() {
		final Object testingData[][] = {
		// caso positivo, alguien no logueado accede a lista de catálogos de los handyworkers
		{ null }
		// no hay más casos, todo el mundo en cualquier momento puede listar los catálogos de los handyworkers.
		};
		this.templateBrowseRoutes((Class<?>) testingData[0][0]);
	}

	private void templateBrowseRoutes(Class<?> expected) {

		Class<?> caught;
		caught = null;
		try {
			this.handyworkerService.findAll();

		} catch (final Throwable oops) {

			caught = oops.getClass();
		}
		super.checkExceptions(expected, caught);
	}
	
	
	//9.1 Register to the system as an agent.
	@Test
	public void registerAsAgentTest() {
		final Object testingData[][] = {

			// positivo,alguien se registra en el sistema como handyworker

			{ "name", "surname", "email@email.com", "671910556",
					"https://www.google.com/", "postalAddress", "username", "password", null },

			// negativo, alguien se registra en el sistema como handyworker pero
			// sin nombre
			{ "", "surname", "email@gmail.com", "671910556",
					"https://www.google.com/", "postalAddress", "username", "password", DataIntegrityViolationException.class },

		};

		for (int i = 0; i < testingData.length; i++) {
			this.templateRegisterAsAgent((String) testingData[i][0],
					(String) testingData[i][1], (String) testingData[i][2],
					(String) testingData[i][3], (String) testingData[i][4],
					(String) testingData[i][5], (String) testingData[i][6], 
					(String) testingData[i][7], (Class<?>) testingData[i][8]);
		}
	}

	private void templateRegisterAsAgent(String name, String surname,
			String email, String phoneNumber, String picture, String postalAddress, String username, String password, Class<?> expected) {
		Class<?> caught;
		caught = null;

		try {
			super.unauthenticate();
			
			Agent agent = this.agentService.create();
			agent.setEmail(email);
			agent.setName(name);
			agent.setPhoneNumber(phoneNumber);
			agent.setSurname(surname);
			agent.setPictures(picture);
			agent.getUserAccount().setPassword(password);
			agent.getUserAccount().setUsername(username);

			Agent saved = this.agentService.save(agent);
			Assert.isTrue(agentService.findAll().contains(saved));

			this.agentService.flush();

		} catch (final Throwable oops) {

			caught = oops.getClass();
		}
		super.checkExceptions(expected, caught);

	}
	
}
