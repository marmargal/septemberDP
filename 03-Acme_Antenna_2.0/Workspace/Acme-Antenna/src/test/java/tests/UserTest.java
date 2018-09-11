package tests;

import java.util.Collection;
import java.util.Date;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import services.AntennaService;
import services.HandyworkerService;
import services.RequestService;
import services.UserService;
import utilities.AbstractTest;
import domain.Antenna;
import domain.CreditCard;
import domain.Handyworker;
import domain.Request;
import domain.User;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring/junit.xml" })
@Transactional
public class UserTest extends AbstractTest {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private RequestService requestService;
	
	@Autowired
	private AntennaService antennaService;
	
	@Autowired
	private HandyworkerService handyworkerService;
	
	//4.1 Make a request for maintenance to a handyworker.
	
	@Test
	public void makeRequestTest() {
		final Object testingData[][] = {
		// caso positivo, un user crea un request.
				{
					"holderName", "brandName","number", 10, 19, 123,
					"Description", new Date(System.currentTimeMillis() + 1000), "Result", "user1", "antenna1", "handyworker1", null 
				},
				
		// caso negativo, un agent crea un request.
				{ 
					"holderName", "brandName","number", 10, 19, 123,
					"Description", new Date(System.currentTimeMillis() + 1000), "Result", "agent1", "antenna1", "handyworker1", IllegalArgumentException.class 
				}
		};
		
		
		for (int i = 0; i < testingData.length; i++)
			this.templateMakeRequestTest((String) testingData[i][0],(String) testingData[i][1],
								 (String) testingData[i][2],(Integer) testingData[i][3],
								 (Integer) testingData[i][4],(Integer) testingData[i][5],
								 (String) testingData[i][6],(Date) testingData[i][7],
								 (String) testingData[i][8],(String) testingData[i][9],
								 (String) testingData[i][10],(String) testingData[i][11],
								 (Class<?>) testingData[i][12]);
	}

	private void templateMakeRequestTest(String holderName, String brandName, String number, Integer expirationMonth, 
			Integer expirationYear, Integer cvv, String description, Date finishMoment, String result, String nameActor, String antennaName, 
			String handyworkerName, Class<?> expected) {

		Class<?> caught;
		caught = null;
		try {
			this.authenticate(nameActor);
			
			final int antennalId = this.getEntityId(antennaName);
			final Antenna antenna = this.antennaService.findOne(antennalId);
			
			final int handyworkerId = this.getEntityId(handyworkerName);
			final Handyworker handyworker = this.handyworkerService.findOne(handyworkerId);
			
			Request request = this.requestService.create();
			
			CreditCard cc = new CreditCard();
			cc.setHolderName(holderName);
			cc.setBrandName(brandName);
			cc.setNumber(number);
			cc.setExpirationMonth(expirationMonth);
			cc.setExpirationYear(expirationYear);
			cc.setCvv(cvv);
			
			request.setCreditCard(cc);
			request.setDescription(description);
			request.setFinishMoment(finishMoment);
			request.setResult(result);
			
			request.setAntenna(antenna);
			request.setHandyworker(handyworker);
			
			this.requestService.save(request);
			
			super.unauthenticate();
			
			this.requestService.flush();

		} catch (final Throwable oops) {

			caught = oops.getClass();
		}
		super.checkExceptions(expected, caught);
	}

	
	//4.2 List his or her already-serviced requests.
	@Test
	public void listRequestsAlreadyServicedTest() {
		final Object testingData[][] = {
			// caso positivo, alguien logueado como user ve su lista de request.
			{
				"user1", null 
			},
			
			// caso negativo, alguien logueado como agent ve la lista de request de un user.
			{
				"agent1", IllegalArgumentException.class 
			}
		};
	
		for (int i = 0; i < testingData.length; i++)
			this.templateListsRequest((String) testingData[i][0],(Class<?>) testingData[i][1]);
		}

	@SuppressWarnings("unused")
	private void templateListsRequest(String nameActor, Class<?> expected) {
		Class<?> caught;
		caught = null;
		try {
			this.authenticate(nameActor);
			
			this.userService.checkAuthority();
			User user = this.userService.findByPrincipal();
			Collection<Request> requests = this.requestService.alreadyServicedRequest(user.getId());
			
			super.unauthenticate();
			
		} catch (final Throwable oops) {

			caught = oops.getClass();
		}
		super.checkExceptions(expected, caught);
	}
	
	
	//4.3 List his or her not-yet-serviced requests.
	@Test
	public void listRequestsnotYetServicedTest() {
		final Object testingData[][] = {
			// caso positivo, alguien logueado como user ve su lista de request.
			{
				"user1", null 
			},
			
			// caso negativo, alguien logueado como agent ve la lista de request de un user.
			{
				"agent1", IllegalArgumentException.class 
			}
		};
	
		for (int i = 0; i < testingData.length; i++)
			this.templateListsRequest((String) testingData[i][0],(Class<?>) testingData[i][1]);
		}

	@SuppressWarnings("unused")
	private void templatelistRequestsnotYetServiced(String nameActor, Class<?> expected) {
		Class<?> caught;
		caught = null;
		try {
			this.authenticate(nameActor);
			
			this.userService.checkAuthority();
			User user = this.userService.findByPrincipal();
			Collection<Request> requests = this.requestService.notYetServicedRequest(user.getId());
			
			super.unauthenticate();
			
		} catch (final Throwable oops) {

			caught = oops.getClass();
		}
		super.checkExceptions(expected, caught);
	}
	
}
