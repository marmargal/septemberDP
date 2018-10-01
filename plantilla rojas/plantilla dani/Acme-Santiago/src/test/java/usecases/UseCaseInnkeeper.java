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

import services.AmenityService;
import services.BridService;
import services.HikeService;
import services.InnService;
import services.InnkeeperService;
import services.RegistrytService;
import services.UserService;
import utilities.AbstractTest;
import domain.Amenity;
import domain.Inn;
import domain.Registry;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring/junit.xml" })
@Transactional
public class UseCaseInnkeeper extends AbstractTest {

	@Autowired
	private InnService innService;

	@Autowired
	private InnkeeperService innkeeperService;

	@Autowired
	private UserService userService;

	@Autowired
	private HikeService hikeService;

	@Autowired
	private RegistrytService registrytService;

	@Autowired
	private AmenityService amenityService;


	/*
	 * 10. An actor who is authenticated as an innkeeper must be able to: 1.
	 * Certify that a user has stayed in an inn that he or she manages in the
	 * destination city of a particular hike on a particular day.
	 * 
	 * como el registro tiene la fecha, el usuario y el hike cada vez que este
	 * hace un registro certifica que el usuario ha estado en esa fecha, lugar y
	 * para ese hike
	 */

	
	@Test
	public void RegistryInnkeeperTest() {
		final Object testingData[][] = {
				// positivo, el innkeeper1 hace un registro en un inn suyo
				{ "innkeeper1", "02/02/2010", "inn1", null },
				// negativo, el innkeeper1 hace un registro en un inn suyo con
				// la fecha mal
				{ "innkeeper1", "02/02/2019", "inn1",
						IllegalArgumentException.class },
				// negativo, el user1 hace un registro en un inn
				{ "user1", "02/02/2010", "inn1", IllegalArgumentException.class } };

		for (int i = 0; i < testingData.length; i++) {
			this.templateRegistryInnkeeper((String) testingData[i][0],
					(String) testingData[i][1], (String) testingData[i][2],
					(Class<?>) testingData[i][3]);

		}
	}

	private void templateRegistryInnkeeper(String nameInnkeeper,
			String dateName, String innName, Class<?> expected) {
		Class<?> caught;
		caught = null;

		try {
			this.authenticate(nameInnkeeper);
			Registry registry = this.registrytService.create();
			final DateFormat format = new SimpleDateFormat("dd/MM/yyyy");
			final Date date = format.parse(dateName);
			registry.setDate(date);
			registry.setUser(userService.findOne(super.getEntityId("user1")));
			registry.setHike(hikeService.findOne(super.getEntityId("hike1")));
			Inn inn = this.innService.findOne(super.getEntityId(innName));
			Collection<Registry> registries = inn.getRegistries();
			registries.add(registry);
			inn.setRegistries(registries);
			this.innService.save(inn);
			Assert.isTrue(this.innService.findOne(inn.getId()).getRegistries()
					.contains(registry));
			this.unauthenticate();
		} catch (final Throwable oops) {
			caught = oops.getClass();
		}
		super.checkExceptions(expected, caught);

	}

	// suit de test funcionales, en la primera parte (amenitiesTest) hay 6 y en
	// la segunda parte (amenities2Test) hay 4. la primera parte son los create
	// y edit y en la segunada los delete y list
	/*
	 * 17. An actor who is authenticated as an innkeeper must be able to: 1.
	 * Manage the amenities that are offered by the inns that he or she manages,
	 * which includes creating them, editing them, deleting them, and listing
	 * them.
	 */

	@Test
	public void amenitiesTest() {
		final Object testingData[][] = {

				// caso positivo, un inkeeper edita un amenity suyo
				{ "innkeeper1", "edit", "description", "https://www.google.es",
						"inn1", null },

				// caso positivo, un inkeeper crea un amenity
				{ "innkeeper1", "name", "description", "https://www.google.es",
						"inn1", null },

				// caso negativo, un user intenta crear un amenity
				{ "user1", "name", "description", "https://www.google.es",
						"inn1", IllegalArgumentException.class },
				// caso negativo, un inkeeper crea un amenity sin nombre
				{ "innkeeper1", "", "description", "https://www.google.es",
						"inn1", ConstraintViolationException.class },

				// caso negativo, un user edita un amenity
				{ "user1", "edit", "description", "https://www.google.es",
						"inn1", ConstraintViolationException.class },

				// caso negativo, un inkeeper edita un amenity que no es suyo
				{ "innkeeper1", "edit", "innkeeper2", "https://www.google.es",
						"inn1", ConstraintViolationException.class },

		};
		for (int i = 0; i < testingData.length; i++) {
			this.templateAmenities((String) testingData[i][0],
					(String) testingData[i][1], (String) testingData[i][2],
					(String) testingData[i][3], (String) testingData[i][4],
					(Class<?>) testingData[i][5]);
		}

	}

	private void templateAmenities(String innkeeperName, String amenityName,
			String description, String picture, String innName,
			Class<?> expected) {
		Class<?> caught;
		caught = null;
		super.unauthenticate();
		try {
			super.authenticate(innkeeperName);
			Amenity amenity = null;
			if (amenityName.equals("edit")) {
				ArrayList<Amenity> amenities = new ArrayList<>();
				if (description.equals("innkeeper2")) {
					amenities.addAll(this.innkeeperService.findOne(
							super.getEntityId(description)).getAmenities());
					amenity = amenities.get(0);
					amenity.setName(amenityName);
				} else {
					amenities.addAll(this.innkeeperService.findByPrincipal()
							.getAmenities());
					amenity = amenities.get(0);
					amenity.setName(amenityName);
				}

			} else {
				amenity = this.amenityService.create();
				amenity.setDescription(description);
				amenity.setInn(this.innService.findOne(super
						.getEntityId(innName)));
				amenity.setName(amenityName);
				Collection<String> pictures = new ArrayList<>();
				pictures.add(picture);
				amenity.setPictures(pictures);
			}
			Amenity saved = this.amenityService.save(amenity);
			if (amenityName.equals("edit")) {
				Assert.isTrue(this.amenityService.findOne(saved.getId())
						.getName().equals(amenityName));
			} else {
				Assert.isTrue(this.amenityService.findAll().contains(saved));
			}
			this.amenityService.flush();
			this.unauthenticate();
		} catch (final Throwable oops) {
			caught = oops.getClass();
		}
		super.checkExceptions(expected, caught);
	}

	/*
	 * hago un segundo test que es la continuación del anteior con el delete y
	 * el list de amenity con los 4 casos de prueba que quedan, esto es porque
	 * el primero se me estaba quedando muy lioso con tantos if
	 */

	@Test
	public void amenities2Test() {
		final Object testingData[][] = {

				// caso positivo, un inkeeper list sus amenitys
				{ "innkeeper1", "list", "description", "https://www.google.es",
						"inn1", null },
				// caso negativo, un user list
				{ "user1", "list", "description", "https://www.google.es",
						"inn1", IllegalArgumentException.class },
				// caso positivo, un inkeeper borra un amenity
				{ "innkeeper1", "delete", "description",
						"https://www.google.es", "inn1", null },
				// caso negativo, un inkeeper borra un amenity
				{ "innkeeper1", "delete", "description",
						"https://www.google.es", "inn1",
						IllegalArgumentException.class }, };
		for (int i = 0; i < testingData.length; i++) {
			this.templateAmenities2((String) testingData[i][0],
					(String) testingData[i][1], (String) testingData[i][2],
					(String) testingData[i][3], (String) testingData[i][4],
					(Class<?>) testingData[i][5]);
		}
	}

	private void templateAmenities2(String innkeeperName, String amenityName,
			String description, String picture, String innName,
			Class<?> expected) {
		Class<?> caught;
		caught = null;
		super.unauthenticate();
		try {
			super.authenticate(innkeeperName);
			if (amenityName.equals("delete")) {
				Amenity amenity = this.amenityService.findOne(super
						.getEntityId("amenity1"));
				this.amenityService.delete(amenity);
			} else {
				if (innkeeperName.equals("user1")) {
					this.amenityService.findAll();
				} else {
					for (Inn inn : this.innkeeperService.findByPrincipal()
							.getInns()) {
						this.amenityService.findAmenitiesByInn(inn.getId());

					}
				}
			}
			this.unauthenticate();
		} catch (final Throwable oops) {
			caught = oops.getClass();
		}
		super.checkExceptions(expected, caught);
	}
}
