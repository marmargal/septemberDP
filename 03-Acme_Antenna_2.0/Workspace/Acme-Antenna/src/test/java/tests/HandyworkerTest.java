package tests;

import java.util.ArrayList;
import java.util.Collection;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import services.HandyworkerService;
import services.RequestService;
import utilities.AbstractTest;
import domain.Handyworker;
import domain.Request;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring/junit.xml" })
@Transactional
public class HandyworkerTest extends AbstractTest {
	
	@Autowired
	private HandyworkerService handyworkerService;
	
	@Autowired
	private RequestService requestService;
	
	//5.1 Service a request.
	@Test
	public void serviceRequestTest() {
		final Object testingData[][] = {
		// caso positivo, un handyworker atiende uno de sus request.
				{
					"result", "request3", "handyworker1", "user1", null 
				},
				
		// caso negativo, un handyworker atiende un request que no tiene asignado.
				{ 
					"result", "request3", "handyworker2", "user1", null
				}
		};
		
		
		for (int i = 0; i < testingData.length; i++)
			this.templateServiceRequestTest((String) testingData[i][0], (String) testingData[i][1],(String) testingData[i][2], (String) testingData[i][3],
					(Class<?>) testingData[i][4]);
	}

	private void templateServiceRequestTest(String result, String requestName, String handyworkerName, String userName, Class<?> expected) {

		Class<?> caught;
		caught = null;
		try {
			this.authenticate(userName);
			
			final int handyworkerId = this.getEntityId(handyworkerName);
			final Handyworker handyworker = this.handyworkerService.findOne(handyworkerId);
			
			final int requestId = this.getEntityId(requestName);
			final Request request = this.requestService.findOne(requestId);
			
			handyworker.getRequests().add(request);
			requestService.save(request);
			handyworkerService.saveRequest(handyworker);
			
			super.unauthenticate();
			
			this.requestService.flush();

		} catch (final Throwable oops) {

			caught = oops.getClass();
		}
		super.checkExceptions(expected, caught);
	}
	
	
	
	
	
	// 5.2 List the requests that he or she has already serviced.
	@Test
	public void listRequestServiceTest() {
		final Object testingData[][] = {
		// caso positivo, un handyworker lista los request que ha atendido.
				{
					"handyworker1", null 
				},
				
		// caso negativo, un agent intenta listar los request atendidos de un handyworker.
				{ 
					"agent1", IllegalArgumentException.class
				}
		};
		
		
		for (int i = 0; i < testingData.length; i++)
			this.templateListServiceRequestTest((String) testingData[i][0], (Class<?>) testingData[i][1]);
	}

	private void templateListServiceRequestTest(String handyworkerName, Class<?> expected) {

		Class<?> caught;
		caught = null;
		try {
			this.authenticate(handyworkerName);
			
			this.handyworkerService.checkAuthority();
			Collection<Request> lista = new ArrayList<>();
			Collection<Request> requests = new ArrayList<>();
			
			Handyworker handyworker = handyworkerService.findByPrincipal();

			lista = handyworker.getRequests();
			for (Request r : lista) {
				if (r.getFinishMoment() != null) {
					requests.add(r);
				}
			}
			
			super.unauthenticate();
		} catch (final Throwable oops) {

			caught = oops.getClass();
		}
		super.checkExceptions(expected, caught);
	}
	
	
	// 5.3 List the requests that he or she hasn’t serviced, yet.
	@Test
	public void notServiceRequestTest() {
		final Object testingData[][] = {
		// caso positivo, un handyworker lista los request que ha atendido.
				{
					"handyworker1", null 
				},
				
		// caso negativo, un agent intenta listar los request atendidos de un handyworker.
				{ 
					"agent1", IllegalArgumentException.class
				}
		};
		
		
		for (int i = 0; i < testingData.length; i++)
			this.templateNotServiceRequestTest((String) testingData[i][0], (Class<?>) testingData[i][1]);
	}

	private void templateNotServiceRequestTest(String handyworkerName, Class<?> expected) {

		Class<?> caught;
		caught = null;
		try {
			this.authenticate(handyworkerName);
			
			this.handyworkerService.checkAuthority();
			Collection<Request> lista = new ArrayList<>();
			Collection<Request> requests = new ArrayList<>();
			
			Handyworker handyworker = handyworkerService.findByPrincipal();

			lista = handyworker.getRequests();
			for (Request r : lista) {
				if (r.getFinishMoment() == null) {
					requests.add(r);
				}
			}
			
			super.unauthenticate();
		} catch (final Throwable oops) {

			caught = oops.getClass();
		}
		super.checkExceptions(expected, caught);
	}

	
	
}
