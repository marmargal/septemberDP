package usecases;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import services.CambioService;
import utilities.AbstractTest;
import domain.Cambio;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring/junit.xml" })
@Transactional
public class UseCaseCambio extends AbstractTest {
	
	@Autowired
	private CambioService cambioService;
	
	/*
	 * It must include one positive and one negative test case regarding 
	 * an administrator who authenticates, lists the XXXX that are awaiting 
	 * a decision, selects one of them, and accepts it or denies it. 
	 * The decision must be made randomly.
	 */
	
	@Test
	public void listCambioAdminTest() {
		final Object testingData[][] = {
				// positivo, el admin lista los cambios
				{ "admin", null },
				// negativo, el user1 lista los cambios
				{ "user1", IllegalArgumentException.class },

		};
		for (int i = 0; i < testingData.length; i++) {
			this.templateListCambioAdmin((String) testingData[i][0],
					(Class<?>) testingData[i][1]);
		}
	}

	private void templateListCambioAdmin(String adminName, Class<?> expected) {
		Class<?> caught;
		caught = null;

		try {
			this.authenticate(adminName);
			this.cambioService.findCambiosWithoutDecision();

			this.unauthenticate();

		} catch (final Throwable oops) {
			caught = oops.getClass();
		}
		super.checkExceptions(expected, caught);

	}
	
	@Test
	public void editCambioAdminTest() {
		final Object testingData[][] = {
				// positivo, el admin realiza la decision
				{ "admin", "cambio2", null },
				// negativo, el user1 realiza la decision
				{ "user1", "cambio2", IllegalArgumentException.class },

		};
		for (int i = 0; i < testingData.length; i++) {
			this.templateEditCambioAdmin((String) testingData[i][0], // username
					(String) testingData[i][1], // cambio
					(Class<?>) testingData[i][2]);
		}
	}

	private void templateEditCambioAdmin(String adminName, String cambio, Class<?> expected) {
		Class<?> caught;
		caught = null;

		try {
			this.authenticate(adminName);
			
			final int cambioId = this.getEntityId(cambio);
			final Cambio cambioFinal = this.cambioService.findOne(cambioId);
			
			List<Boolean> decision = new ArrayList<Boolean>();
			decision.add(true);
			decision.add(false);
			Boolean random = decision.get(new Random().nextInt(decision.size()));
			
			cambioFinal.setApproved(random);

			this.cambioService.save(cambioFinal);
			this.unauthenticate();
			this.cambioService.flush();
		} catch (final Throwable oops) {
			caught = oops.getClass();
		}
		super.checkExceptions(expected, caught);

	}

}
