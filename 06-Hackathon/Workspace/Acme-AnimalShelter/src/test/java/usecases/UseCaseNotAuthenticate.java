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
import services.VoluntaryService;
import utilities.AbstractTest;
import domain.Center;
import domain.Client;
import domain.Pet;
import domain.Voluntary;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring/junit.xml" })
@Transactional
public class UseCaseNotAuthenticate extends AbstractTest{
	
	@Autowired
	private PetService petService;
	@Autowired
	private CenterService centerService;
	@Autowired
	private EventService eventService;
	@Autowired
	private VoluntaryService voluntaryService;
	@Autowired
	private ClientService clientService;

	/*
	 * Caso de uso: Not authenticate->Ver la lista de animales en espera de adopción. 11a
	 */
	@Test
	public void listPetWaitingAdoptionTest() {

		final Object testingData[][] = { {
				// Positive
				 null }
				// Negative: not negative case
			 };

		for (int i = 0; i < testingData.length; i++)
			this.templateListPetTest(i, 
					(Class<?>) testingData[i][0]);
	}

	protected void templateListPetTest(final Integer i,
			 final Class<?> expected) {
		Class<?> caught;

		caught = null;
		try {

			final Collection<Pet> listPet = this.petService.findPetsWaitingAdoption();

			Assert.notNull(listPet);

		} catch (final Throwable oops) {
			caught = oops.getClass();
		}
		super.checkExceptions(expected, caught);
	}
	
	
	/*
	 * Caso de uso: Veterinary->Registrarse en el sistema como cliente o voluntario. 11b
	 */

	@Test
	  public void RegisterAsUserTest() {
	    final Object testingData[][] = {

	        // positivo,alguien se registra en el sistema como user

	        { "voluntary6", "surname", "email@email.com", "671910556", "address"
	            , null },
//	            { "voluntary1", "surname", "email@email.com", "671910556", "address"
//		            , null },

	        // negativo, alguien se registra en el sistema como user pero
	        // sin nombre
//	        { "", "surname", "email@gmail.com", "671910556", "address",
//	            ConstraintViolationException.class },

	    };

	    for (int i = 0; i < testingData.length; i++) {
	      this.templateRegisterAsUser((String) testingData[i][0],
	          (String) testingData[i][1], (String) testingData[i][2],
	          (String) testingData[i][3], (String) testingData[i][4],
	          (Class<?>) testingData[i][5]);
	    }
	  }

	  private void templateRegisterAsUser(String name, String surname,
	      String email, String phoneNumber, String address, Class<?> expected) {
		  
	    Class<?> caught;
	    caught = null;

	    try {
	      if (name.equals("client6")) {
	    	  
	        Client client= this.clientService.create();
		      client.setAddress(address);
		      client.setEmail(email);
		      client.setName(name);
		      client.setPhoneNumber(phoneNumber);
		      client.setSurname(surname);
		      client.getUserAccount().setPassword(name);

		      Client saved = this.clientService.save(client);
		     Assert.notNull(saved);
		      
		      
		      this.clientService.flush();
		      
	      }else if(name.equals("voluntary6")){
	    	  super.unauthenticate();
	    	  Voluntary voluntary= this.voluntaryService.create();
	    	  voluntary.setAddress(address);
	    	  voluntary.setEmail(email);
	    	  voluntary.setName(name);
		      voluntary.setPhoneNumber(phoneNumber);
		      voluntary.setSurname(surname);
		      voluntary.getUserAccount().setPassword(name);

		      Voluntary saved = this.voluntaryService.save(voluntary);
		      Collection<Voluntary> voluntarios=voluntaryService.findAll();
		      System.out.println(voluntarios);
		      Assert.notNull(saved);
		      
		      
		      this.voluntaryService.flush();
	      }
	      
	      

	    } catch (final Throwable oops) {
System.out.println(oops.getMessage());
	      caught = oops.getClass();
	      
	    }
	    super.checkExceptions(expected, caught);

	  }
	
	
	/*
	 * Caso de uso: Not authenticate->Ver la lista de centros con su foto y descripción.. 11d
	 */
	@Test
	public void listCenterTest() {

		final Object testingData[][] = { {
				// Positive
				 null }
				// Negative: not negative case
			 };

		for (int i = 0; i < testingData.length; i++)
			this.templateListCenterTest(i, 
					(Class<?>) testingData[i][0]);
	}

	protected void templateListCenterTest(final Integer i,
			 final Class<?> expected) {
		Class<?> caught;

		caught = null;
		try {

			final Collection<Center> listCenter = this.centerService.findAll();

			Assert.notNull(listCenter);

		} catch (final Throwable oops) {
			caught = oops.getClass();
		}
		super.checkExceptions(expected, caught);
	}
	
	
	/*
	 * Caso de uso: Not authenticate->Listar los eventos que se han publicado y no hayan finalizado y ver su página de promoción. 11e
	 */
	@Test
	public void listEventNotFinalisedTest() {

		final Object testingData[][] = { {
				// Positive
				 null }
				// Negative: se comprueba dentro del metodo que no tenga ninguna fecha pasada
			 };

		for (int i = 0; i < testingData.length; i++)
			this.templateListEventTest(i, 
					(Class<?>) testingData[i][0]);
	}

	protected void templateListEventTest(final Integer i,
			 final Class<?> expected) {
		Class<?> caught;

		caught = null;
		try {

			final Collection<domain.Event> listEvent = this.eventService.findEventNotEnd();

			Date actual= new Date();
			for(domain.Event e: listEvent){
				Assert.notNull(e.getEndDate().after(actual));
			}
			
			
			Assert.notNull(listEvent);

		} catch (final Throwable oops) {
			caught = oops.getClass();
		}
		super.checkExceptions(expected, caught);
	}
	
}
