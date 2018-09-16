package usecases;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import services.InnService;
import utilities.AbstractTest;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring/junit.xml" })
@Transactional
public class UseaseAuthenticated extends AbstractTest {

	@Autowired
	private InnService innService;

	/*
	 * 16. An actor who can display an inn must be able to: 1. Display the
	 * amenities that it offers.
	 */

	@Test
	public void listInnsTest() {
		final Object testingData[][] = {
				// caso positivo, user1 accede a lista de inns
				{ null },
				// caso negativo, alguien sin autenticar accede a la lista de
				// inns
				{ IllegalArgumentException.class } };
		this.templatelistInns((Class<?>) testingData[0][0]);
	}

	private void templatelistInns(Class<?> expected) {

		Class<?> caught;
		caught = null;
		try {
			if (expected != null) {
				super.unauthenticate();
			} else {
				super.authenticate("user1");
			}
			this.innService.findAll();
			super.unauthenticate();
		} catch (final Throwable oops) {

			caught = oops.getClass();
		}
		super.checkExceptions(expected, caught);
	}

}
