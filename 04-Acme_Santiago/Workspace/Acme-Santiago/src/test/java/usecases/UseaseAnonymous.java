package usecases;

import java.util.Collection;

import javax.validation.ConstraintViolationException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import services.InnService;
import services.RouteService;
import services.UserService;
import utilities.AbstractTest;
import domain.Hike;
import domain.Inn;
import domain.Route;
import domain.User;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring/junit.xml" })
@Transactional
public class UseaseAnonymous extends AbstractTest {

	@Autowired
	private UserService userService;

	@Autowired
	private RouteService routeService;

	@Autowired
	private InnService innService;

	/*
	 * 3. An actor who is not authenticated must be able to: 1. Register to the
	 * system as a user.
	 */

	@Test
	public void RegisterAsUserTest() {
		final Object testingData[][] = {

				// positivo,alguien se registra en el sistema como user

				{ "name", "surname", "email@email.com", "671910556", "address",
						"https://www.google.com/", "postalAddress", null },

				// negativo, alguien se registra en el sistema como user pero
				// sin nombre
//				{ "", "surname", "email@gmail.com", "671910556", "address",
//						"https://www.google.com/", "postalAddress",
//						ConstraintViolationException.class },

		};

		for (int i = 0; i < testingData.length; i++) {
			this.templateRegisterAsUser((String) testingData[i][0],
					(String) testingData[i][1], (String) testingData[i][2],
					(String) testingData[i][3], (String) testingData[i][4],
					(String) testingData[i][5], (String) testingData[i][6],
					(Class<?>) testingData[i][7]);
		}
	}

	private void templateRegisterAsUser(String name, String surname,
			String email, String phoneNumber, String address, String picture,
			String postalAddress, Class<?> expected) {
		Class<?> caught;
		caught = null;

		try {
			if (name.equals("user1")) {
				this.authenticate("user1");
			}
			User user = this.userService.create();
			user.setAddress(address);
			user.setEmail(email);
			user.setName(name);
			user.setPhoneNumber(phoneNumber);
			user.setSurname(surname);
			user.setPictures(picture);
			user.getUserAccount().setPassword(name);

			User saved = this.userService.save(user);
			Assert.isTrue(userService.findAll().contains(saved));
			this.userService.flush();

		} catch (final Throwable oops) {

			caught = oops.getClass();
		}
		super.checkExceptions(expected, caught);

	}

	/*
	 * 3. An actor who is not authenticated must be able to: 2. Browse the
	 * routes.
	 */
	@Test
	public void BrowseRoutesTest() {
		final Object testingData[][] = {
		// caso positivo, alguien sin loguear accede a lista de rutas
		{ null }
		// no hay más casos, todo el mundo en cualquier momento puede listar las
		// rutas
		};
		this.templateBrowseRoutes((Class<?>) testingData[0][0]);
	}

	private void templateBrowseRoutes(Class<?> expected) {

		Class<?> caught;
		caught = null;
		try {
			this.routeService.findAll();

		} catch (final Throwable oops) {

			caught = oops.getClass();
		}
		super.checkExceptions(expected, caught);
	}

	/*
	 * 3. An actor who is not authenticated must be able to: 3. Search for
	 * routes using a single key word that must appear somewhere in their names,
	 * their descriptions, or their hikes. 4. Search for routes whose length is
	 * in a user-provided range.5. Search for routes that have a minimum or a
	 * maximum number of hikes.
	 */
	@Test
	public void searchTest() {
		final Object testingData[][] = {
				// busqueda con criterio route1
				{ "route1", null },
				// busqueda con criterio description1
				{ "description1", null },
				// busqueda con criterio hike1
				{ "hike1", null },
				// busqueda sin criterio description1(debe devolver todas las
				// rutas)
				{ "", null },
				// busqueda por longitud
				{ "longitud", null },
				// busqueda sin resultado
				{ "ttttttttt", null },
				// busqueda por número de hikes
				{ "hike", null },
		// no hay casos negativos ya que todos los criterios de busqueda son
		// válidos, y si no hay coincidencias devuelve una colección vacía
		};
		for (int i = 0; i < testingData.length; i++) {
			this.templatesearch((String) testingData[i][0],
					(Class<?>) testingData[i][1]);
		}
	}

	private void templatesearch(String criteria, Class<?> expected) {

		Class<?> caught;
		caught = null;
		try {
			if (criteria.equals("hike")) {
				Collection<Route> routes = this.routeService
						.numHikesRoute(3, 1);
				for (Route route : routes) {
					Assert.isTrue(route.getHikes().size() <= 3
							&& route.getHikes().size() >= 1);
				}
			} else if (criteria.equals("longitud")) {
				Collection<Route> routes = this.routeService.lengthRoute(350.,
						250.);
				for (Route route : routes) {
					Assert.isTrue(route.getLength() <= 350.
							&& route.getLength() >= 250.);
				}
			} else {
				Collection<Route> routes = this.routeService
						.searchRoute(criteria);
				if (criteria.isEmpty()) {
					Assert.isTrue(routes.equals(this.routeService.findAll()));
				} else {
					if (criteria.equals("ttttttttt")) {
						Assert.isTrue(routes.isEmpty());
					}
					for (Route route : routes) {
						for (Hike hike : route.getHikes()) {
							Assert.isTrue(route.getName().contains(criteria)
									|| route.getDescription()
											.contains(criteria)
									|| hike.getName().contains(criteria));

						}
					}
				}
			}

		} catch (final Throwable oops) {

			caught = oops.getClass();
		}
		super.checkExceptions(expected, caught);
	}

	/*
	 * 3. An actor who is not authenticated must be able to: 6. Browse the users
	 * of the system and their profiles, which must include their personal data
	 * and the list of routes that they have registered.
	 */
	@Test
	public void BrowseUsersTest() {
		final Object testingData[][] = {
				// alguien accede a la lista de usuarios
				{ "", null },
				// alguien accede al perfil del user1
				{ "user1", null }

		};
		for (int i = 0; i < testingData.length; i++) {
			this.templateBrowseUsers((String) testingData[i][0],
					(Class<?>) testingData[i][1]);
		}
	}

	private void templateBrowseUsers(String userBrowse, Class<?> expected) {

		Class<?> caught;
		caught = null;
		try {
			Collection<User> users = this.userService.findAll();
			if (!userBrowse.isEmpty()) {
				User user = this.userService.findOne(super
						.getEntityId(userBrowse));
				Assert.isTrue(this.userService.findAll().contains(user));
			}
		} catch (final Throwable oops) {

			caught = oops.getClass();
		}
		super.checkExceptions(expected, caught);
	}

	/*
	 * 20. An actor who is not authenticated must be able to: 1. List the inns
	 * that are registered to the system as long as the corresponding credit
	 * cards are not expired.
	 */
	@Test
	public void innTest() {
		final Object testingData[][] = {
		// listar inn con fecha de expiración
		{ 10, 20, null },

		};

		for (int i = 0; i < testingData.length; i++) {
			this.templateInn((Integer) testingData[i][0],
					(Integer) testingData[i][1], (Class<?>) testingData[i][2]);
		}
	}

	private void templateInn(Integer mes, Integer ano, Class<?> expected) {

		Class<?> caught;
		caught = null;
		try {
			Collection<Inn> inns = this.innService.findCcExpirationYear(ano,
					mes);
			for (Inn inn : inns) {
				if ((inn.getCreditCard().getExpirationYear() > ano)) {
					Assert.isTrue(false);
				} else if ((inn.getCreditCard().getExpirationYear() == ano && inn
						.getCreditCard().getExpirationMonth() > mes)) {

					Assert.isTrue(false);

				}

			}
			Assert.isTrue(this.innService.findAll().containsAll(inns));
		} catch (final Throwable oops) {

			caught = oops.getClass();
		}
		super.checkExceptions(expected, caught);

	}

}
