package usecases;

import java.util.Collection;

import javax.transaction.Transactional;

import junit.framework.Assert;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import services.BossService;
import services.ClientService;
import services.ConfigurationService;
import services.EmployeeService;
import services.VeterinaryService;
import services.VoluntaryService;
import utilities.AbstractTest;
import domain.Boss;
import domain.Client;
import domain.Configuration;
import domain.Employee;
import domain.Veterinary;
import domain.Voluntary;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring/junit.xml" })
@Transactional
public class UseCaseAdministrator extends AbstractTest {
	
	
	@Autowired
	private ConfigurationService	configurationService;
	@Autowired
	private BossService	bossService;
	@Autowired
	private ClientService	clientService;
	@Autowired
	private EmployeeService	employeeService;
	@Autowired
	private VeterinaryService	veterinaryService;
	@Autowired
	private VoluntaryService	voluntaryService;
	

	/*
	 * Caso de uso: Admin-> Editar los parámetros de configuración 13a
	 */

	@Test
	public void editConfigurationTest() {

		final Object testingData[][] = {
				{// Positive
				"admin", "configuration", "text1","text2","text3","text4", null },
				{// Negative: with voluntary like principal
				"voluntary1", "configuration", "text1","text2","text3","text4", IllegalArgumentException.class },
				{// Negative: without text
				"admin", "",  "text1","text2","text3","text4", NullPointerException.class }, 
				{// Negative: with anonymous user
				"", "configuration",  "text1","text2","text3","text4", IllegalArgumentException.class }, 
				{// Positive
				"admin", "configuration", "","","","", null } };

		for (int i = 0; i < testingData.length; i++)
			this.templateEditConfigurationTest(i, (String) testingData[i][0],
					(String) testingData[i][1], (String) testingData[i][2],(String) testingData[i][3], (String) testingData[i][4],(String) testingData[i][5], 
					(Class<?>) testingData[i][6]);
	}

	protected void templateEditConfigurationTest(final Integer i,
			final String principal, final String configuration, final String banner,final String countryCode, final String englishWelcome,final String spanishWelcome,
			final Class<?> expected) {
		Class<?> caught;

		caught = null;
		try {
			super.authenticate(principal);

			Integer configurationId = 0;
			if (configuration == "" || configuration == null)
				configurationId = null;
			else
				configurationId = super.getEntityId(configuration);

			final Configuration configurationBD = this.configurationService
					.findOne(configurationId);
			configurationBD.setBanner(banner);
			configurationBD.setCountryCode(countryCode);
			configurationBD.setEnglishWelcome(englishWelcome);
			configurationBD.setSpanishWelcome(spanishWelcome);

			this.configurationService.save(configurationBD);
			//this.configurationService.flush();

		} catch (final Throwable oops) {
			caught = oops.getClass();
		}
		super.checkExceptions(expected, caught);

	}
	
	/*
	 * Caso de uso: Admin-> Listar los usuarios del sistema agrupados según su rol en la protectora(Boss). 13b
	 */
	
	@Test
	public void listBossTest() {

		final Object testingData[][] = {
			{// Positive
				"admin", null
			}, {// Negative: with admin that not exists
				"admin1", IllegalArgumentException.class
			}, {// Negative: with anonymous user
				"", IllegalArgumentException.class
			},
		};

		for (int i = 0; i < testingData.length; i++)
			this.templateListBossTest(i, (String) testingData[i][0], (Class<?>) testingData[i][1]);
	}

	protected void templateListBossTest(final Integer i, final String principal, final Class<?> expected) {
		Class<?> caught;

		caught = null;
		try {
			super.authenticate(principal);

			final Collection<Boss> listBoss = this.bossService.findAll();

			Assert.assertNotNull(listBoss);

		} catch (final Throwable oops) {
			caught = oops.getClass();
		}
		super.checkExceptions(expected, caught);
	}
	
	
	/*
	 * Caso de uso: Admin-> Listar los usuarios del sistema agrupados según su rol en la protectora(Client). 13b
	 */
	
	@Test
	public void listClientTest() {

		final Object testingData[][] = {
			{// Positive
				"admin", null
			}, {// Negative: with admin that not exists
				"admin1", IllegalArgumentException.class
			}, {// Negative: with anonymous user
				"", IllegalArgumentException.class
			},
		};

		for (int i = 0; i < testingData.length; i++)
			this.templateListClientTest(i, (String) testingData[i][0], (Class<?>) testingData[i][1]);
	}

	protected void templateListClientTest(final Integer i, final String principal, final Class<?> expected) {
		Class<?> caught;

		caught = null;
		try {
			super.authenticate(principal);

			final Collection<Client> listClient = this.clientService.findAll();

			Assert.assertNotNull(listClient);

		} catch (final Throwable oops) {
			caught = oops.getClass();
		}
		super.checkExceptions(expected, caught);
	}
	
	/*
	 * Caso de uso: Admin-> Listar los usuarios del sistema agrupados según su rol en la protectora(Employee). 13b
	 */
	
	@Test
	public void listEmployeeTest() {
		System.out.println("eleeeeeeeeeeeeeeeee");
		final Object testingData[][] = {
			{// Positive
				"admin", null
			}, {// Negative: with admin that not exists
				"admin1", IllegalArgumentException.class
			}, {// Negative: with anonymous user
				"", IllegalArgumentException.class
			},
		};

		for (int i = 0; i < testingData.length; i++)
			this.templateListEmployeeTest(i, (String) testingData[i][0], (Class<?>) testingData[i][1]);
		
	}

	protected void templateListEmployeeTest(final Integer i, final String principal, final Class<?> expected) {
		Class<?> caught;

		caught = null;
		try {
			super.authenticate(principal);

			final Collection<Employee> listEmployee = this.employeeService.findAll();
			System.out.println(listEmployee.toString());
			Assert.assertNotNull(listEmployee);

		} catch (final Throwable oops) {
			caught = oops.getClass();
		}
		super.checkExceptions(expected, caught);
	}
	
	
	
	/*
	 * Caso de uso: Admin-> Listar los usuarios del sistema agrupados según su rol en la protectora(Veterinary). 13b
	 */
	
	@Test
	public void listVeterinaryTest() {

		final Object testingData[][] = {
			{// Positive
				"admin", null
			}, {// Negative: with admin that not exists
				"admin1", IllegalArgumentException.class
			}, {// Negative: with anonymous user
				"", IllegalArgumentException.class
			},
		};

		for (int i = 0; i < testingData.length; i++)
			this.templateListVeterinaryTest(i, (String) testingData[i][0], (Class<?>) testingData[i][1]);
	}

	protected void templateListVeterinaryTest(final Integer i, final String principal, final Class<?> expected) {
		Class<?> caught;

		caught = null;
		try {
			super.authenticate(principal);

			final Collection<Veterinary> listBoss = this.veterinaryService.findAll();

			Assert.assertNotNull(listBoss);

		} catch (final Throwable oops) {
			caught = oops.getClass();
		}
		super.checkExceptions(expected, caught);
	}
	
	
	/*
	 * Caso de uso: Admin-> Listar los usuarios del sistema agrupados según su rol en la protectora(Voluntary). 13b
	 */
	
	@Test
	public void listVoluntaryTest() {

		final Object testingData[][] = {
			{// Positive
				"admin", null
			}, {// Negative: with admin that not exists
				"admin1", IllegalArgumentException.class
			}, {// Negative: with anonymous user
				"", IllegalArgumentException.class
			},
		};

		for (int i = 0; i < testingData.length; i++)
			this.templateListVoluntaryTest(i, (String) testingData[i][0], (Class<?>) testingData[i][1]);
	}

	protected void templateListVoluntaryTest(final Integer i, final String principal, final Class<?> expected) {
		Class<?> caught;

		caught = null;
		try {
			super.authenticate(principal);

			final Collection<Voluntary> listClient = this.voluntaryService.findAll();

			Assert.assertNotNull(listClient);

		} catch (final Throwable oops) {
			caught = oops.getClass();
		}
		super.checkExceptions(expected, caught);
	}
	

}
