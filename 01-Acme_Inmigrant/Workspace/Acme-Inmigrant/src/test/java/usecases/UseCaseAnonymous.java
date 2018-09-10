package usecases;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import security.Authority;
import security.UserAccount;
import services.ImmigrantService;
import services.OfficerService;
import utilities.AbstractTest;
import domain.Immigrant;
import domain.Officer;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
	"classpath:spring/junit.xml"
})
@Transactional
public class UseCaseAnonymous extends AbstractTest {
	
	@Autowired
	private OfficerService officerService;
	
	@Autowired
	private ImmigrantService immigrantService;
	
	@Test
	public void RegisterOfficerTest() {
		
		final Object testingData[][] = {
				{ // Positive
					"name", "surname", "email@gmail.com", "321654987", "address", null
				}
				, { // Negative: wrong email
					"name", "surname", "email", "321654987", "address", DataIntegrityViolationException.class
				}, { // Negative: wrong surname
					"name", "", "email@gmail.com", "321654987", "address", DataIntegrityViolationException.class
				}
			};

			for (int i = 0; i < testingData.length; i++)
				this.templateRegisterOfficer(
					(String) testingData[i][0], // name
					(String) testingData[i][1], // surname
					(String) testingData[i][2], // email
					(String) testingData[i][3], // phoneNumber
					(String) testingData[i][4], // address
					(Class<?>) testingData[i][5]);
		
	}
	
	protected void templateRegisterOfficer(
			final String name, final String surname, 
			final String email, final String phoneNumber, final String address, final Class<?> expected) {

		Class<?> caught;
		caught = null;

		try {
			
			final Officer officer = this.officerService.create();
			
			final UserAccount userAccount = new UserAccount();
			userAccount.setUsername("userTest");
			userAccount.setPassword("userTest");
			final List<Authority> authorities = new ArrayList<>();
			final Authority aut = new Authority();
			aut.setAuthority("OFFICER");
			authorities.add(aut);
			userAccount.setAuthorities(authorities);
			
			officer.setName(name);
			officer.setSurname(surname);
			officer.setEmail(email);
			officer.setPhoneNumber(phoneNumber);
			officer.setAddress(address);
			officer.setUserAccount(userAccount);
			
			this.officerService.save(officer);
			this.unauthenticate();
			this.officerService.flush();
			this.authenticate(officer.getUserAccount().getUsername());
		} catch (final Throwable oops) {
			caught = oops.getClass();
		}
		super.checkExceptions(expected, caught);
	}
	
	@Test
	public void RegisterImmigrantTest() {
		
		final Object testingData[][] = {
				{ // Positive
					"name", "surname", "email@gmail.com", "321654987", "address", null
				}
				, { // Negative: wrong email
					"name", "surname", "email", "321654987", "address", DataIntegrityViolationException.class
				}, { // Negative: wrong surname
					"name", "", "email@gmail.com", "321654987", "address", DataIntegrityViolationException.class
				}
			};

			for (int i = 0; i < testingData.length; i++)
				this.templateRegisterImmigrant(
					(String) testingData[i][0], // name
					(String) testingData[i][1], // surname
					(String) testingData[i][2], // email
					(String) testingData[i][3], // phoneNumber
					(String) testingData[i][4], // address
					(Class<?>) testingData[i][5]);
		
	}
	
	protected void templateRegisterImmigrant(
			final String name, final String surname, 
			final String email, final String phoneNumber, final String address, final Class<?> expected) {

		Class<?> caught;
		caught = null;

		try {
			
			final Immigrant immigrant = this.immigrantService.create();
			
			final UserAccount userAccount = new UserAccount();
			userAccount.setUsername("userTest");
			userAccount.setPassword("userTest");
			final List<Authority> authorities = new ArrayList<>();
			final Authority aut = new Authority();
			aut.setAuthority("IMMIGRANT");
			authorities.add(aut);
			userAccount.setAuthorities(authorities);
			
			immigrant.setName(name);
			immigrant.setSurname(surname);
			immigrant.setEmail(email);
			immigrant.setPhoneNumber(phoneNumber);
			immigrant.setAddress(address);
			immigrant.setUserAccount(userAccount);
			
			this.immigrantService.save(immigrant);
			this.unauthenticate();
			this.immigrantService.flush();
			this.authenticate(immigrant.getUserAccount().getUsername());
		} catch (final Throwable oops) {
			caught = oops.getClass();
		}
		super.checkExceptions(expected, caught);
	}


}
