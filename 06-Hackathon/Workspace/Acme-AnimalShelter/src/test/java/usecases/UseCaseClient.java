package usecases;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import services.ClientService;
import services.DonationService;
import utilities.AbstractTest;
import domain.Donation;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring/junit.xml" })
@Transactional
public class UseCaseClient extends AbstractTest {
	@Autowired
	private ClientService clientService;

	@Autowired
	private DonationService donationService;

	/*
	 * Caso de uso: Client-> Realizar una donación 18a
	 */

	@Test
	public void createDonationTest() {

		final Object testingData[][] = { {
				// Positive
				"client1", "donation1", true, null }, {
				// Negative
				"client1", "", true, NullPointerException.class } };

		for (int i = 0; i < testingData.length; i++)
			this.templateCreateDonationTest(i, (String) testingData[i][0],
					(String) testingData[i][1], (boolean) testingData[i][2],
					(Class<?>) testingData[i][3]);
	}

	protected void templateCreateDonationTest(final Integer i,
			final String principal, final String donation,
			final boolean banner, final Class<?> expected) {
		Class<?> caught;

		caught = null;
		try {
			super.authenticate(principal);

			final Donation donationBD = new Donation();
			donationBD.setCuantity(2.0);
			donationBD.setName("donacion15");
			donationBD.setVersion(12);

			this.donationService.save(donationBD);
			this.donationService.flush();

		} catch (final Throwable oops) {
			caught = oops.getClass();
		}
		super.checkExceptions(expected, caught);

	}

}
