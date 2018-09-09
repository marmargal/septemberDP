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

import services.AntennaService;
import services.PlatformService;
import services.SatelliteService;
import utilities.AbstractTest;
import domain.Antenna;
import domain.Platform;
import domain.Satellite;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring/junit.xml" })
@Transactional
public class AuthenticatedTest extends AbstractTest {

	@Autowired
	private SatelliteService satelliteService;

	@Autowired
	private PlatformService platformService;
	
	@Autowired
	private AntennaService antennaService;
	
	// En los tests de esta clase se han añadido en conjunto tanto los que puede hacer un autenticado, ya pudiera hacerlo un no autenticado como la
	// comprobación con cualquiera de los roles posibles.

	// 5.2 Browse the list of satellites and navigate to the platforms that they
	// broadcast

	@Test
	public void listSatellitesTest() {

		final Object testingData[][] = {
				{	// Positivo unauthenticated
					null, null
				}, { // Positivo con admin
					"admin", null
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
				}, { // Positivo con admin
					"admin", null
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
				}, { // Positivo con admin
					"admin", null
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
	
	@Test
	public void registerAntennasTest() {

		final Object testingData[][] = {
				{ // Positivo con admin
					"admin", 123456, "model-A", 20.5, 30.5, 90.0, null
				}, { // Positivo con user
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
			
			this.unauthenticate();


		} catch (final Throwable oops) {
			caught = oops.getClass();
		}
		super.checkExceptions(expected, caught);
	}
}
