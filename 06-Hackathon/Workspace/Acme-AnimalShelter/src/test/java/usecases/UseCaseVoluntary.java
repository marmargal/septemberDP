package usecases;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import services.DonationService;
import services.EventService;
import services.NoticeService;
import services.StandService;
import utilities.AbstractTest;
import domain.Donation;
import domain.Event;
import domain.Notice;
import domain.Stand;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring/junit.xml" })
@Transactional
public class UseCaseVoluntary extends AbstractTest{

	@Autowired
	private NoticeService noticeService;
	@Autowired
	private DonationService donationService;

	@Autowired
	private EventService eventService;
	
	@Autowired
	private StandService standService;
	

	/*
	 * Caso de uso: Voluntary-> Dar un aviso de un animal en riesgo.. 16a
	 */

	@Test
	public void createNoticeTest() {

		final Object testingData[][] = {
				{
						// Positive
						"voluntary1", "notice1", null },
				{
						// Negative: employee try to create a notice
						"employee1", "notice1", 
						IllegalArgumentException.class },
				{
						// Negative: veterinary try to create a notice
						"veterinary1", "notice1",
						IllegalArgumentException.class}
						};

		for (int i = 0; i < testingData.length; i++)
			this.templateCreateApplicationTest(i, (String) testingData[i][0],
					(String) testingData[i][1], 
					(Class<?>) testingData[i][2]);
	}

	protected void templateCreateApplicationTest(final Integer i,
			final String principal, final String application, 
			final Class<?> expected) {
		Class<?> caught;

		caught = null;
		try {
			super.authenticate(principal);

			final Notice noticeBD = this.noticeService.create();
			noticeBD.setVersion(1);

			this.noticeService.flush();

		} catch (final Throwable oops) {
			caught = oops.getClass();
		}
		super.checkExceptions(expected, caught);

	}

	
	
	
	/*
	 * Caso de uso: Voluntary-> Realizar una donación 16b
	 */

	@Test
	public void createDonationTest() {

		final Object testingData[][] = {
				{
						// Positive
						"voluntary1", "donation1", "event1", null },
				{
						// Negative: event that not exists
						"voluntary1", "donation1", "event9",
						NumberFormatException.class },
				{
						// Negative: event that not exists
						"voluntary1", "donation1", null,
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
	 * Caso de uso: Voluntary-> Apuntarse y quitarse de un stand 16c
	 */

	@Test
	public void joinDisjoinStandTest() {

		final Object testingData[][] = {
				{
						// Positive
						"voluntary1", "stand1",  null },
				{
						// Negative: stand not asociated
						"voluntary1", "stand3", 
						NumberFormatException.class },
				{
						// Negative: stand that not exists
						"voluntary1", null, 
						NullPointerException.class } 
						};

		for (int i = 0; i < testingData.length; i++)
			this.templateJoinDisjoinStandTest(i, (String) testingData[i][0],
					(String) testingData[i][1], 
					(Class<?>) testingData[i][2]);
	}

	protected void templateJoinDisjoinStandTest(final Integer i,
			final String principal, final String stand,
			final Class<?> expected) {
		Class<?> caught;

		caught = null;
		try {
			super.authenticate(principal);
			int standId = super.getEntityId(stand);
			Stand stand1 = this.standService.findOne(standId);
			this.standService.joinVoluntary(stand1);

			this.donationService.flush();

		} catch (final Throwable oops) {
			caught = oops.getClass();
		}
		super.checkExceptions(expected, caught);

	}
	
	
}
