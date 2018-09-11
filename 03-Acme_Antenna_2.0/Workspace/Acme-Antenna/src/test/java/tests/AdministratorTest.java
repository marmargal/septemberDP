package tests;

import java.util.ArrayList;
import java.util.Collection;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Assert;

import services.ActorService;
import services.AdministratorService;
import services.BannerService;
import services.CommentService;
import services.ConfigurationService;
import services.PlatformService;
import services.SatelliteService;
import services.TutorialService;
import utilities.AbstractTest;
import domain.Actor;
import domain.Banner;
import domain.Comment;
import domain.Configuration;
import domain.Platform;
import domain.Satellite;
import domain.Tutorial;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring/junit.xml" })
@Transactional
public class AdministratorTest extends AbstractTest {

	@Autowired
	private TutorialService tutorialService;
	
	@Autowired
	private CommentService commentService;
	
	@Autowired
	private SatelliteService satelliteService;
	
	@Autowired
	private PlatformService platformService;
	
	
	// 5.2 Browse the list of satellites and navigate to the platforms that they broadcast
	
	@Test
	public void listSatellitesTest() {

		final Object testingData[][] = {
			{	// Positivo
				"admin", null
			}	// No pueden existir tests negativos, ya que puede hacerlo cualquier rol y persona sin autenticar
		};

		for (int i = 0; i < testingData.length; i++)
			this.templateListSatellitesTest(i, (String) testingData[i][0], (Class<?>) testingData[i][1]);
	}

	protected void templateListSatellitesTest(final Integer i, final String administrator, final Class<?> expected) {
		Class<?> caught;

		caught = null;
		try {
			super.authenticate(administrator);

			final Collection<Satellite> satellites = this.satelliteService.findAll();
			
			final Collection<Platform> platforms = new ArrayList<>();
			
			for(Satellite satellite: satellites){
				platforms.addAll(satellite.getPlatforms());
			}

			Assert.notNull(satellites);

		} catch (final Throwable oops) {
			caught = oops.getClass();
		}
		super.checkExceptions(expected, caught);
	}
	
	// 5.3 Browse the list of platforms and navigate to the satellites that broadcast them
	
	@Test
	public void listPlatformTest() {

		final Object testingData[][] = {
			{	// Positivo
				"admin", null
			}	// No pueden existir tests negativos, ya que puede hacerlo cualquier rol y persona sin autenticar
		};

		for (int i = 0; i < testingData.length; i++)
			this.templateListPlatformTest((String) testingData[i][0], (Class<?>) testingData[i][1]);
	}
	

	protected void templateListPlatformTest(final String administrator, final Class<?> expected) {
		Class<?> caught;

		caught = null;
		try {
			super.authenticate(administrator);

			final Collection<Platform> platforms = this.platformService.findAll();
			
			final Collection<Satellite> satellites = new ArrayList<>();
			
			for(Platform platform: platforms){
				satellites.add(platform.getSatellite());
			}

			Assert.notNull(platforms);

		} catch (final Throwable oops) {
			caught = oops.getClass();
		}
		super.checkExceptions(expected, caught);
	}
	
	// 5.4 Search for satellites and platforms using a single key word that must be contained in their names or descriptions
	
	@Test
	public void searchSatellitesAndPlatformTest() {

		final Object testingData[][] = {
			{	// Positivo
				"admin", null
			}	// No pueden existir tests negativos, ya que puede hacerlo cualquier rol y persona sin autenticar
		};

		for (int i = 0; i < testingData.length; i++)
			this.templateSearchSatellitesAndPlatformTest((String) testingData[i][0], (Class<?>) testingData[i][1]);
	}
	

	protected void templateSearchSatellitesAndPlatformTest(final String administrator, final Class<?> expected) {
		Class<?> caught;

		caught = null;
		try {
			super.authenticate(administrator);

			final Collection<Platform> platforms = this.platformService.searchPlatform("Platform");
			
			final Collection<Satellite> satellites = this.satelliteService.searchSatellite("Satellite");

			Assert.notNull(satellites);
			Assert.notNull(platforms);

		} catch (final Throwable oops) {
			caught = oops.getClass();
		}
		super.checkExceptions(expected, caught);
	}
	
	// 16.1 Remove a tutorial that he or she thinks is inappropriate
	
	@Test
	public void removeTutorialTest() {

		final Object testingData[][] = {
				
				// Un administrador elimina un tutorial	
				
			{	// Positivo
				"admin", "tutorial1", null
			}, {// Negativo (tutorial que no existe)
				"administrator1", "tutorial8", IllegalArgumentException.class
			}, {// Negativo (admin que no existe)
				"administrator1", "tutorial1", IllegalArgumentException.class
			}
			
		};

		for (int i = 0; i < testingData.length; i++)
			this.templateRemoveTutorialTest((String) testingData[i][0], (String) testingData[i][1], (Class<?>) testingData[i][2]);
	}

	protected void templateRemoveTutorialTest(final String administrator, final String tutorialEntity, final Class<?> expected) {
		Class<?> caught;

		caught = null;
		try {
			super.authenticate(administrator);
			final int tutorialId = this.getEntityId(tutorialEntity);
			final Tutorial tutorial = this.tutorialService.findOne(tutorialId);

			this.tutorialService.delete(tutorial);

			this.unauthenticate();
			this.tutorialService.flush();
		} catch (final Throwable oops) {
			caught = oops.getClass();
		}
		super.checkExceptions(expected, caught);
	}
	
	// 16.2 Remove a comment that he or she thinks is inappropriate.
	
	@Test
	public void removeCommentTest() {

		final Object testingData[][] = {
				
				// Un administrador elimina un comentario	
				
			{	// Positivo
				"admin", "comment1", null
			}, {// Negativo (comentario que no existe)
				"administrator1", "comment8", IllegalArgumentException.class
			}, {// Negativo (comentario que no existe)
				"administrator1", "comment1", IllegalArgumentException.class
			}
			
		};

		for (int i = 0; i < testingData.length; i++)
			this.templateRemoveCommentTest((String) testingData[i][0], (String) testingData[i][1], (Class<?>) testingData[i][2]);
	}

	protected void templateRemoveCommentTest(final String administrator, final String commentEntity, final Class<?> expected) {
		Class<?> caught;

		caught = null;
		try {
			super.authenticate(administrator);
			final int commentId = this.getEntityId(commentEntity);
			final Comment comment = this.commentService.findOne(commentId);

			this.commentService.delete(comment);

			this.unauthenticate();
			this.commentService.flush();
		} catch (final Throwable oops) {
			caught = oops.getClass();
		}
		super.checkExceptions(expected, caught);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	// antenna 2.0 ---------------------------------------------------------------------------
	
	@Autowired
	private ActorService actorService;
	
	@Autowired
	private AdministratorService administratorService;

	@Autowired
	private BannerService bannerService;
	
	@Autowired
	private ConfigurationService configurationService;
	
	
	// 6.1 Ban/unban another actor
	@Test
	public void banActorTest() {

		final Object testingData[][] = {
			{	
				// Positivo, el admin logeado banea a un agent del sistema
				"admin", "agent1", null
			}, 
			
			{
				// Negativo, un agent logeado intenta banear a un user.
				"agent1", "user1", IllegalArgumentException.class
			}
		};

		for (int i = 0; i < testingData.length; i++)
			this.templateBanActorTest((String) testingData[i][0], (String) testingData[i][1], (Class<?>) testingData[i][2]);
	}

	protected void templateBanActorTest(final String administrator, final String banActor, final Class<?> expected) {
		Class<?> caught;

		caught = null;
		try {
			super.authenticate(administrator);
			final int actorId = this.getEntityId(banActor);
			final Actor actor = this.actorService.findOne(actorId);

			this.actorService.ban(actor);
			this.unauthenticate();
			this.actorService.flush();
		} catch (final Throwable oops) {
			caught = oops.getClass();
		}
		super.checkExceptions(expected, caught);
	}

	
	// 11.1 Remove banners that he or she thinks are inappropriate.
	@Test
	public void unbanBannerTest() {

		final Object testingData[][] = {
			{	
				// Positivo, el admin logeado elimina un banner.
				"admin", "banner1", null
			}, 
			
			{
				// Negativo, un user intenta eliminar un banner.
				"user1", "banner1", IllegalArgumentException.class
			}
		};

		for (int i = 0; i < testingData.length; i++)
			this.templateUnbanBannerTest((String) testingData[i][0], (String) testingData[i][1], (Class<?>) testingData[i][2]);
	}

	protected void templateUnbanBannerTest(final String actorName, final String bannerName, final Class<?> expected) {
		Class<?> caught;

		caught = null;
		try {
			super.authenticate(actorName);
			final int bannerId = this.getEntityId(bannerName);
			final Banner banner = this.bannerService.findOne(bannerId);
			
			this.bannerService.delete(banner);
			this.unauthenticate();
			
			this.actorService.flush();
		} catch (final Throwable oops) {
			caught = oops.getClass();
		}
		super.checkExceptions(expected, caught);
	}
	

	// 13.1 Manage a list of taboo words.
	@Test
	public void editTabooWordTest() {

		final Object testingData[][] = {
			{	
				// Positivo, el admin logeado edita las taboo words.
				"admin", "word1", "configuration", null
			}, 
			
			{
				// Negativo, un user intenta añadir edita las taboo words.
				"user", "word1", "configuration", IllegalArgumentException.class
			}
		};

		for (int i = 0; i < testingData.length; i++)
			this.templateEditTabooWordTest((String) testingData[i][0], (String) testingData[i][1], (String) testingData[i][2], (Class<?>) testingData[i][3]);
	}

	protected void templateEditTabooWordTest(final String actorName, final String newWord, final String configurationName, Class<?> expected) {
		Class<?> caught;

		caught = null;
		try {
			super.authenticate(actorName);
			
			final int configuraionId = this.getEntityId(configurationName);
			Configuration configuration = this.configurationService.findOne(configuraionId);
			Collection<String> tabooWords = configuration.getTabooWords();
			tabooWords.add(newWord);
			configuration.setTabooWords(tabooWords);
			this.configurationService.save(configuration);
			this.unauthenticate();
			
			this.actorService.flush();
		} catch (final Throwable oops) {
			caught = oops.getClass();
		}
		super.checkExceptions(expected, caught);
	}
	
	
	// 13.2 List the tutorials and comments that have taboo words.
	@Test
	public void listTutorialsTabooTest() {

		final Object testingData[][] = {
			{	
				// Positivo, el admin logeado lista los commentatios que tienen taboo word.
				"admin", null
			}, 
			
			{
				// Negativo, un user intenta lista los commentatios que tienen taboo word.
				"user1", IllegalArgumentException.class
			}
		};

		for (int i = 0; i < testingData.length; i++)
			this.templateListTutorialsTabooTest((String) testingData[i][0], (Class<?>) testingData[i][1]);
	}

	@SuppressWarnings("unused")
	protected void templateListTutorialsTabooTest(final String actorName, final Class<?> expected) {
		Class<?> caught;

		caught = null;
		try {
			super.authenticate(actorName);
			
			this.administratorService.checkAuthority();
			Collection<Tutorial> tutorials = this.tutorialService.tutorialsTaboo();
			
			this.unauthenticate();
		} catch (final Throwable oops) {
			caught = oops.getClass();
		}
		super.checkExceptions(expected, caught);
	}
	
	@Test
	public void listCommentsTabooTest() {

		final Object testingData[][] = {
			{	
				// Positivo, el admin logeado lista los commentatios que tienen taboo word.
				"admin", null
			}, 
			
			{
				// Negativo, un user intenta lista los commentatios que tienen taboo word.
				"user1", IllegalArgumentException.class
			}
		};

		for (int i = 0; i < testingData.length; i++)
			this.templateListCommentsTabooTest((String) testingData[i][0], (Class<?>) testingData[i][1]);
	}

	@SuppressWarnings("unused")
	protected void templateListCommentsTabooTest(final String actorName, final Class<?> expected) {
		Class<?> caught;

		caught = null;
		try {
			super.authenticate(actorName);
			
			this.administratorService.checkAuthority();
			Collection<Comment> comments = this.commentService.commentsTaboo();
			
			this.unauthenticate();
		} catch (final Throwable oops) {
			caught = oops.getClass();
		}
		super.checkExceptions(expected, caught);
	}
	
	
	// 13.3 Remove a tutorial or a comment that contains taboo words.
	@Test
	public void removeTutorialTabooTest() {

		final Object testingData[][] = {
			{	
				// Positivo, el admin logeado elimina un tutorial que tiene algún taboo word.
				"admin", "tutorial3", null
			}, 
			
			{
				// Negativo, un user intenta elimina un tutorial que tiene algún taboo word.
				"user1", "tutorial3", IllegalArgumentException.class
			}
		};

		for (int i = 0; i < testingData.length; i++)
			this.templateRemoveTutorialTabooTest((String) testingData[i][0], (String) testingData[i][1], (Class<?>) testingData[i][2]);
	}

	protected void templateRemoveTutorialTabooTest(final String actorName, final String tutorialName, final Class<?> expected) {
		Class<?> caught;

		caught = null;
		try {
			super.authenticate(actorName);
			final int tutorialId = this.getEntityId(tutorialName);
			Tutorial tutorial = this.tutorialService.findOne(tutorialId);
			this.tutorialService.delete(tutorial);
			
			this.unauthenticate();
			this.tutorialService.flush();
		} catch (final Throwable oops) {
			caught = oops.getClass();
		}
		super.checkExceptions(expected, caught);
	}
	
	@Test
	public void removeCommentTabooTest() {

		final Object testingData[][] = {
			{	
				// Positivo, el admin logeado elimina un comment que tiene algún taboo word.
				"admin", "comment3", null
			}, 
			
			{
				// Negativo, un user intenta elimina un comment que tiene algún taboo word.
				"user1", "comment3", IllegalArgumentException.class
			}
		};

		for (int i = 0; i < testingData.length; i++)
			this.templateRemoveCommentTabooTest((String) testingData[i][0], (String) testingData[i][1], (Class<?>) testingData[i][2]);
	}

	protected void templateRemoveCommentTabooTest(final String actorName, final String commentName, final Class<?> expected) {
		Class<?> caught;

		caught = null;
		try {
			super.authenticate(actorName);
			final int commentId = this.getEntityId(commentName);
			Comment comment = this.commentService.findOne(commentId);
			this.commentService.delete(comment);
			
			this.unauthenticate();
			this.commentService.flush();
		} catch (final Throwable oops) {
			caught = oops.getClass();
		}
		super.checkExceptions(expected, caught);
	}
	
	
	
	
	
	
}
