package usecases;

import java.util.Collection;

import javax.transaction.Transactional;
import javax.validation.ConstraintViolationException;

import junit.framework.Assert;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import services.ApplicationService;
import services.BossService;
import services.CenterService;
import services.ClientService;
import services.ConfigurationService;
import services.EmployeeService;
import services.MedicalReportService;
import services.MessageService;
import services.NoticeService;
import services.PetService;
import services.VeterinaryService;
import services.VoluntaryService;
import utilities.AbstractTest;
import domain.Application;
import domain.Boss;
import domain.Center;
import domain.Client;
import domain.Configuration;
import domain.Employee;
import domain.MedicalReport;
import domain.Message;
import domain.Notice;
import domain.Pet;
import domain.Veterinary;
import domain.Voluntary;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring/junit.xml" })
@Transactional
public class UseCaseAdministrator extends AbstractTest {

	@Autowired
	private ConfigurationService configurationService;
	@Autowired
	private BossService bossService;
	@Autowired
	private ClientService clientService;
	@Autowired
	private EmployeeService employeeService;
	@Autowired
	private VeterinaryService veterinaryService;
	@Autowired
	private VoluntaryService voluntaryService;
	@Autowired
	private ApplicationService applicationService;
	@Autowired
	private NoticeService noticeService;
	@Autowired
	private MedicalReportService medicalReportService;
	@Autowired
	private CenterService centerService;
	@Autowired
	private PetService petService;
	@Autowired
	private MessageService messageService;

	/*
	 * Caso de uso: Admin-> Editar los parámetros de configuración 13a
	 */

	@Test
	public void editConfigurationTest() {

		final Object testingData[][] = { {// Positive
				"admin", "configuration", "https://google.com",
						"Welcome to Acme Animal Shelter!!",
						"¡¡Bienvenido a Acme Animal Shelter!!", "ES", null }, {// Negative:
						// with
						// voluntary
						// like
						// principal
						"voluntary1", "configuration", "https://google.com",
						"Welcome to Acme Animal Shelter!!",
						"¡¡Bienvenido a Acme Animal Shelter!!", "ES",
						IllegalArgumentException.class }, {// Negative: without
						// text
						"admin", "", "https://google.com",
						"Welcome to Acme Animal Shelter!!",
						"¡¡Bienvenido a Acme Animal Shelter!!", "ES",
						NullPointerException.class }, {// Negative: with
						// anonymous user
						"", "configuration", "https://google.com",
						"Welcome to Acme Animal Shelter!!",
						"¡¡Bienvenido a Acme Animal Shelter!!", "ES",
						IllegalArgumentException.class }, {// Positive
				"admin", "configuration", "", "", "", "",
						ConstraintViolationException.class } };

		for (int i = 0; i < testingData.length; i++)
			this.templateEditConfigurationTest(i, (String) testingData[i][0],
					(String) testingData[i][1], (String) testingData[i][2],
					(String) testingData[i][3], (String) testingData[i][4],
					(String) testingData[i][5], (Class<?>) testingData[i][6]);
	}

	protected void templateEditConfigurationTest(final Integer i,
			final String principal, final String configuration,
			final String banner, final String countryCode,
			final String englishWelcome, final String spanishWelcome,
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
			this.configurationService.flush();

		} catch (final Throwable oops) {
			caught = oops.getClass();
		}
		super.checkExceptions(expected, caught);

	}

	/*
	 * Caso de uso: Admin-> Listar los usuarios del sistema agrupados según su
	 * rol en la protectora(Boss). 13b
	 */

	@Test
	public void listBossTest() {

		final Object testingData[][] = { {
				// Positive
				"admin", null }, {
				// Negative: with admin that not exists
				"admin1", IllegalArgumentException.class }, {
				// Negative: with anonymous user
				"", IllegalArgumentException.class }, };

		for (int i = 0; i < testingData.length; i++)
			this.templateListBossTest(i, (String) testingData[i][0],
					(Class<?>) testingData[i][1]);
	}

	protected void templateListBossTest(final Integer i,
			final String principal, final Class<?> expected) {
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
	 * Caso de uso: Admin-> Listar los usuarios del sistema agrupados según su
	 * rol en la protectora(Client). 13b
	 */

	@Test
	public void listClientTest() {

		final Object testingData[][] = { {
				// Positive
				"admin", null }, {
				// Negative: with admin that not exists
				"admin1", IllegalArgumentException.class }, {
				// Negative: with anonymous user
				"", IllegalArgumentException.class }, };

		for (int i = 0; i < testingData.length; i++)
			this.templateListClientTest(i, (String) testingData[i][0],
					(Class<?>) testingData[i][1]);
	}

	protected void templateListClientTest(final Integer i,
			final String principal, final Class<?> expected) {
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
	 * Caso de uso: Admin-> Listar los usuarios del sistema agrupados según su
	 * rol en la protectora(Employee). 13b
	 */

	@Test
	public void listEmployeeTest() {
		final Object testingData[][] = { {
				// Positive
				"admin", null }, {
				// Negative: with admin that not exists
				"admin1", IllegalArgumentException.class }, {
				// Negative: with anonymous user
				"", IllegalArgumentException.class }, };

		for (int i = 0; i < testingData.length; i++)
			this.templateListEmployeeTest(i, (String) testingData[i][0],
					(Class<?>) testingData[i][1]);

	}

	protected void templateListEmployeeTest(final Integer i,
			final String principal, final Class<?> expected) {
		Class<?> caught;

		caught = null;
		try {
			super.authenticate(principal);

			final Collection<Employee> listEmployee = this.employeeService
					.findAll();
			Assert.assertNotNull(listEmployee);

		} catch (final Throwable oops) {
			caught = oops.getClass();
		}
		super.checkExceptions(expected, caught);
	}

	/*
	 * Caso de uso: Admin-> Listar los usuarios del sistema agrupados según su
	 * rol en la protectora(Veterinary). 13b
	 */

	@Test
	public void listVeterinaryTest() {

		final Object testingData[][] = { {
				// Positive
				"admin", null }, {
				// Negative: with admin that not exists
				"admin1", IllegalArgumentException.class }, {
				// Negative: with anonymous user
				"", IllegalArgumentException.class }, };

		for (int i = 0; i < testingData.length; i++)
			this.templateListVeterinaryTest(i, (String) testingData[i][0],
					(Class<?>) testingData[i][1]);
	}

	protected void templateListVeterinaryTest(final Integer i,
			final String principal, final Class<?> expected) {
		Class<?> caught;

		caught = null;
		try {
			super.authenticate(principal);

			final Collection<Veterinary> listBoss = this.veterinaryService
					.findAll();

			Assert.assertNotNull(listBoss);

		} catch (final Throwable oops) {
			caught = oops.getClass();
		}
		super.checkExceptions(expected, caught);
	}

	/*
	 * Caso de uso: Admin-> Listar los usuarios del sistema agrupados según su
	 * rol en la protectora(Voluntary). 13b
	 */

	@Test
	public void listVoluntaryTest() {

		final Object testingData[][] = { {
				// Positive
				"admin", null }, {
				// Negative: with admin that not exists
				"admin1", IllegalArgumentException.class }, {
				// Negative: with anonymous user
				"", IllegalArgumentException.class }, };

		for (int i = 0; i < testingData.length; i++)
			this.templateListVoluntaryTest(i, (String) testingData[i][0],
					(Class<?>) testingData[i][1]);
	}

	protected void templateListVoluntaryTest(final Integer i,
			final String principal, final Class<?> expected) {
		Class<?> caught;

		caught = null;
		try {
			super.authenticate(principal);

			final Collection<Voluntary> listClient = this.voluntaryService
					.findAll();

			Assert.assertNotNull(listClient);

		} catch (final Throwable oops) {
			caught = oops.getClass();
		}
		super.checkExceptions(expected, caught);
	}

	/*
	 * Caso de uso: Admin-> Eliminar solicitudes pendientes inapropiadas o de
	 * clientes que estén baneados.. 13d
	 */
	@Test
	public void deleteRequestTest() {

		final Object testingData[][] = {
				{// Positive
				"admin", "application2", null },
				{// Negative: with voluntary like principal
				"voluntary1", "application1", IllegalArgumentException.class },
				{// Negative: application dont exists
				"admin", "0", IllegalArgumentException.class }

		};

		for (int i = 0; i < testingData.length; i++)
			this.templateDeleteApplicationTest(i, (String) testingData[i][0],
					(String) testingData[i][1], (Class<?>) testingData[i][2]);
	}

	protected void templateDeleteApplicationTest(final Integer i,
			final String principal, final String application,
			final Class<?> expected) {
		Class<?> caught;

		caught = null;
		try {
			super.authenticate(principal);
			Integer applicationId = 0;
			if (!application.equals("0")) {
				if (application == "" || application == null)
					applicationId = null;
				else
					applicationId = super.getEntityId(application);

			}

			final Application categoryBD = this.applicationService
					.findOne(applicationId);

			this.applicationService.delete(categoryBD);
			this.applicationService.flush();

		} catch (final Throwable oops) {
			caught = oops.getClass();
		}
		super.checkExceptions(expected, caught);
	}

	/*
	 * Caso de uso: Admin-> Eliminar avisos que se sospeche que sean falsos o
	 * inapropiados. 13e
	 */
	@Test
	public void deleteNoticeTest() {

		final Object testingData[][] = { {// Positive
				"admin", "notice1", null }, {// Negative: with voluntary like
												// principal
				"voluntary1", "notice1", IllegalArgumentException.class }, {// Negative:
																			// notice
																			// dont
																			// exists
				"admin", "0", IllegalArgumentException.class }

		};

		for (int i = 0; i < testingData.length; i++)
			this.templateDeleteNoticeTest(i, (String) testingData[i][0],
					(String) testingData[i][1], (Class<?>) testingData[i][2]);
	}

	protected void templateDeleteNoticeTest(final Integer i,
			final String principal, final String notice, final Class<?> expected) {
		Class<?> caught;

		caught = null;
		try {
			super.authenticate(principal);
			Integer noticeId = 0;
			if (!notice.equals("0")) {
				if (notice == "" || notice == null)
					noticeId = null;
				else
					noticeId = super.getEntityId(notice);

			}

			final Notice categoryBD = this.noticeService.findOne(noticeId);

			this.noticeService.delete(categoryBD);
			this.noticeService.flush();

		} catch (final Throwable oops) {
			caught = oops.getClass();
		}
		super.checkExceptions(expected, caught);
	}

	/*
	 * Caso de uso: Admin-> Eliminar informes de los veterinarios baneados. 13f
	 */
	@Test
	public void deleteMedicalReportTest() {

		final Object testingData[][] = {
				{// Positive
				"admin", "medicalReport1", null },
				{// Negative: with voluntary like principal
				"voluntary1", "medicalReport1", IllegalArgumentException.class },
				{// Negative: medical report dont exists
				"admin", "0", IllegalArgumentException.class }

		};

		for (int i = 0; i < testingData.length; i++)
			this.templateDeleteMedicalReportTest(i, (String) testingData[i][0],
					(String) testingData[i][1], (Class<?>) testingData[i][2]);
	}

	protected void templateDeleteMedicalReportTest(final Integer i,
			final String principal, final String medicalReport,
			final Class<?> expected) {
		Class<?> caught;

		caught = null;
		try {
			super.authenticate(principal);
			Integer medicalReportId = 0;
			if (!medicalReport.equals("0")) {
				if (medicalReport == "" || medicalReport == null)
					medicalReportId = null;
				else
					medicalReportId = super.getEntityId(medicalReport);

			}

			final MedicalReport categoryBD = this.medicalReportService
					.findOne(medicalReportId);

			this.medicalReportService.delete(categoryBD);
			this.medicalReportService.flush();

		} catch (final Throwable oops) {
			caught = oops.getClass();
		}
		super.checkExceptions(expected, caught);
	}

	/*
	 * Caso de uso: Admin-> Eliminar un centro por cierre del mismo. 13g
	 */
	@Test
	public void deleteCenterTest() {

		final Object testingData[][] = {
				{// Positive
				"admin", "center2", null },
				{// Negative: with voluntary like
					// principal
						"voluntary1", "center1", IllegalArgumentException.class },
				{// Negative:
					// medical
					// center
					// dont
					// exists
						"admin", "0", IllegalArgumentException.class }

		};

		for (int i = 0; i < testingData.length; i++)
			this.templateDeleteCenterTest(i, (String) testingData[i][0],
					(String) testingData[i][1], (Class<?>) testingData[i][2]);
	}

	protected void templateDeleteCenterTest(final Integer i,
			final String principal, final String center, final Class<?> expected) {
		Class<?> caught;

		caught = null;
		try {
			super.authenticate(principal);
			Integer centerId = 0;
			if (!center.equals("0")) {
				if (center == "" || center == null)
					centerId = null;
				else
					centerId = super.getEntityId(center);

			}

			final Center categoryBD = this.centerService.findOne(centerId);

			this.centerService.delete(categoryBD);
			this.centerService.flush();

		} catch (final Throwable oops) {
			caught = oops.getClass();
		}
		super.checkExceptions(expected, caught);
	}

	/*
	 * Caso de uso: Admin-> Eliminar un animal por fallecimiento.. 13h
	 */
	@Test
	public void deletePetTest() {

		final Object testingData[][] = {
				{// Positive
				"admin", "pet2", null },
				{// Negative: with voluntary like
					// principal
						"voluntary1", "pet3", IllegalArgumentException.class },
				{// Negative:
					// medical
					// center
					// dont
					// exists
						"admin", "0", IllegalArgumentException.class }

		};

		for (int i = 0; i < testingData.length; i++)
			this.templateDeletePetTest(i, (String) testingData[i][0],
					(String) testingData[i][1], (Class<?>) testingData[i][2]);
	}

	protected void templateDeletePetTest(final Integer i,
			final String principal, final String pet, final Class<?> expected) {
		Class<?> caught;

		caught = null;
		try {
			super.authenticate(principal);
			Integer petId = 0;
			if (!pet.equals("0")) {
				if (pet == "" || pet == null)
					petId = null;
				else
					petId = super.getEntityId(pet);

			}

			final Pet categoryBD = this.petService.findOne(petId);

			this.petService.delete(categoryBD);
			this.petService.flush();

		} catch (final Throwable oops) {
			caught = oops.getClass();
		}
		super.checkExceptions(expected, caught);
	}

	/*
	 * Caso de uso: Admin-> Ver los mensajes de cualquier usuario y eliminarlos.
	 * 13i
	 */
	@Test
	public void deleteMessageTest() {

		final Object testingData[][] = { {// Positive
				"admin", "message2", null }, {// Negative: with voluntary like
						// principal
						"voluntary1", "message2",
						IllegalArgumentException.class }, {// Negative:
															// medical
															// center
															// dont
															// exists
						"admin", "0", IllegalArgumentException.class }

		};

		for (int i = 0; i < testingData.length; i++)
			this.templateDeleteMessageTest(i, (String) testingData[i][0],
					(String) testingData[i][1], (Class<?>) testingData[i][2]);
	}

	protected void templateDeleteMessageTest(final Integer i,
			final String principal, final String message,
			final Class<?> expected) {
		Class<?> caught;

		caught = null;
		try {
			super.authenticate(principal);
			Integer messageId = 0;
			if (!message.equals("0")) {
				if (message == "" || message == null)
					messageId = null;
				else
					messageId = super.getEntityId(message);

			}

			final Message categoryBD = this.messageService.findOne(messageId);

			this.messageService.delete(categoryBD);
			this.messageService.flush();

		} catch (final Throwable oops) {
			caught = oops.getClass();
		}
		super.checkExceptions(expected, caught);
	}

	/*
	 * Caso de uso: Admin-> Banear los empleados, veterinarios, voluntarios y
	 * clientes(Empleado). 13c
	 */

	@Test
	public void editBannerTest() {

		final Object testingData[][] = { {
				// Positive
				"admin", "employee2", true, null }, {
				// Negative
				"admin", "", true, NullPointerException.class } };

		for (int i = 0; i < testingData.length; i++)
			this.templateEditBannerTest(i, (String) testingData[i][0],
					(String) testingData[i][1], (boolean) testingData[i][2],
					(Class<?>) testingData[i][3]);
	}

	protected void templateEditBannerTest(final Integer i,
			final String principal, final String employee,
			final boolean banner, final Class<?> expected) {
		Class<?> caught;

		caught = null;
		try {
			super.authenticate(principal);

			Integer employeeId = 0;
			if (employee == "" || employee == null)
				employeeId = null;
			else
				employeeId = super.getEntityId(employee);

			final Employee configurationBD = this.employeeService
					.findOne(employeeId);
			configurationBD.setBan(true);

			this.employeeService.save(configurationBD);
			this.employeeService.flush();

		} catch (final Throwable oops) {
			caught = oops.getClass();
		}
		super.checkExceptions(expected, caught);

	}

	/*
	 * Caso de uso: Admin-> Banear los empleados, veterinarios, voluntarios y
	 * clientes(veterinarios). 13c
	 */

	@Test
	public void editBannerVeterinaryTest() {

		final Object testingData[][] = { {
				// Positive
				"admin", "veterinary2", true, null }, {
				// Negative
				"admin", "", true, NullPointerException.class } };

		for (int i = 0; i < testingData.length; i++)
			this.templateEditBannerVeterinaryTest(i,
					(String) testingData[i][0], (String) testingData[i][1],
					(boolean) testingData[i][2], (Class<?>) testingData[i][3]);
	}

	protected void templateEditBannerVeterinaryTest(final Integer i,
			final String principal, final String veterinary,
			final boolean banner, final Class<?> expected) {
		Class<?> caught;

		caught = null;
		try {
			super.authenticate(principal);

			Integer employeeId = 0;
			if (veterinary == "" || veterinary == null)
				employeeId = null;
			else
				employeeId = super.getEntityId(veterinary);

			final Veterinary configurationBD = this.veterinaryService
					.findOne(employeeId);
			configurationBD.setBan(true);

			this.veterinaryService.save(configurationBD);
			this.veterinaryService.flush();

		} catch (final Throwable oops) {
			caught = oops.getClass();
		}
		super.checkExceptions(expected, caught);

	}

	/*
	 * Caso de uso: Admin-> Banear los empleados, veterinarios, voluntarios y
	 * clientes(voluntarios). 13c
	 */

	@Test
	public void editBannerVoluntaryTest() {

		final Object testingData[][] = { {
				// Positive
				"admin", "voluntary1", true, null }, {
				// Negative
				"admin", "", true, NullPointerException.class } };

		for (int i = 0; i < testingData.length; i++)
			this.templateEditBannerVoluntaryTest(i, (String) testingData[i][0],
					(String) testingData[i][1], (boolean) testingData[i][2],
					(Class<?>) testingData[i][3]);
	}

	protected void templateEditBannerVoluntaryTest(final Integer i,
			final String principal, final String voluntary,
			final boolean banner, final Class<?> expected) {
		Class<?> caught;

		caught = null;
		try {
			super.authenticate(principal);

			Integer employeeId = 0;
			if (voluntary == "" || voluntary == null)
				employeeId = null;
			else
				employeeId = super.getEntityId(voluntary);

			final Voluntary configurationBD = this.voluntaryService
					.findOne(employeeId);
			configurationBD.setBan(true);

			this.voluntaryService.save(configurationBD);
			this.voluntaryService.flush();

		} catch (final Throwable oops) {
			caught = oops.getClass();
		}
		super.checkExceptions(expected, caught);

	}

	/*
	 * Caso de uso: Admin-> Banear los empleados, veterinarios, voluntarios y
	 * clientes(clientes). 13c
	 */

	@Test
	public void editBannerClientTest() {

		final Object testingData[][] = { {
				// Positive
				"admin", "client1", true, null }, {
				// Negative
				"admin", "", true, NullPointerException.class } };

		for (int i = 0; i < testingData.length; i++)
			this.templateEditBannerClientTest(i, (String) testingData[i][0],
					(String) testingData[i][1], (boolean) testingData[i][2],
					(Class<?>) testingData[i][3]);
	}

	protected void templateEditBannerClientTest(final Integer i,
			final String principal, final String client, final boolean banner,
			final Class<?> expected) {
		Class<?> caught;

		caught = null;
		try {
			super.authenticate(principal);

			Integer employeeId = 0;
			if (client == "" || client == null)
				employeeId = null;
			else
				employeeId = super.getEntityId(client);

			final Client configurationBD = this.clientService
					.findOne(employeeId);
			configurationBD.setBan(true);

			this.clientService.save(configurationBD);
			this.clientService.flush();

		} catch (final Throwable oops) {
			caught = oops.getClass();
		}
		super.checkExceptions(expected, caught);

	}

}
