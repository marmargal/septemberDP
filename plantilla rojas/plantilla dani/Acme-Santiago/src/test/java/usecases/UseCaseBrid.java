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

import services.BridService;
import utilities.AbstractTest;
import domain.Brid;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring/junit.xml" })
@Transactional
public class UseCaseBrid extends AbstractTest {
	
	@Autowired
	private BridService bridService;
	
	/*
	 * It must include one positive and one negative test case regarding 
	 * an administrator who authenticates, lists the XXXX that are awaiting 
	 * a decision, selects one of them, and accepts it or denies it. 
	 * The decision must be made randomly.
	 */
	
	
	
	@Test
	public void listBridAdminTest() {
		final Object testingData[][] = {
				// positivo, el admin lista los brids
				{ "admin", null },
				// negativo, el user1 lista los brids
				{ "user1", IllegalArgumentException.class },

		};
		for (int i = 0; i < testingData.length; i++) {
			this.templateListBridAdmin((String) testingData[i][0],
					(Class<?>) testingData[i][1]);
		}
	}

	private void templateListBridAdmin(String adminName, Class<?> expected) {
		Class<?> caught;
		caught = null;

		try {
			this.authenticate(adminName);
			this.bridService.findBridsWithoutDecision();

			this.unauthenticate();

		} catch (final Throwable oops) {
			caught = oops.getClass();
		}
		super.checkExceptions(expected, caught);

	}
	
	@Test
	public void editBridAdminTest() {
		final Object testingData[][] = {
				// positivo, el admin realiza la decision
				{ "admin", "brid2", null },
				// negativo, el user1 realiza la decision
				{ "user1", "brid2", IllegalArgumentException.class },

		};
		for (int i = 0; i < testingData.length; i++) {
			this.templateEditBridAdmin((String) testingData[i][0], // username
					(String) testingData[i][1], // brid
					(Class<?>) testingData[i][2]);
			
		}
	}

	private void templateEditBridAdmin(String adminName, String brid, Class<?> expected) {
		Class<?> caught;
		caught = null;

		try {
			this.authenticate(adminName);
			
			final int bridId = this.getEntityId(brid);
			final Brid bridFinal = this.bridService.findOne(bridId);
			
			List<Boolean> decision = new ArrayList<Boolean>();
			decision.add(true);
			decision.add(false);
			Boolean random = decision.get(new Random().nextInt(decision.size()));
			
			bridFinal.setApproved(random);

			this.bridService.save(bridFinal);
			this.unauthenticate();
			this.bridService.flush();
		} catch (final Throwable oops) {
			caught = oops.getClass();
		}
		super.checkExceptions(expected, caught);

	}

	
	/*select t.administrator from Brid t where (select count(w) from Brid w where w.administrator.id = t.administrator.id and w.approved = true)
>= all(select (select count(w2) from Brid w2 where w2.administrator.id = t2.administrator.id and w2.approved = true) from Brid t2);*/
}
