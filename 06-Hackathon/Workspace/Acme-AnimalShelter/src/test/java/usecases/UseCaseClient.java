package usecases;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import services.ApplicationService;
import services.ClientService;
import services.DonationService;
import services.EventService;
import services.PetService;
import utilities.AbstractTest;
import domain.Application;
import domain.Donation;
import domain.Event;
import domain.Pet;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring/junit.xml" })
@Transactional
public class UseCaseClient extends AbstractTest {

	@Autowired
	private DonationService donationService;

	@Autowired
	private EventService eventService;
	
	@Autowired
	private PetService petService;
	
	@Autowired
	private ApplicationService applicationService;

	/*
	 * Caso de uso: Client-> Realizar una donación 18a
	 */

	@Test
	public void createDonationTest() {

		final Object testingData[][] = {
				{
						// Positive
						"client1", "donation1", "event1", null },
				{
						// Negative: event that not exists
						"client1", "donation1", "event9",
						NumberFormatException.class },
				{
						// Negative: event that not exists
						"client1", "donation1", null,
						NullPointerException.class } };

		for (int i = 0; i < testingData.length; i++)
			this.templateCreateDonationTest(i, (String) testingData[i][0],
					(String) testingData[i][1], (String) testingData[i][2],
					(Class<?>) testingData[i][3]);
	}

	protected void templateCreateDonationTest(final Integer i,
			final String principal, final String donation, final String event,
			final Class<?> expected) {
		Class<?> caught;

		caught = null;
		try {
			super.authenticate(principal);
			int eventId = super.getEntityId(event);
			Event event1 = this.eventService.findOne(eventId);

			final Donation donationBD = this.donationService.create(event1);
			donationBD.setCuantity(2.0);
			donationBD.setName("donacion15");
			donationBD.setVersion(12);

			this.donationService.flush();

		} catch (final Throwable oops) {
			caught = oops.getClass();
		}
		super.checkExceptions(expected, caught);

	}
	
	
	/*
	 * Caso de uso: Client-> Realizar una única solicitud por animal. 18b
	 */

	@Test
	public void createApplicationTest() {

		final Object testingData[][] = {
				{"client1", "application1", "pet1", null 
					},
				{
						// Negative: pet that not exists
						"client1", "application1", "pet8",
						NumberFormatException.class },
//				{
//						// Negative: pet that not exists
//						"client1", "application1", null,
//						NullPointerException.class 
//							} 
							};

		for (int i = 0; i < testingData.length; i++)
			this.templateCreateApplicationTest(i, (String) testingData[i][0],
					(String) testingData[i][1], (String) testingData[i][2],
					(Class<?>) testingData[i][3]);
	}

	protected void templateCreateApplicationTest(final Integer i,
			final String principal, final String application, final String pet,
			final Class<?> expected) {
		Class<?> caught;

		caught = null;
		try {
			super.authenticate(principal);
			int petId = super.getEntityId(pet);
			Pet pet1 = this.petService.findOne(petId);

			final Application applicationBD = this.applicationService.create(pet1);
			applicationBD.setVersion(1);

			this.applicationService.save(applicationBD);
			this.applicationService.flush();

		} catch (final Throwable oops) {
			caught = oops.getClass();
		}
		super.checkExceptions(expected, caught);

	}

}
