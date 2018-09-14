package usecases;

import java.util.Collection;
import java.util.Date;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Assert;

import services.CenterService;
import services.ClientService;
import services.EventService;
import services.PetService;
import utilities.AbstractTest;
import domain.Center;
import domain.Client;
import domain.Pet;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring/junit.xml" })
@Transactional
public class UseCaseAuthenticate extends AbstractTest {

	@Autowired
	private PetService petService;
	@Autowired
	private CenterService centerService;
	@Autowired
	private EventService eventService;

	@Autowired
	private ClientService clientService;

	/*
	 * Caso de uso: authenticate->Ver la lista de animales en espera de
	 * adopción. 11a
	 */
	@Test
	public void listPetWaitingAdoptionTest() {

		final Object testingData[][] = { {
		// Positive
		null }
		// Negative: negative case
		};

		for (int i = 0; i < testingData.length; i++)
			this.templateListPetTest(i, (Class<?>) testingData[i][0]);
	}

	private void templateListPetTest(final Integer i, final Class<?> expected) {
		Class<?> caught;

		caught = null;
		try {
			super.authenticate("client1");
			final Collection<Pet> listPet = this.petService
					.findPetsWaitingAdoption();

			Assert.notNull(listPet);
			super.unauthenticate();
		} catch (final Throwable oops) {
			caught = oops.getClass();
		}
		super.checkExceptions(expected, caught);
	}

	/*
	 * Caso de uso: authenticate->Ver la lista de centros con su foto y
	 * descripción.. 11d
	 */
	@Test
	public void listCenterTest() {

		final Object testingData[][] = { {
		// Positive
		null }
		// Negative: negative case
		};

		for (int i = 0; i < testingData.length; i++)
			this.templateListCenterTest(i, (Class<?>) testingData[i][0]);
	}

	private void templateListCenterTest(final Integer i, final Class<?> expected) {
		Class<?> caught;

		caught = null;
		try {
			super.authenticate("client1");

			final Collection<Center> listCenter = this.centerService.findAll();

			Assert.notNull(listCenter);
			super.unauthenticate();

		} catch (final Throwable oops) {
			caught = oops.getClass();
		}
		super.checkExceptions(expected, caught);
	}

	/*
	 * Caso de uso: authenticate->Listar los eventos que se han publicado y no
	 * hayan finalizado y ver su página de promoción. 11e
	 */
	@Test
	public void listEventNotFinalisedTest() {

		final Object testingData[][] = { {
		// Positive
		null }
		// Negative: se comprueba dentro del metodo que no tenga ninguna fecha
		// pasada
		};

		for (int i = 0; i < testingData.length; i++)
			this.templateListEventTest(i, (Class<?>) testingData[i][0]);
	}

	private void templateListEventTest(final Integer i, final Class<?> expected) {
		Class<?> caught;

		caught = null;
		try {
			super.authenticate("client1");

			final Collection<domain.Event> listEvent = this.eventService
					.findEventNotEnd();

			Date actual = new Date();
			for (domain.Event e : listEvent) {
				Assert.notNull(e.getEndDate().after(actual));
			}

			Assert.notNull(listEvent);
			super.unauthenticate();

		} catch (final Throwable oops) {
			caught = oops.getClass();
		}
		super.checkExceptions(expected, caught);
	}

	@Test
	public void editPersonalDataTest() {

		final Object testingData[][] = { {
		// Positive
		null } };

		for (int i = 0; i < testingData.length; i++)
			this.templateEditPersonalDataTest(i, (Class<?>) testingData[i][0]);
	}

	private void templateEditPersonalDataTest(final Integer i,
			final Class<?> expected) {
		Class<?> caught;

		caught = null;
		try {
			super.authenticate("client1");
			Client client = this.clientService.findByPrincipal();
			client.setAddress("edit");

			this.clientService.save(client);
			super.unauthenticate();

		} catch (final Throwable oops) {
			caught = oops.getClass();
		}
		super.checkExceptions(expected, caught);
	}

}
