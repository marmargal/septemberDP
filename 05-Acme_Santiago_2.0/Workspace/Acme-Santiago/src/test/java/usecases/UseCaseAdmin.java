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

import services.AdministratorService;
import services.ChirpService;
import services.CommentService;
import services.ConfigurationService;
import services.HikeService;
import services.RouteService;
import utilities.AbstractTest;
import domain.Chirp;
import domain.Comment;
import domain.Configuration;
import domain.Hike;
import domain.Route;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring/junit.xml" })
@Transactional
public class UseCaseAdmin extends AbstractTest {

	@Autowired
	private RouteService routeService;

	@Autowired
	private HikeService hikeService;

	@Autowired
	private ConfigurationService configurationService;

	@Autowired
	private ChirpService chirpService;

	@Autowired
	private CommentService commentService;

	@Autowired
	private AdministratorService administratorService;

	/*
	 * 6. An actor who is authenticated as an administrator must be able to: 1.
	 * Remove a route that he or she thinks is inappropriate. Removing a route
	 * involves removing all of the hikes of which it is composed.
	 */
	@Test
	public void RouteAdminTest() {
		final Object testingData[][] = {
				// positivo, el admin borra el route1
				{ "admin", "route1", null },
				// negativo, alguien sin autenticar intenta borrarlo
				{ "", "route1", IllegalArgumentException.class },
				// newgativo, un user intenta borrar un route que no es suyo
				{ "user1", "route2", IllegalArgumentException.class }, };
		for (int i = 0; i < testingData.length; i++) {
			this.templateRouteAdmin((String) testingData[i][0],
					(String) testingData[i][1], (Class<?>) testingData[i][2]);
		}
	}

	private void templateRouteAdmin(String adminName, String routeName,
			Class<?> expected) {
		Class<?> caught;
		caught = null;

		try {
			this.authenticate(adminName);
			Route route1 = this.routeService.findOne(super
					.getEntityId(routeName));
			Collection<Hike> hikesRoute1 = new ArrayList<>();
			hikesRoute1.addAll(route1.getHikes());
			this.routeService.delete(route1);

			routeService.flush();
			hikeService.flush();
			this.unauthenticate();

		} catch (final Throwable oops) {
			caught = oops.getClass();
		}
		super.checkExceptions(expected, caught);

	}

	/*
	 * 16. An actor who is authenticated as an administrator must be able to: 1.
	 * Manage a list of taboo words.
	 */

	@Test
	public void tabooWordsAdminTest() {
		final Object testingData[][] = {
				// positivo, el admin añade una palabra más a las tabooWords
				{ "admin", "taboo", null },
				// negativo, el user1 añade una palabra más a las tabooWords
				{ "user1", "taboo", IllegalArgumentException.class },
				// negativo, alguien sin autenticar añade una palabra más a las
				// tabooWords
				{ "", "taboo", IllegalArgumentException.class },
				// positivo, el admin quita una palabra más a las tabooWords

				{ "admin", "sex", null }, };
		for (int i = 0; i < testingData.length; i++) {
			this.templatetabooWordsAdmin((String) testingData[i][0],
					(String) testingData[i][1], (Class<?>) testingData[i][2]);
		}

	}

	private void templatetabooWordsAdmin(String adminName, String tabooWord,
			Class<?> expected) {
		Class<?> caught;
		caught = null;

		try {
			this.authenticate(adminName);
			Collection<String> taboos = this.configurationService
					.findTabooWords();
			if (tabooWord.equals("sex")) {
				taboos.remove(tabooWord);
			} else {
				taboos.add(tabooWord);
			}
			Configuration config = this.configurationService.findOne(super
					.getEntityId("configuration"));
			config.setTabooWords(taboos);
			this.configurationService.save(config);
			if (tabooWord.equals("sex")) {
				Assert.isTrue(!this.configurationService
						.findOne(super.getEntityId("configuration"))
						.getTabooWords().contains(tabooWord));
			} else {
				Assert.isTrue(this.configurationService
						.findOne(super.getEntityId("configuration"))
						.getTabooWords().contains(tabooWord));
			}

			this.unauthenticate();
			configurationService.flush();

		} catch (final Throwable oops) {
			caught = oops.getClass();
		}
		super.checkExceptions(expected, caught);
	}

	/*
	 * * 16. An actor who is authenticated as an administrator must be able to:
	 * 2. List the chirps that contain taboo words. 4. List the comments that
	 * contain taboo words.
	 */

	@Test
	public void chirptCommentListabooAdminTest() {
		final Object testingData[][] = {
				// positivo, el admin lista los chirps con taboo words
				{ "admin", "chirp", null },
				// positivo, el admin lista los comments con taboo words
				{ "admin", "comment", null },
				// negativo, el user1 lista los chirps con taboo words
				{ "user1", "chirp", IllegalArgumentException.class },
				// negativo, el user1 lista los comments con taboo words
				{ "user1", "comment", IllegalArgumentException.class },
				// negativo, alguien sin autenticar lista los chirps con taboo
				// words
				{ "", "chirp", IllegalArgumentException.class },
				// negativo,alguien sin autenticar lista los comments con taboo
				// words
				{ "", "comment", IllegalArgumentException.class }

		};
		for (int i = 0; i < testingData.length; i++) {
			this.templatechirptCommentListAdmin((String) testingData[i][0],
					(String) testingData[i][1], (Class<?>) testingData[i][2]);
		}
	}

	private void templatechirptCommentListAdmin(String adminName,
			String chirpComment, Class<?> expected) {
		Class<?> caught;
		caught = null;

		try {
			this.authenticate(adminName);

			if (chirpComment.equals("chirp")) {
				for (Chirp chirp : this.chirpService.findChirpTaboo()) {
					Assert.isTrue(chirp.getTaboo());
				}
			} else {
				for (Comment comment : this.commentService.findCommentTaboo()) {
					Assert.isTrue(comment.isTaboo());
				}
			}
			this.chirpService.flush();
			this.unauthenticate();
		} catch (final Throwable oops) {
			caught = oops.getClass();
		}
		super.checkExceptions(expected, caught);

	}

	/*
	 * 16. An actor who is authenticated as an administrator must be able to: 3.
	 * Remove a chirp that he or she thinks is inappropriate. 5. Remove a
	 * comment that he or she thinks is inappropriate.
	 */

	@Test
	public void chirptCommentRemoveAdminTest() {
		final Object testingData[][] = {
				// positivo, el admin borra un chirp
				{ "admin", "chirp1", null },
				// positivo, el admin borra un comment
				{ "admin", "comment1", null },
				// negativo, alguien sin autenticar borra un chirp
				{ "", "chirp1", IllegalArgumentException.class },
				// negativo, alguien sin autenticar borra un comment
				{ "", "comment1", IllegalArgumentException.class },
				// negativo, el user1 borra un chirp
				{ "user1", "chirp1", IllegalArgumentException.class },
				// negativo, el user1 borra un comment
				{ "user1", "comment1", IllegalArgumentException.class } };

		for (int i = 0; i < testingData.length; i++) {
			this.templatechirptCommentRemoveAdmin((String) testingData[i][0],
					(String) testingData[i][1], (Class<?>) testingData[i][2]);
		}
	}

	private void templatechirptCommentRemoveAdmin(String adminName,
			String chirpComment, Class<?> expected) {
		Class<?> caught;
		caught = null;

		try {
			this.authenticate(adminName);
			if (chirpComment.equals("chirp1")) {
				Chirp chirp = this.chirpService.findOne(super
						.getEntityId(chirpComment));
				this.chirpService.delete(chirp);
				Assert.isTrue(!this.chirpService.findAll().contains(chirp));

			} else {
				Comment comment = this.commentService.findOne(super
						.getEntityId(chirpComment));
				this.commentService.delete(comment);
				Assert.isTrue(!this.commentService.findAll().contains(comment));

			}

			this.chirpService.flush();
			this.commentService.flush();
			this.administratorService.flush();
			this.unauthenticate();
		} catch (final Throwable oops) {
			caught = oops.getClass();
		}
		super.checkExceptions(expected, caught);
	}

}
