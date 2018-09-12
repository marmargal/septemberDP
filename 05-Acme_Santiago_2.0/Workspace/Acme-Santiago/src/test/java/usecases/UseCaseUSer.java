package usecases;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import services.ChirpService;
import services.CommentService;
import services.CompostelaService;
import services.HikeService;
import services.InnService;
import services.RouteService;
import services.UserService;
import services.WalkService;
import utilities.AbstractTest;
import domain.Compostela;
import domain.Route;
import domain.Walk;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring/junit.xml" })
@Transactional
public class UseCaseUSer extends AbstractTest {

	@Autowired
	private RouteService routeService;

	@Autowired
	private InnService innService;

	@Autowired
	private CompostelaService compostelaService;

	@Autowired
	private HikeService hikeService;

	@Autowired
	private CommentService commentService;

	@Autowired
	private UserService userService;

	@Autowired
	private ChirpService chirpService;

	@Autowired
	private WalkService walkService;

	/*
	 * 9. An actor who is authenticated as a user must be able to: 1.
	 * Instantiate a route as a walk.
	 */

	@Test
	public void instaciateWalkUserTest() {
		final Object testingData[][] = {

				// positivo, el user1 instancia una ruta como walk
				{ "user1", null },
				// negativo, el agent1 instancia una ruta como walk
				{ "agent1", IllegalArgumentException.class },
				// negativo, el alguien sin autenticar instancia una ruta como
				// walk
				{ "", IllegalArgumentException.class },

		};

		for (int i = 0; i < testingData.length; i++) {
			this.templateInstaciateWalkUser((String) testingData[i][0],
					(Class<?>) testingData[i][1]);
		}
	}

	private void templateInstaciateWalkUser(String userName, Class<?> expected) {
		Class<?> caught;
		caught = null;

		try {
			super.unauthenticate();
			super.authenticate(userName);
			Route route = this.routeService
					.findOne(super.getEntityId("route1"));
			Walk walk = this.walkService.create(route);
			Collection<Date> daysOfEachHike = new ArrayList<>();
			Date date = new Date(System.currentTimeMillis() - 100);
			daysOfEachHike.add(date);
			walk.setDaysOfEachHike(daysOfEachHike);
			walk.setInn(innService.findOne(super.getEntityId("inn1")));
			walk.setTitle("aaa");
			this.walkService.save(walk);
			super.unauthenticate();
		} catch (final Throwable oops) {
			caught = oops.getClass();
		}
		super.checkExceptions(expected, caught);

	}

	/*
	 * 9. An actor who is authenticated as a user must be able to: 2. List his
	 * or her walks.
	 */
	@Test
	public void listWalkUserTest() {
		final Object testingData[][] = {

				// positivo, el user1 lista sus walks
				{ "user1", null },
				// negativo, el agent1 lista los walks
				{ "agent1", IllegalArgumentException.class },
				// negativo, el alguien sin autenticar lista los walks
				{ "", IllegalArgumentException.class },

		};

		for (int i = 0; i < testingData.length; i++) {
			this.templateListWalkUser((String) testingData[i][0],
					(Class<?>) testingData[i][1]);
		}
	}

	private void templateListWalkUser(String userName, Class<?> expected) {
		Class<?> caught;
		caught = null;

		try {
			super.unauthenticate();
			super.authenticate(userName);
			this.walkService.findWalkByUser(super.getEntityId(userName));
			super.unauthenticate();
		} catch (final Throwable oops) {
			caught = oops.getClass();
		}
		super.checkExceptions(expected, caught);

	}

	/*
	 * 9. An actor who is authenticated as a user must be able to: 3. Request a
	 * Compostela for a walk of his or hers.
	 */

	@Test
	public void RequestCompostelaUserTest() {
		final Object testingData[][] = {

				// positivo, el user1 solicita un compostela
				{ "user1", null },
				// negativo, el agent1 solicita un compostela
				{ "agent1", IllegalArgumentException.class },
				// negativo, el alguien sin autenticar solicita un compostela
				{ "", IllegalArgumentException.class },

		};

		for (int i = 0; i < testingData.length; i++) {
			this.templateRequestCompostelaUser((String) testingData[i][0],
					(Class<?>) testingData[i][1]);
		}
	}

	private void templateRequestCompostelaUser(String userName,
			Class<?> expected) {
		Class<?> caught;
		caught = null;

		try {
			super.unauthenticate();
			super.authenticate(userName);
			Compostela compostela = this.compostelaService.create();
			compostela.setWalk(this.walkService.findOne(super
					.getEntityId("walk1")));
			this.compostelaService.save(compostela);
			super.unauthenticate();
		} catch (final Throwable oops) {
			caught = oops.getClass();
		}
		super.checkExceptions(expected, caught);

	}

}
