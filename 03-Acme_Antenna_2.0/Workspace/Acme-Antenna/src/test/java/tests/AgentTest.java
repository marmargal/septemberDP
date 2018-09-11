package tests;

import javax.transaction.Transactional;
import javax.validation.ConstraintViolationException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import services.BannerService;
import utilities.AbstractTest;
import domain.Banner;
import domain.CreditCard;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring/junit.xml" })
@Transactional
public class AgentTest extends AbstractTest {

	@Autowired
	private BannerService bannerService;
	
	
	//10.1 Register a banner to the system.
	@Test
	public void registerBannerTest() {

		final Object testingData[][] = {
			{	
				// Positivo, un agent logeado registra un nuevo banner.
				"agent1", "http://www.coordinadora.com/wp-content/uploads/sidebar_usuario-corporativo.png",
				"http://www.coordinadora.com/wp-content/uploads/sidebar_usuario-corporativo.png", 
				"holderName", "brandName","number", 10, 19, 123, null
			},
			
			{
				// Negativo, un agent logeado intenta registrar un nuevo banner con una imagen que no es una URL.
				"user1", "url",
				"http://www.coordinadora.com/wp-content/uploads/sidebar_usuario-corporativo.png", 
				"holderName", "brandName","number", 10, 19, 123, ConstraintViolationException.class
			}

		};

		for (int i = 0; i < testingData.length; i++)
			this.templateRegisterBannerTest((String) testingData[i][0], (String) testingData[i][1], (String) testingData[i][2],
					(String) testingData[i][3], (String) testingData[i][4], (String) testingData[i][5],
					(Integer) testingData[i][6], (Integer) testingData[i][7], (Integer) testingData[i][8],
					(Class<?>) testingData[i][9]);
	}

	protected void templateRegisterBannerTest(final String actorName, final String picture, final String targetPage,
			String holderName, String brandName, String number, Integer expirationMonth, Integer expirationYear, Integer cvv, final Class<?> expected) {
		
		Class<?> caught;

		caught = null;
		try {
			super.authenticate(actorName);
			
			Banner banner = this.bannerService.create();
			CreditCard cc = new CreditCard();
			
			cc.setBrandName(brandName);
			cc.setCvv(cvv);
			cc.setExpirationMonth(expirationMonth);
			cc.setExpirationYear(expirationYear);
			cc.setHolderName(holderName);
			cc.setNumber(number);
			
			banner.setCreditCard(cc);
			banner.setPicture(picture);
			banner.setTargetPage(targetPage);
			
			this.bannerService.save(banner);
			super.unauthenticate();
			this.bannerService.flush();

		} catch (final Throwable oops) {
			caught = oops.getClass();
		}
		super.checkExceptions(expected, caught);
	}
	
	
}
