package usecases;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import services.AdvertisementService;
import services.CompostelaService;
import utilities.AbstractTest;
import domain.Advertisement;
import domain.Compostela;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring/junit.xml" })
@Transactional
public class UseCaseAdmin extends AbstractTest {

	@Autowired
	private AdvertisementService advertisementService;

	@Autowired
	private CompostelaService compostelaService;

	/*
	 * 5. An actor who is authenticated as an administrator must be able to: 1.
	 * List the advertisements that contain taboo words in its title.
	 */
	@Test
	public void listAdvertisementsAdminTest() {
		final Object testingData[][] = {
				// positivo, el admin lista los advertisements con taboo words
				{ "admin", null },
				// negativo, el user1 lista los advertisements con taboo words
				{ "user1", IllegalArgumentException.class },
				// negativo, el alguien sin autenticar lista los advertisements
				// con taboo words
				{ "", IllegalArgumentException.class },

		};
		for (int i = 0; i < testingData.length; i++) {
			this.templateListAdvertisementsAdmin((String) testingData[i][0],
					(Class<?>) testingData[i][1]);
		}
	}

	private void templateListAdvertisementsAdmin(String adminName,
			Class<?> expected) {
		Class<?> caught;
		caught = null;

		try {
			this.authenticate(adminName);
			this.advertisementService.findAdvertisementTaboo();

			this.unauthenticate();

		} catch (final Throwable oops) {
			caught = oops.getClass();
		}
		super.checkExceptions(expected, caught);

	}

	/*
	 * 5. An actor who is authenticated as an administrator must be able to: 2.
	 * Remove an advertisement that he or she thinks is inappropriate.
	 */
	@Test
	public void deleteAdvertisementsAdminTest() {
		final Object testingData[][] = {
				// positivo, el admin borra un advertisement
				{ "admin", null },
				// negativo, el user1 borra un advertisement
				{ "user1", IllegalArgumentException.class },
				// negativo, el alguien sin autenticar borra un advertisement
				{ "", IllegalArgumentException.class },

		};
		for (int i = 0; i < testingData.length; i++) {
			this.templateDeleteAdvertisementsAdmin((String) testingData[i][0],
					(Class<?>) testingData[i][1]);
		}
	}

	private void templateDeleteAdvertisementsAdmin(String adminName,
			Class<?> expected) {
		Class<?> caught;
		caught = null;

		try {
			this.authenticate(adminName);
			Advertisement advertisement = this.advertisementService
					.findOne(super.getEntityId("advertisement1"));
			this.advertisementService.delete(advertisement);
			this.unauthenticate();

		} catch (final Throwable oops) {
			caught = oops.getClass();
		}
		super.checkExceptions(expected, caught);

	}

	/*
	 * 11. An actor who is authenticated as an administrator must be able to: 1.
	 * List the requests for Compostelas that are awaiting a decision.
	 */
	@Test
	public void listCompostelaAdminTest() {
		final Object testingData[][] = {
				// positivo, el admin lista los compostelas que no tienen una
				// decisión final
				{ "admin", null },
				// negativo, el user1 lista los compostelas que no tienen una
				// decisión final
				{ "user1", IllegalArgumentException.class },
				// negativo, el alguien sin autenticar lista los compostelas que
				// no tienen una decisión final
				{ "", IllegalArgumentException.class },

		};
		for (int i = 0; i < testingData.length; i++) {
			this.templateListCompostelaAdmin((String) testingData[i][0],
					(Class<?>) testingData[i][1]);
		}
	}

	private void templateListCompostelaAdmin(String adminName, Class<?> expected) {
		Class<?> caught;
		caught = null;

		try {
			this.authenticate(adminName);
			this.compostelaService.findCompostelaByFinallyDecision(false);

			this.unauthenticate();

		} catch (final Throwable oops) {
			caught = oops.getClass();
		}
		super.checkExceptions(expected, caught);

	}

	/*
	 * 11. An actor who is authenticated as an administrator must be able to: 2.
	 * Make a decision regarding a Compostela. The decision may be either to
	 * approve it (if the user has stayed in an inn at the end of every hike of
	 * the corresponding route) or deny it (otherwise). In cases in which a
	 * Compostela is denied, the administrator must provide a justification.
	 */
	@Test
	public void decisionCompostelaAdminTest() {
		final Object testingData[][] = {
				// positivo, el admin toma una decision final sobre un
				// compostela
				{ "admin", null },
				// negativo, el user1 toma una decision final sobre un
				// compostela
				// decisión final
				{ "user1", IllegalArgumentException.class },
				// negativo, el alguien sin autenticar toma una decision final
				// sobre un compostela
				{ "", IllegalArgumentException.class },

		};
		for (int i = 0; i < testingData.length; i++) {
			this.templateDecisionCompostelaAdmin((String) testingData[i][0],
					(Class<?>) testingData[i][1]);
		}
	}

	private void templateDecisionCompostelaAdmin(String adminName,
			Class<?> expected) {
		Class<?> caught;
		caught = null;

		try {
			this.authenticate(adminName);
			Compostela compostela = this.compostelaService.findOne(super
					.getEntityId("compostela1"));
			compostela.setfinallyDecision(true);
			compostelaService.save(compostela);
			this.unauthenticate();
			this.compostelaService.flush();
		} catch (final Throwable oops) {
			caught = oops.getClass();
		}
		super.checkExceptions(expected, caught);

	}
}
