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

import services.CommentService;
import services.PlatformService;
import services.SatelliteService;
import services.TutorialService;
import utilities.AbstractTest;
import domain.Comment;
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
		} catch (final Throwable oops) {
			caught = oops.getClass();
		}
		super.checkExceptions(expected, caught);
	}
	
	
}
