package tests;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Assert;

import services.AntennaService;
import services.CommentService;
import services.PlatformService;
import services.SatelliteService;
import services.SubscriptionService;
import services.TutorialService;
import utilities.AbstractTest;
import domain.Antenna;
import domain.Comment;
import domain.CreditCard;
import domain.Platform;
import domain.Satellite;
import domain.Subscription;
import domain.Tutorial;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring/junit.xml" })
@Transactional
public class UserTest extends AbstractTest{

	@Autowired
	private SatelliteService satelliteService;

	@Autowired
	private PlatformService platformService;
	
	@Autowired
	private AntennaService antennaService;
	
	@Autowired
	private SubscriptionService subscriptionService;
	
	@Autowired
	private TutorialService tutorialService;
	
	@Autowired
	private CommentService commentService;
	
	// En los tests de esta clase se han añadido en conjunto tanto los que puede hacer un user, ya pudiera hacerlo un no autenticado, para así
	// evitar duplicar el test en el de no autenticado y en este.

	// 5.2 Browse the list of satellites and navigate to the platforms that they
	// broadcast

	@Test
	public void listSatellitesTest() {

		final Object testingData[][] = {
				{	// Positivo unauthenticated
					null, null
				}, { // Positivo con user
					"user1", null
				}
		};

		for (int i = 0; i < testingData.length; i++)
			this.templateListSatellitesTest((String) testingData[i][0], (Class<?>) testingData[i][1]);
	}

	protected void templateListSatellitesTest(final String actor, final Class<?> expected) {
		Class<?> caught;

		caught = null;
		try {
			if(actor != null){
				super.authenticate(actor);
			}

			final Collection<Satellite> satellites = this.satelliteService
					.findAll();

			final Collection<Platform> platforms = new ArrayList<>();

			for (Satellite satellite : satellites) {
				platforms.addAll(satellite.getPlatforms());
			}

			this.unauthenticate();
			
			Assert.notNull(satellites);

		} catch (final Throwable oops) {
			caught = oops.getClass();
		}
		super.checkExceptions(expected, caught);
	}

	// 5.3 Browse the list of platforms and navigate to the satellites that
	// broadcast them

	@Test
	public void listPlatformTest() {

		final Object testingData[][] = {
				{	// Positivo unauthenticated
					null, null
				}, { // Positivo con user
					"user1", null
				}
		};

		for (int i = 0; i < testingData.length; i++)
			this.templateListPlatformTest((String) testingData[i][0], (Class<?>) testingData[i][1]);
	}

	protected void templateListPlatformTest(final String actor, final Class<?> expected) {
		Class<?> caught;

		caught = null;
		try {
			if(actor != null){
				super.authenticate(actor);
			}
			
			final Collection<Platform> platforms = this.platformService
					.findAll();

			final Collection<Satellite> satellites = new ArrayList<>();

			for (Platform platform : platforms) {
				satellites.add(platform.getSatellite());
			}

			this.unauthenticate();
			Assert.notNull(platforms);

		} catch (final Throwable oops) {
			caught = oops.getClass();
		}
		super.checkExceptions(expected, caught);
	}

	// 5.4 Search for satellites and platforms using a single key word that must
	// be contained in their names or descriptions

	@Test
	public void searchSatellitesAndPlatformTest() {

		final Object testingData[][] = {
				{	// Positivo unauthenticated
					null, null
				}, { // Positivo con user
					"user1", null
				}
		};

		for (int i = 0; i < testingData.length; i++)
			this.templateSearchSatellitesAndPlatformTest((String) testingData[i][0], (Class<?>) testingData[i][1]);
	}

	protected void templateSearchSatellitesAndPlatformTest(final String actor, final Class<?> expected) {
		Class<?> caught;

		caught = null;
		try {
			if(actor != null){
				super.authenticate(actor);
			}
			
			final Collection<Platform> platforms = this.platformService
					.searchPlatform("Platform");

			final Collection<Satellite> satellites = this.satelliteService
					.searchSatellite("Satellite");
			
			this.unauthenticate();

			Assert.notNull(satellites);
			Assert.notNull(platforms);

		} catch (final Throwable oops) {
			caught = oops.getClass();
		}
		super.checkExceptions(expected, caught);
	}
	
	// 6.2 Manage his or her antennas, which includes registering them, editing them, deleting them, and listing them. 
	// The information about an antenna is private to the actor who registers it
	
			// REGISTERING THEM
	
	@Test
	public void registerAntennasTest() {

		final Object testingData[][] = {
				{ // Positivo con user
					"user1", 123456, "model-A", 20.5, 30.5, 90.0, null
				}
		};

		for (int i = 0; i < testingData.length; i++)
			this.templateRegisterAntennasTest((String) testingData[i][0], (Integer) testingData[i][1], (String) testingData[i][2], (Double) testingData[i][3], (Double) testingData[i][4], (Double) testingData[i][5], (Class<?>) testingData[i][6]);
	}
	
	protected void templateRegisterAntennasTest(final String actor, final Integer serialNumber, final String model, final Double azimuth, final Double elevation, final Double quality, final Class<?> expected) {
		Class<?> caught;

		caught = null;
		try {
			if(actor != null){
				super.authenticate(actor);
			}
			
			Antenna antenna = this.antennaService.create();
			
			antenna.setSerialNumber(serialNumber);
			antenna.setModel(model);
			antenna.setAzimuth(azimuth);
			antenna.setElevation(elevation);
			antenna.setQuality(quality);
			
			int satelliteId = this.getEntityId("satellite1");
			Satellite satellite = this.satelliteService.findOne(satelliteId);
			
			antenna.setSatellite(satellite);
			
			Antenna savedAntenna = this.antennaService.save(antenna);
			
			this.unauthenticate();
			
			Assert.notNull(savedAntenna);


		} catch (final Throwable oops) {
			caught = oops.getClass();
		}
		super.checkExceptions(expected, caught);
	}
	
				// EDITING THEM
	
	@Test
	public void editingAntennasTest() {

		final Object testingData[][] = {
				{ // Positivo con user
					"user1", "model-A", null
				}, { // Negativo con user distinto a quien creó la antenna
					"user2", "model-A", IllegalArgumentException.class
				} , { // Negativo con usuario sin autenticar
					null, "model-A", IllegalArgumentException.class
				}
		};

		for (int i = 0; i < testingData.length; i++)
			this.templateEditingAntennasTest((String) testingData[i][0], (String) testingData[i][1], (Class<?>) testingData[i][2]);
	}
	
	protected void templateEditingAntennasTest(final String actor, final String model, final Class<?> expected) {
		Class<?> caught;

		caught = null;
		try {
			if(actor != null){
				super.authenticate(actor);
			}
			
			int antennaId = this.getEntityId("antenna1");
			Antenna antenna = this.antennaService.findOne(antennaId);
			antenna.setModel(model);
			
			Antenna savedAntenna = this.antennaService.save(antenna);
			
			this.unauthenticate();
			
			Assert.notNull(savedAntenna);


		} catch (final Throwable oops) {
			caught = oops.getClass();
		}
		super.checkExceptions(expected, caught);
	}
	
				// LISTING THEM
	
	@Test
	public void listAntennaTest() {

		final Object testingData[][] = {
				{ // Positivo con user
					"user1", null
				}, { // Negativo con user que no corresponde a esas antennas
					"user2", IllegalArgumentException.class
				}
		};

		for (int i = 0; i < testingData.length; i++)
			this.templateListAntennaTest((String) testingData[i][0], (Class<?>) testingData[i][1]);
	}

	protected void templateListAntennaTest(final String actor, final Class<?> expected) {
		Class<?> caught;

		caught = null;
		try {
			String userLogado = "user1";
			
			super.authenticate(userLogado);
			
			int actorId = this.getEntityId(actor);

			final Collection<Antenna> antennas = this.antennaService.findAntennasByUser(actorId);

			this.unauthenticate();
			
			Assert.notNull(antennas);

		} catch (final Throwable oops) {
			caught = oops.getClass();
		}
		super.checkExceptions(expected, caught);
	}
	
	// 6.3 Subscribe to a platform for a period of time by providing a valid credit card. The
	// subscription results in a key code that must be provided by the system
	
	@Test
	public void subscribePlatformTest() {

		final Object testingData[][] = {
				{ // Positivo con user
					"user1", "platform3", null
				} , { // Negativo con admin
					"admin", "platform3", IllegalArgumentException.class
				} , { // Negativo con subscripción en platform que ya tiene el user 1
					"user1", "platform1", IllegalArgumentException.class
				}
		};

		for (int i = 0; i < testingData.length; i++)
			this.templateSubscribePlatformTest((String) testingData[i][0], (String) testingData[i][1], (Class<?>) testingData[i][2]);
	}
	
	protected void templateSubscribePlatformTest(final String actor, final String platformEntity, final Class<?> expected) {
		Class<?> caught;

		caught = null;
		try {
			if(actor != null){
				super.authenticate(actor);
			}
			
			int platformId = this.getEntityId(platformEntity);
			Platform platform = this.platformService.findOne(platformId);
			
			Subscription subscription = this.subscriptionService.create();
			subscription.setPlatform(platform);
			
			CreditCard creditCard = new CreditCard();
			creditCard.setBrandName("Master Card");
			creditCard.setCvv(123);
			creditCard.setExpirationMonth(12);
			creditCard.setExpirationYear(25);
			creditCard.setHolderName("BBVA");
			creditCard.setNumber("5555555555554444");
			
			subscription.setCreditCard(creditCard);
			
			int year = 2027;
		    int month = 12;
		    int day = 20;
		    
		    String dateString = year + "/" + month + "/" + day;
			Date date = new Date();
			
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
			date = formatter.parse(dateString);
			
			subscription.setEndDate(date);
			
			Subscription savedSubscription = this.subscriptionService.save(subscription);
			
			this.unauthenticate();
			
			Assert.notNull(savedSubscription);


		} catch (final Throwable oops) {
			caught = oops.getClass();
		}
		super.checkExceptions(expected, caught);
	}
	
	// 15.2 List the tutorials and display them, including their comments
	
	@Test
	public void listTutorialTest() {

		final Object testingData[][] = {
				{ // Positivo con user
					"user1", null
				}
		};

		for (int i = 0; i < testingData.length; i++)
			this.templateListTutorialTest((String) testingData[i][0], (Class<?>) testingData[i][1]);
	}

	protected void templateListTutorialTest(final String actor, final Class<?> expected) {
		Class<?> caught;

		caught = null;
		try {
			if(actor != null){
				super.authenticate(actor);
			}
			
			final Collection<Tutorial> tutorials = this.tutorialService
					.findAll();

			final Collection<Comment> comments = new ArrayList<>();
			
			for (Tutorial tutorial: tutorials) {
				comments.addAll(tutorial.getComments());
			}

			this.unauthenticate();
			Assert.notNull(tutorials);
			Assert.notNull(comments);

		} catch (final Throwable oops) {
			caught = oops.getClass();
		}
		super.checkExceptions(expected, caught);
	}
	
	// 15.3 Publish a tutorial and edit it
	
				// PUBLISH IT
	
	@Test
	public void publishTutorialTest() {

		final Object testingData[][] = {
				{ // Positivo con user
					"user1", "Titulo 1", "Texto 1", "http://www.google.es", null
				} , { // Negativo con Admin
					"admin", "Titulo 1", "Texto 1", "http://www.google.es", IllegalArgumentException.class
				} , { // Negativo con no autenticado
					null, "Titulo 1", "Texto 1", "http://www.google.es", IllegalArgumentException.class
				}
		};

		for (int i = 0; i < testingData.length; i++)
			this.templatePublishTutorialTest((String) testingData[i][0], (String) testingData[i][1], (String) testingData[i][2], (String) testingData[i][3], (Class<?>) testingData[i][4]);
	}
	
	protected void templatePublishTutorialTest(final String actor, final String title, final String text, final String pictures, final Class<?> expected) {
		Class<?> caught;

		caught = null;
		try {
			if(actor != null){
				super.authenticate(actor);
			}
			
			Tutorial tutorial = this.tutorialService.create();
			
			tutorial.setTitle(title);
			tutorial.setText(text);
			tutorial.setPictures(pictures);
			
			Tutorial savedTutorial = this.tutorialService.save(tutorial);
			
			this.unauthenticate();
			
			Assert.notNull(savedTutorial);


		} catch (final Throwable oops) {
			caught = oops.getClass();
		}
		super.checkExceptions(expected, caught);
	}
	
				// EDITING IT
	
	@Test
	public void editingTutorialTest() {

		final Object testingData[][] = {
				{ // Positivo con user
					"user1", "new title", null
				}, { // Negativo con user distinto a quien creó el tutorial
					"user2", "new title", IllegalArgumentException.class
				} , { // Negativo con usuario sin autenticar
					null, "new title", IllegalArgumentException.class
				}
		};

		for (int i = 0; i < testingData.length; i++)
			this.templateEditingTutorialTest((String) testingData[i][0], (String) testingData[i][1], (Class<?>) testingData[i][2]);
	}
	
	protected void templateEditingTutorialTest(final String actor, final String title, final Class<?> expected) {
		Class<?> caught;

		caught = null;
		try {
			if(actor != null){
				super.authenticate(actor);
			}
			
			int tutorialId = this.getEntityId("tutorial1");
			Tutorial tutorial = this.tutorialService.findOne(tutorialId);
			tutorial.setTitle(title);
			
			Tutorial savedTutorial = this.tutorialService.save(tutorial);
			
			this.unauthenticate();
			
			Assert.notNull(savedTutorial);


		} catch (final Throwable oops) {
			caught = oops.getClass();
		}
		super.checkExceptions(expected, caught);
	}
	
	// 15.4 Post a comment to a tutorial
	
	@Test
	public void postCommentTest() {

		final Object testingData[][] = {
				{ // Positivo con user
					"user1", "Titulo 1", "Texto 1", "http://www.google.es", null
				} , { // Negativo con Admin
					"admin", "Titulo 1", "Texto 1", "http://www.google.es", IllegalArgumentException.class
				} , { // Negativo con no autenticado
					null, "Titulo 1", "Texto 1", "http://www.google.es", IllegalArgumentException.class
				}
		};

		for (int i = 0; i < testingData.length; i++)
			this.templatePostCommentTest((String) testingData[i][0], (String) testingData[i][1], (String) testingData[i][2], (String) testingData[i][3], (Class<?>) testingData[i][4]);
	}
	
	protected void templatePostCommentTest(final String actor, final String title, final String text, final String pictures, final Class<?> expected) {
		Class<?> caught;

		caught = null;
		try {
			if(actor != null){
				super.authenticate(actor);
			}
			
			int tutorialId = this.getEntityId("tutorial1");
		
			Tutorial tutorial = this.tutorialService.findOne(tutorialId);
			
			Comment comment = this.commentService.create();
			
			comment.setTitle(title);
			comment.setText(text);
			comment.setPictures(pictures);
			comment.setTutorial(tutorial);
			
			Comment savedComment = this.commentService.save(comment);
			
			this.unauthenticate();
			
			Assert.notNull(savedComment);


		} catch (final Throwable oops) {
			caught = oops.getClass();
		}
		super.checkExceptions(expected, caught);
	}
	
	// 20.1 Reply to any comment that he or she can display
	
	@Test
	public void postReplyTest() {

		final Object testingData[][] = {
				{ // Positivo con user
					"user1", "Titulo 1", "Texto 1", "http://www.google.es", null
				} , { // Negativo con Admin
					"admin", "Titulo 1", "Texto 1", "http://www.google.es", IllegalArgumentException.class
				} , { // Negativo con no autenticado
					null, "Titulo 1", "Texto 1", "http://www.google.es", IllegalArgumentException.class
				}
		};

		for (int i = 0; i < testingData.length; i++)
			this.templatePostReplyTest((String) testingData[i][0], (String) testingData[i][1], (String) testingData[i][2], (String) testingData[i][3], (Class<?>) testingData[i][4]);
	}
	
	protected void templatePostReplyTest(final String actor, final String title, final String text, final String pictures, final Class<?> expected) {
		Class<?> caught;

		caught = null;
		try {
			if(actor != null){
				super.authenticate(actor);
			}
			
			int commentId = this.getEntityId("comment1");
			int tutorialId = this.getEntityId("tutorial1");
		
			Comment comment = this.commentService.findOne(commentId);
			Tutorial tutorial = this.tutorialService.findOne(tutorialId);
			
			Comment reply = this.commentService.create();
			
			reply.setTitle(title);
			reply.setText(text);
			reply.setPictures(pictures);
			reply.setTutorial(tutorial);
			reply.setCommentParent(comment);
			
			comment.getReplies().add(reply);
			
			Comment savedReply = this.commentService.save(reply);
			Comment savedComment = this.commentService.save(comment);
			
			this.unauthenticate();
			
			Assert.notNull(savedReply);
			Assert.notNull(savedComment);


		} catch (final Throwable oops) {
			caught = oops.getClass();
		}
		super.checkExceptions(expected, caught);
	}
}
