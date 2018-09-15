package usecases;

import java.util.ArrayList;
import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import services.AdvertisementService;
import services.AgentService;
import services.HikeService;
import utilities.AbstractTest;
import domain.Advertisement;
import domain.Agent;
import domain.CreditCard;
import domain.Hike;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring/junit.xml" })
@Transactional
public class UseCaseAgent extends AbstractTest {

	@Autowired
	private AdvertisementService advertisementService;

	@Autowired
	private HikeService hikeService;

	@Autowired
	private AgentService agentService;

	/*
	 * 4. An actor who is authenticated as an agent must be able to: 2. Register
	 * an advertisement and associate it with a hike.
	 */
	@Test
	public void RegisterAdvertisemenAgentTest() {
		final Object testingData[][] = {

				// positivo, el agent1 registra un advertisement
				{ "agent1", null },
				// negativo, el user1 registra un advertisement
				{ "user1", IllegalArgumentException.class },
				// negativo, el alguien sin autenticar registra un advertisement
				{ "", IllegalArgumentException.class },

		};

		for (int i = 0; i < testingData.length; i++) {
			this.templateRegisterAdvertisementAgent((String) testingData[i][0],
					(Class<?>) testingData[i][1]);
		}
	}

	private void templateRegisterAdvertisementAgent(String agentName,
			Class<?> expected) {
		Class<?> caught;
		caught = null;

		try {
			super.unauthenticate();
			super.authenticate(agentName);
			Advertisement advertisement = this.advertisementService.create();
			advertisement.setActiveDays(1);
			advertisement.setBanner("https://www.google.es/");
			CreditCard creditCard = new CreditCard();
			creditCard.setBrandName("aaaaaaa");
			creditCard.setCvv(122);
			creditCard.setExpirationMonth(10);
			creditCard.setExpirationYear(25);
			creditCard.setHolderName("aaaaaa");
			creditCard.setNumber("4024007146545768");
			advertisement.setCreditCard(creditCard);
			advertisement.setHike(this.hikeService.findOne(super
					.getEntityId("hike1")));
			advertisement.setTaboo(false);
			advertisement.setTargetPage("https://www.google.es/");
			advertisement.setTitle("aaaaaa");
			this.advertisementService.save(advertisement);
			this.advertisementService.flush();

			super.unauthenticate();
		} catch (final Throwable oops) {
			caught = oops.getClass();
		}
		super.checkExceptions(expected, caught);

	}

	/*
	 * 4. An actor who is authenticated as an agent must be able to:3. List the
	 * hikes in which they have registered an advertisement.
	 */
	@Test
	public void listHikesAdvertisemenAgentTest() {
		final Object testingData[][] = {

				// positivo, el agent1 lista los hikes con un advertisement suyo
				{ "agent1", null },
				// negativo, el user1 lista los hikes con un advertisement
				{ "user1", NullPointerException.class },
				// negativo, el alguien sin autenticar lista los hikes con un
				// advertisement suyo
				{ "", IllegalArgumentException.class },

		};

		for (int i = 0; i < testingData.length; i++) {
			this.templateListHikesAdvertisemenAgent((String) testingData[i][0],
					(Class<?>) testingData[i][1]);
		}
	}

	private void templateListHikesAdvertisemenAgent(String agentName,
			Class<?> expected) {
		Class<?> caught;
		caught = null;

		try {
			super.unauthenticate();
			super.authenticate(agentName);
			Agent agent = this.agentService.findByPrincipal();
			Collection<Hike> hikes = new ArrayList<>();

			Collection<Advertisement> advertisements = new ArrayList<Advertisement>();
			advertisements.addAll(agent.getAdvertisements());

			for (Advertisement a : advertisements) {
				hikes.addAll(this.hikeService.findHikeByAdvertisement(a.getId()));
			}
			super.unauthenticate();
		} catch (final Throwable oops) {
			caught = oops.getClass();
		}
		super.checkExceptions(expected, caught);

	}

	/*
	 * 4. An actor who is authenticated as an agent must be able to: 4. List the
	 * hikes in which they have not registered any advertisements.
	 */

	@Test
	public void listHikesWithoutAdvertisemenAgentTest() {
		final Object testingData[][] = {

				// positivo, el agent1 lista los hikes sin un advertisement suyo
				{ "agent1", null },
				// negativo, el user1 lista los hikes sin un advertisement
				{ "user1", NullPointerException.class },
				// negativo, el alguien sin autenticar lista los hikes sin un
				// advertisement suyo
				{ "", IllegalArgumentException.class },

		};

		for (int i = 0; i < testingData.length; i++) {
			this.templateListHikesWithoutAdvertisemenAgent(
					(String) testingData[i][0], (Class<?>) testingData[i][1]);
		}
	}

	private void templateListHikesWithoutAdvertisemenAgent(String agentName,
			Class<?> expected) {
		Class<?> caught;
		caught = null;

		try {
			super.unauthenticate();
			super.authenticate(agentName);
			Agent agent = this.agentService.findByPrincipal();
			Collection<Hike> hikes = new ArrayList<>();

			Collection<Advertisement> advertisements = new ArrayList<Advertisement>();
			advertisements.addAll(agent.getAdvertisements());

			for (Advertisement a : advertisements) {
				hikes.addAll(this.hikeService.findHikeByAdvertisement(a.getId()));
			}
			Collection<Hike> hikesRes = new ArrayList<Hike>();
			hikesRes.addAll(hikeService.findAll());

			hikesRes.removeAll(hikes);
			Assert.isTrue(!hikesRes.containsAll(hikes));
			super.unauthenticate();
		} catch (final Throwable oops) {
			caught = oops.getClass();
		}
		super.checkExceptions(expected, caught);

	}
}
