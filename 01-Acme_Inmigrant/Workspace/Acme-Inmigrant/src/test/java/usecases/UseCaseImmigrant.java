package usecases;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import services.ApplicationService;
import utilities.AbstractTest;
import domain.Application;
import domain.CreditCard;
import domain.PersonalSection;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
	"classpath:spring/junit.xml"
})
@Transactional
public class UseCaseImmigrant extends AbstractTest {
	
	@Autowired
	private ApplicationService applicationService;
	
	/*
	 * 12. An actor who is authenticated as an immigrant must be able to:
			1. Manage an arbitrary number of applications, which includes opening them, closing
			them, editing them, linking them to applications by other users, and displaying
			them. Once an application is opened, it can be edited until it is closed. Closing an
			application that is linked to others closes them all immediately.
			2. Answer the questions that an officer has posed on any of his or her applications.
			3. List his or her applications according to whether they are opened, closed and accepted, 
			closed and rejected, or closed and awaiting a decision.
	 */
	
	@Test
	public void CreateApplicationTest() {
		
		final Object testingData[][] = {
				{// Positive
					"immigrant1", "05/07/2018", "2.0", false, null
				}, {//Positive
					"immigrant1", "02/02/2018", "3.0", false, null
				}, {// Negative: wrong roll
					"officer1", "02/02/2018", "3.0", false, IllegalArgumentException.class
				}
			};

			for (int i = 0; i < testingData.length; i++)
				this.templateCreateApplication((String) testingData[i][0], // Username login
					(String) testingData[i][1], // closedMoment
					Double.parseDouble((String) testingData[i][2]), //statistics
					(Boolean) testingData[i][3], // closed
					(Class<?>) testingData[i][4]);
		
	}
	
	protected void templateCreateApplication(final String principal,
			final String closedMoment, final Double statistics,
			final Boolean closed, final Class<?> expected) {

		Class<?> caught;
		caught = null;

		try {
			super.authenticate(principal);
			final Application application = this.applicationService.create();
			
			CreditCard cc = new CreditCard();
			cc.setBrandName("MasterCard");
			cc.setCvv(334);
			cc.setExpirationMonth(12);
			cc.setExpirationYear(2019);
			cc.setHolderName("Raul");
			cc.setNumber("5574588374439106");

			final DateFormat format = new SimpleDateFormat("dd/MM/yyyy");
			final Date d1 = format.parse(closedMoment);
			application.setClosedMoment(d1);
			application.setCreditCard(cc);
			application.setStatistics(statistics);
			application.setClosed(closed);
			
			PersonalSection personalSection = new PersonalSection();
			Date birthDate = new Date(System.currentTimeMillis()-1000);
			personalSection.setBirthDate(birthDate);
			personalSection.setBirthPlace("Sevilla");
			Collection<String> names = new ArrayList<>();
			names.add("Daniel");
			personalSection.setNames(names);
			personalSection.setPicture("http://www.imagen.com");
			
			application.setPersonalSection(personalSection);

			this.applicationService.save(application);
			this.unauthenticate();
			this.applicationService.flush();
		} catch (final Throwable oops) {
			caught = oops.getClass();
		}
		super.checkExceptions(expected, caught);
	}
	
	@Test
	public void EditApplicationTest() {

		final Object testingData[][] = {
			{ // Positive
				"immigrant1", "application1", "Sevilla", null
			}, { // Negative: wrong roll
				"officer1", "application1", "Sevilla", IllegalArgumentException.class }
			, { // Negative: wrong roll
				"immigrant1", "application2", "Sevilla", IllegalArgumentException.class }
		};

		for (int i = 0; i < testingData.length; i++)
			this.templateEditApplication((String) testingData[i][0], // Username
				(String) testingData[i][1], // applicationId
				(String) testingData[i][2], // PersonalSection birthPlace
				(Class<?>) testingData[i][3]);
	}
	
	protected void templateEditApplication(final String immigrant, final String application, final String birthPlace, final Class<?> expected) {
		Class<?> caught;

		caught = null;

		try {
			
			super.authenticate(immigrant);
			
			final int applicationId = this.getEntityId(application);
			final Application applicationFinal = this.applicationService.findOne(applicationId);
			
			applicationFinal.getPersonalSection().setBirthPlace(birthPlace);
			
			this.applicationService.save(applicationFinal);
			this.unauthenticate();
			this.applicationService.flush();
			
		} catch (final Throwable oops) {
			caught = oops.getClass();
		}
		super.checkExceptions(expected, caught);
	}
	
	@Test
	public void ListApplicationTest() {

		final Object testingData[][] = {
			{ // Positive
				"immigrant1", null
			}, { // Negative: wrong roll
				"officer1", null }
		};

		for (int i = 0; i < testingData.length; i++)
			this.listApplicationTemplate((String) testingData[i][0], // Username
				(Class<?>) testingData[i][1]);
	}
	
	protected void listApplicationTemplate(final String immigrant, final Class<?> expected) {
		Class<?> caught;
		caught = null;
		try {

			this.authenticate(immigrant);
			this.applicationService.findAll();
			
		} catch (final Throwable oops) {
			caught = oops.getClass();
		}
		this.checkExceptions(expected, caught);
	}

}
