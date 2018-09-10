package usecases;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import javax.validation.ConstraintViolationException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import services.ChirpService;
import services.CommentService;
import services.HikeService;
import services.RouteService;
import services.UserService;
import utilities.AbstractTest;
import domain.Chirp;
import domain.Comment;
import domain.Hike;
import domain.Route;
import domain.User;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring/junit.xml" })
@Transactional
public class UseCaseUSer extends AbstractTest {

	@Autowired
	private RouteService routeService;

	@Autowired
	private HikeService hikeService;

	@Autowired
	private CommentService commentService;

	@Autowired
	private UserService userService;

	@Autowired
	private ChirpService chirpService;

	// suit de test funcionales
	// 5. An actor who is authenticated as a user must be able to:
	// 1. Manage his or her routes, which includes creating, editing, deleting,
	// and listing them.

	@Test
	public void RouteUserTest() {
		final Object testingData[][] = {

				// Positivo(sin observaciones de interés)

				// { "user1", "routeeeee", 300.0, "description",
				// "http://www.google.es", "hike1", "", "", "", null,
				// null, null },
				//
				// // positivo con comentario
				//
				// { "user1", "route", 300.0, "description",
				// "http://www.google.es", "hike1", "01/05/2018",
				// "comment", "text", 1, false, null },
				// // negativo autenticado como innkeeper
				// { "innkeeper1", "route", 300.0, "description",
				// "http://www.google.es", "hike1", "", "", "", null,
				// null, IllegalArgumentException.class },
				// // negativo crear una ruta sin hikes
				// { "user1", "route", 300.0, "description",
				// "http://www.google.es", "", "", "", "", null, null,
				// NullPointerException.class },
				// // positivo editar una ruta cambiando el nombre
				// { "user1", "edit", 300.0, "description",
				// "http://www.google.es", "hike1", "01/05/2018",
				// "comment", "text", 1, false, null },
				//
				// // negativo editar una ruta sin ser user
				// { "innkeeper1", "edit", 300.0, "description",
				// "http://www.google.es", "hike1", "01/05/2018",
				// "comment", "text", 1, false,
				// IllegalArgumentException.class },
				// negativo borrar una ruta sin ser user ni admin
//				{ "innkeeper1", "delete", 300.0, "description",
//						"http://www.google.es", "hike1", "01/05/2018",
//						"comment", "text", 1, false,
//						IllegalArgumentException.class },
				// positivo borrar una ruta

				{ "user1", "delete", 300.0, "description",
						"http://www.google.es", "hike1", "01/05/2018",
						"comment", "text", 1, false, null },
				/*
				 * positivo listar todas las rutas (lo puede hacer cualquira
				 * registrado o no)
				 */
//				{ "user1", "list", 300.0, "description",
//						"http://www.google.es", "hike1", "01/05/2018",
//						"comment", "text", 1, false, null },
//				// negativo crear una ruta sin fotos ni descripción
//				{ "user1", "route", 300.0, "", "", "hike1", "", "", "", null,
//						null, ConstraintViolationException.class },

		};

		for (int i = 0; i < testingData.length; i++) {
			this.templateCreateRoute((String) testingData[i][0],
					(String) testingData[i][1], (Double) testingData[i][2],
					(String) testingData[i][3], (String) testingData[i][4],
					(String) testingData[i][5], (String) testingData[i][6],
					(String) testingData[i][7], (String) testingData[i][8],
					(Integer) testingData[i][9], (Boolean) testingData[i][10],
					(Class<?>) testingData[i][11]);
		}

	}

	private void templateCreateRoute(final String principal, final String name,
			final Double length, final String description,
			final String picture, final String hikeName, final String moment,
			final String title, final String text, final Integer rating,
			final Boolean taboo, final Class<?> expected) {

		Class<?> caught;
		caught = null;

		try {

			this.authenticate(principal);
			Route route = null;
			if (name.equals("edit")) {
				route = routeService.findOne(super.getEntityId("route1"));
				route.setName(name);
			} else {
				route = routeService.create();

				route.setName(name);
				route.setDescription(description);
				route.setLength(length);
				ArrayList<String> pictures = new ArrayList<>();
				pictures.add(picture);
				route.setPictures(pictures);
				Hike hike = null;

				Collection<Hike> hikes = new ArrayList<>();
				if (!hikeName.equals("")) {
					Integer hikeId = super.getEntityId(hikeName);
					hike = hikeService.findOne(hikeId);
					hikes.add(hike);
				} else {
					hikes = null;
				}
				route.setHikes(hikes);
				if (!title.equals("")) {
					Comment comment = commentService.create();
					comment.setPictures(pictures);
					final DateFormat format = new SimpleDateFormat("dd/MM/yyyy");
					final Date d1 = format.parse(moment);
					comment.setMoment(d1);
					comment.setRating(rating);
					comment.setTaboo(taboo);

					comment.setText(text);

					comment.setTitle(title);
					comment.setRoute(route);
					Collection<Comment> comments = new ArrayList<>();
					comments.add(comment);
					route.setComments(comments);
				}
			}
			if (name.equals("delete")) {
				route = routeService.findOne(super.getEntityId("route1"));

				routeService.delete(route);

				Collection<Route> routes = this.routeService.findAll();
				Assert.isTrue(!routes.contains(route));
				this.routeService.flush();
				this.hikeService.flush();
			} else {
				final Route saved = this.routeService.save(route);
				this.routeService.flush();
				Collection<Route> routes = this.routeService.findAll();
				if (name.equals("edit")) {
					Assert.isTrue(routeService.findOne(route.getId()).getName()
							.equals(name));
				} else {
					Assert.isTrue(routes.contains(saved));

				}
			}
			if (name.equals("list")) {
				Collection<Route> routes = this.routeService.findAll();
				Assert.notNull(routes);
			}
			this.unauthenticate();
		} catch (final Throwable oops) {
			caught = oops.getClass();
		}
		super.checkExceptions(expected, caught);
	}

	// 5. An actor who is authenticated as a user must be able to:
	// 2. Follow/unfollow other users.

	@Test
	public void FollowUserTest() {
		final Object testingData[][] = {
				// true hago follow, false unfollow
				// positivo, el user1 hace follow al user2
				{ true, "user1", "user2", null },
				// positivo, el user1 hace unfollow al user2
				{ false, "user1", "user2", null },
				// negativo alguien con rol distinto de user hace follow al
				// user2
				{ true, "Innkeeper1", "user2", IllegalArgumentException.class },
				// negativo alguien con rol distinto de user hace unfollow al
				// user2
				{ false, "Innkeeper1", "user2", IllegalArgumentException.class },
		// no hay más casos negativos ya que si ya seguias al user y vuelves a
		// hacer follow la acción no tiene efecto, y si haces unfollow a alguien
		// a quien no sigues igual

		};

		for (int i = 0; i < testingData.length; i++) {
			this.templateFollow((Boolean) testingData[i][0],
					(String) testingData[i][1], (String) testingData[i][2],
					(Class<?>) testingData[i][3]);
		}

	}

	private void templateFollow(final Boolean follow, final String nameUser1,
			final String nameUser2, final Class<?> expected) {
		Class<?> caught;
		caught = null;

		try {
			this.authenticate(nameUser1);
			User user2 = this.userService.findOne(super.getEntityId(nameUser2));
			User user1 = this.userService.findByPrincipal();
			if (follow) {
				this.userService.follow(user2.getId());

				Assert.isTrue(this.userService.findOne(user1.getId())
						.getFollowing().contains(user2)
						&& this.userService.findOne(user2.getId())
								.getFollowers().contains(user1));
			} else {
				this.userService.unfollow(user2.getId());

				Assert.isTrue(!this.userService.findOne(user1.getId())
						.getFollowing().contains(user2)
						&& !this.userService.findOne(user2.getId())
								.getFollowers().contains(user1));
			}
			this.userService.flush();
			this.unauthenticate();

		} catch (final Throwable oops) {
			caught = oops.getClass();
		}
		super.checkExceptions(expected, caught);
	}

	// 5. An actor who is authenticated as a user must be able to:
	// 3. Browse the users who he or she follows.
	@Test
	public void BrowseFollowingUserTest() {
		final Object testingData[][] = {
				// true hago follow, false unfollow
				// positivo, el user1lista a los usuarios que sigue
				{ "user1", null },
				// negativo, el user2 lista los usuarios que sigue el user1
				{ "user2", IllegalArgumentException.class },
				// negativo, alguien que no es user intenta listar los usuarios
				// que sigue el user1
				{ "innkeeper", IllegalArgumentException.class },

		};

		for (int i = 0; i < testingData.length; i++) {
			this.templateBrowseFollowing((String) testingData[i][0],
					(Class<?>) testingData[i][1]);
		}

	}

	private void templateBrowseFollowing(final String nameUser1,
			final Class<?> expected) {
		Class<?> caught;
		caught = null;

		try {
			this.authenticate(nameUser1);
			User user = this.userService.findByPrincipal();
			Assert.isTrue(this.userService.findAll().containsAll(
					user.getFollowing()));
			if (nameUser1.equals("user2")) {
				Assert.isTrue(this.userService
						.findOne(super.getEntityId("user1")).getFollowing()
						.equals(user.getFollowing()));
			}
			this.userService.flush();
			this.unauthenticate();

		} catch (final Throwable oops) {
			caught = oops.getClass();
		}
		super.checkExceptions(expected, caught);
	}

	// 5. An actor who is authenticated as a user must be able to:
	// 4. Browse the users who follow him or her.

	@Test
	public void BrowseFollowUserTest() {
		final Object testingData[][] = {
				// true hago follow, false unfollow
				// positivo, el user1lista a los usuarios que sigue
				{ "user1", null },
				// negativo, el user2 lista los usuarios que sigue el user1
				{ "user2", IllegalArgumentException.class },
				// negativo, alguien que no es user intenta listar los usuarios
				// que sigue el user1
				{ "innkeeper", IllegalArgumentException.class },

		};

		for (int i = 0; i < testingData.length; i++) {
			this.templateBrowseFollow((String) testingData[i][0],
					(Class<?>) testingData[i][1]);
		}

	}

	private void templateBrowseFollow(final String nameUser1,
			final Class<?> expected) {
		Class<?> caught;
		caught = null;

		try {
			this.authenticate(nameUser1);
			User user = this.userService.findByPrincipal();
			Assert.isTrue(this.userService.findAll().containsAll(
					user.getFollowers()));
			if (nameUser1.equals("user2")) {
				Assert.isTrue(this.userService
						.findOne(super.getEntityId("user1")).getFollowers()
						.equals(user.getFollowers()));
			}
			this.userService.flush();
			this.unauthenticate();

		} catch (final Throwable oops) {
			caught = oops.getClass();
		}
		super.checkExceptions(expected, caught);
	}

	/*
	 * 15. An actor who is authenticated as a user must be able to: 1. Post a
	 * chirp. Chirps may not be changed or deleted once they are posted.
	 */

	@Test
	public void ChirpUserTest() {

		final Object testingData[][] = {
				// positivo, un user crea un chirp
				{ "user1", "title", "text", false, null },
				// negativo,un user crea un chirp con el texto en blanco
				{ "user1", "title", "", false,
						ConstraintViolationException.class },
				// negativo, alguien que no es un user crea un chirp
				{ "innkeeper1", "title", "text", false,
						IllegalArgumentException.class }, };
		for (int i = 0; i < testingData.length; i++) {
			this.templateChirpUser((String) testingData[i][0],
					(String) testingData[i][1], (String) testingData[i][2],
					(Boolean) testingData[i][3], (Class<?>) testingData[i][4]);
		}
	}

	private void templateChirpUser(final String userName, final String title,
			final String text, final Boolean taboo, final Class<?> expected) {
		Class<?> caught;
		caught = null;

		try {
			super.authenticate(userName);
			Chirp chirp = this.chirpService.create();
			chirp.setTaboo(taboo);
			chirp.setText(text);
			chirp.setTitle(title);

			Chirp saved = this.chirpService.save(chirp);
			Assert.notNull(this.chirpService.findOne(saved.getId()));
			this.userService.flush();
			this.unauthenticate();
		} catch (final Throwable oops) {
			caught = oops.getClass();
		}
		super.checkExceptions(expected, caught);
	}

	/*
	 * 15. An actor who is authenticated as a user must be able to: 2. Display a
	 * stream with the chirps posted by all of the users that he or she follows.
	 */
	@Test
	public void ChirpUserDisplayTest() {

		final Object testingData[][] = {

				// positivo, el user ve los chirps de los user que sigue
				{ "user1", null },
				// negativo, el user2 ve los chirps de la gente que sigue del
				// user1
				{ "user2", IllegalArgumentException.class },
				// negativo, alguien que no es user intenta ver los chirps de la
				// gente que sigue del
				// user1
				{ "innkeeper", IllegalArgumentException.class }, };

		for (int i = 0; i < testingData.length; i++) {
			this.templateChirpUserDisplay((String) testingData[i][0],
					(Class<?>) testingData[i][1]);
		}

	}

	private void templateChirpUserDisplay(final String nameUser1,
			final Class<?> expected) {
		Class<?> caught;
		caught = null;

		try {
			this.authenticate(nameUser1);
			User user = this.userService.findByPrincipal();
			Collection<Chirp> chirps = new ArrayList<>();
			for (User u : user.getFollowing()) {
				chirps.addAll(u.getChirps());
			}
			Assert.isTrue(this.chirpService.findAll().containsAll(chirps));
			if (nameUser1.equals("user2")) {
				User user1 = userService.findOne(super.getEntityId("user1"));
				Collection<Chirp> chirps1 = new ArrayList<>();
				for (User u : user1.getFollowing()) {
					chirps1.addAll(u.getChirps());
				}

				Assert.isTrue(chirps.equals(chirps1));
			}
			this.userService.flush();
			this.unauthenticate();

		} catch (final Throwable oops) {
			caught = oops.getClass();
		}
		super.checkExceptions(expected, caught);
	}
}
