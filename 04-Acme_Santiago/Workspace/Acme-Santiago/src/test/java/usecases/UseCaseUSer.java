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
import org.springframework.util.Assert;

import services.CommentService;
import services.HikeService;
import services.RouteService;
import services.UserService;
import utilities.AbstractTest;
import domain.Comment;
import domain.Hike;
import domain.Route;
import domain.User;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring/junit.xml" })
public class UseCaseUSer extends AbstractTest {

	@Autowired
	private RouteService routeService;

	@Autowired
	private UserService userService;

	@Autowired
	private HikeService hikeService;

	@Autowired
	private CommentService commentService;

	// suit de test funcionales
	// 5. An actor who is authenticated as a user must be able to:
	// 1. Manage his or her routes, which includes creating, editing, deleting,
	// and listing them.
	// autenticado como user ---> crear una ruta
	@Test
	public void createRouteTest() {
		final Object testingData[][] = {
				// Positivo(sin observaciones de interés)

				{ "user1", "route", 300.0, "description",
						"http://www.google.es", "hike1", "", "", "", null,
						null, null },
				// positivo con comentario

				{ "user1", "route", 300.0, "description",
						"http://www.google.es", "hike1", "01/05/2018",
						"comment", "text", 1, false, null },
				// negativo autenticado como innkeeper
				{ "innkeeper1", "route", 300.0, "description",
						"http://www.google.es", "hike1", "", "", "", null,
						null, NullPointerException.class }

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
			final Route route = routeService.create();

			route.setName(name);
			route.setDescription(description);
			route.setLength(length);
			ArrayList<String> pictures = new ArrayList<>();
			pictures.add(picture);
			route.setPictures(pictures);
			User user = this.userService.findOne(userService.findByPrincipal()
					.getId());
			route.setUser(user);
			Integer hikeId = super.getEntityId(hikeName);
			final Hike hike = hikeService.findOne(hikeId);
			Collection<Hike> hikes = new ArrayList<>();
			hikes.add(hike);
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
				comment.setUser(user);
				comment.setRoute(route);
				Collection<Comment> comments = new ArrayList<>();
				comments.add(comment);
				route.setComments(comments);
			}

			final Route saved = this.routeService.save(route);
			this.routeService.flush();
			Collection<Route> routes = this.routeService.findAll();
			Assert.isTrue(routes.contains(saved));
			this.unauthenticate();
		} catch (final Throwable oops) {

			caught = oops.getClass();
		}
		super.checkExceptions(expected, caught);
	}
}
