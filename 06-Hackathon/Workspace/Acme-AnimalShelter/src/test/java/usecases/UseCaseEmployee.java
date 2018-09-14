package usecases;

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

import services.ApplicationService;
import services.CenterService;
import services.EmployeeService;
import services.PetService;
import services.ReportService;
import services.VeterinaryService;
import utilities.AbstractTest;
import domain.Application;
import domain.Employee;
import domain.Pet;
import domain.Report;
import domain.Veterinary;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring/junit.xml" })
@Transactional
public class UseCaseEmployee extends AbstractTest {

	@Autowired
	private ApplicationService applicationService;

	@Autowired
	private EmployeeService employeeService;

	@Autowired
	private VeterinaryService veterinaryService;

	@Autowired
	private ReportService reportService;

	@Autowired
	private CenterService centerService;

	@Autowired
	private PetService petService;

	/*
	 * 14. Un usuario autentificado como empleado podrá: e. Listar los avisos
	 * ordenados por fecha, siendo los primeros los más recientes. f. Desligar
	 * voluntarios de su stand. g. Listar los avisos y descartarlos, bien porque
	 * no se pueden atender o bien porque ya han sido atendidos.
	 */

	/*
	 * 14. Un usuario autentificado como empleado podrá: a. Listar las
	 * solicitudes de los animales asociados al mismo centro al que está
	 * asociado el empleado y asignárselas a si mismo. b. Crear reportes
	 * asociados a las solicitudes asignadas de los clientes que soliciten a un
	 * animal. Asignarse una aplicación implica crear un reporte,así que para
	 * reducir un poco la complejidad no existe la acción de asignase una
	 * aplicación, sino que se crea un reporte para una applicación, lo que
	 * lleva implicito el asignartela
	 */
	@Test
	public void lisApplicationAndAsignTest() {

		final Object testingData[][] = {
				// Positive lista las solicitudes pendientes
				{ "", null },
				// negativo, alguien que no es un employee intenta hacer lo
				// mismo
				{ "", IllegalArgumentException.class },
				// positivo, el employee crea un reporte
				{ "", null },
				// negativo, alguien que no es un employee crea un reporte
				{ "", IllegalArgumentException.class },
				// negativo, se intenta crear un reporte sin application
				{ "sin", IllegalArgumentException.class }

		};

		for (int i = 0; i < testingData.length; i++)
			this.templateLisApplicationAndAsignTest((String) testingData[i][0],
					(Class<?>) testingData[i][1]);
	}

	private void templateLisApplicationAndAsignTest(String assign,
			final Class<?> expected) {
		Class<?> caught;

		caught = null;
		try {
			if (expected != null && assign.equals("")) {
				super.authenticate("voluntary1");

			} else {
				super.authenticate("employee1");

			}
			if (assign.equals("")) {
				Employee employee = this.employeeService.findByPrincipal();
				Collection<Application> applications = this.applicationService
						.findApplicationsPendingPerCentre(employee.getCenter());
				Assert.notNull(applications);
			} else if (assign.equals("")) {
				Report report = this.reportService.create();
				Employee employee = this.employeeService.findByPrincipal();
				ArrayList<Application> applications = new ArrayList<>();
				applications
						.addAll(this.applicationService
								.findApplicationsPendingPerCentre(employee
										.getCenter()));

				Application app = applications.get(0);
				report.setApplication(app);
				report.setDescription("aaaa");
				report.setEmployee(employee);
				report.setSuitable(false);
				this.reportService.save(report);

			} else {
				Report report = this.reportService.create();
				Employee employee = this.employeeService.findByPrincipal();
				ArrayList<Application> applications = new ArrayList<>();
				applications
						.addAll(this.applicationService
								.findApplicationsPendingPerCentre(employee
										.getCenter()));
				report.setApplication(null);
				report.setDescription("aaaa");
				report.setEmployee(employee);
				report.setSuitable(false);
				this.reportService.save(report);
			}

			this.unauthenticate();
		} catch (final Throwable oops) {
			caught = oops.getClass();
		}
		super.checkExceptions(expected, caught);
	}

	/*
	 * suit de test 14. Un usuario autentificado como empleado podrá:c.
	 * Registrar, editar y eliminar animales en el sistema, siempre asociados al
	 * mismo centro que el empleado.
	 */

	@Test
	public void petCrudTest() {

		final Object testingData[][] = {
				// positivo, un employee registra una mascota en su centro
				{ "employee1", "register", null },
				// negativo, un voluntary registra una mascota
				{ "voluntary1", "register", IllegalArgumentException.class },
				// negativo, employee registra una mascota nula
				{ "employee1", "", IllegalArgumentException.class },
				// positivo, un employee edita una mascota en su centro
				{ "employee1", "edit", null },
				// negativo,un alguien no autenticado edita una mascota en su
				// centro
				{ "", "edit", IllegalArgumentException.class },
				// negativo,un employee edita una mascota de otro centro
				{ "employee1", "otro", IllegalArgumentException.class },
				// positivo, un employee borra una mascota en su centro
				{ "employee1", "delete", null },
				// negativo, un cliente borra una mascota
				{ "client1", "delete", IllegalArgumentException.class },
				// negativo, un employee borra una mascota que no existe
				{ "employee1", "exist", IllegalArgumentException.class },
				// negativo, un employee borra una mascota que nula
				{ "employee1", "null", IllegalArgumentException.class }, };

		for (int i = 0; i < testingData.length; i++)
			this.templatePetCrudTest((String) testingData[i][0],
					(String) testingData[i][1], (Class<?>) testingData[i][2]);
	}

	private void templatePetCrudTest(String actor, String action,
			final Class<?> expected) {
		Class<?> caught;

		caught = null;
		try {
			if (action.equals("register") && actor.equals("employee1")) {
				this.authenticate(actor);
				Employee employee = this.employeeService.findByPrincipal();
				Pet pet = petService.create();
				pet.setAge(2);
				pet.setCenter(employee.getCenter());
				pet.setChip(true);
				pet.setDate(new Date(System.currentTimeMillis() - 100));
				pet.setFoodExpense(2);
				pet.setName("name");
				pet.setStatus(true);
				pet.setType("DOG");
				this.petService.save(pet);
			} else if (action.equals("register") && actor.equals("voluntary1")) {
				this.authenticate(actor);

				Pet pet = petService.create();
				pet.setAge(2);
				pet.setCenter(centerService.findOne(super
						.getEntityId("center1")));
				pet.setChip(true);
				pet.setDate(new Date(System.currentTimeMillis() - 100));
				pet.setFoodExpense(2);
				pet.setName("name");
				pet.setStatus(true);
				pet.setType("DOG");
				this.petService.save(pet);
			} else if (action.equals("") && actor.equals("employee1")) {
				this.authenticate(actor);
				Pet pet = null;
				this.petService.save(pet);
			} else if (action.equals("edit") && actor.equals("employee1")) {
				this.authenticate(actor);
				Pet pet = this.petService.findOne(super.getEntityId("pet1"));
				pet.setName("name");
				this.petService.save(pet);
			} else if (action.equals("edit") && actor.equals("")) {
				Pet pet = this.petService.findOne(super.getEntityId("pet1"));
				pet.setName("name");
				this.petService.save(pet);
			} else if (action.equals("otro") && actor.equals("employee1")) {
				super.authenticate(actor);
				Pet pet = this.petService.findOne(super.getEntityId("pet6"));
				pet.setName("name");
				this.petService.save(pet);
			} else if (action.equals("delete") && actor.equals("employee1")) {
				super.authenticate(actor);
				Pet pet = this.petService.findOne(super.getEntityId("pet1"));
				this.petService.delete(pet);
			} else if (action.equals("delete") && actor.equals("client1")) {
				super.authenticate(actor);
				Pet pet = this.petService.findOne(super.getEntityId("pet1"));
				this.petService.delete(pet);
			} else if (action.equals("exist") && actor.equals("employee1")) {
				super.authenticate(actor);
				Pet pet = this.petService.create();
				pet.setId(999999999);
				this.petService.delete(pet);
			} else if (action.equals("null") && actor.equals("employee1")) {
				super.authenticate(actor);

				this.petService.delete(null);
			}

			this.unauthenticate();
		} catch (final Throwable oops) {
			caught = oops.getClass();
		}
		super.checkExceptions(expected, caught);

	}

	/*
	 * 14. Un usuario autentificado como empleado podrá: d. Buscar animales que
	 * ya estén registrados, ver su ficha y su centro asociado.
	 */

	@Test
	public void lisPetTest() {

		final Object testingData[][] = {
				// Positive, employee1 lista las todas las mascotas
				{ "", null },
				// negativo, client1 accede a la ficha de una mascota
				{ "client1", IllegalArgumentException.class },
				// negativo, employee1 accede a la ficha que no existe
				{ "exist", IllegalArgumentException.class },

		};

		for (int i = 0; i < testingData.length; i++)
			this.templateLisPetTest((String) testingData[i][0],
					(Class<?>) testingData[i][1]);
	}

	private void templateLisPetTest(String actor, final Class<?> expected) {
		Class<?> caught;

		caught = null;
		try {
			if (actor.equals("employee1")) {
				super.authenticate("employee1");

				Collection<Pet> pets = this.petService.findAll();
				Assert.notNull(pets);
			} else if (actor.equals("client1")) {
				super.authenticate(actor);
				this.reportService.findOne(super.getEntityId("report1"));
			} else if (actor.equals("exist")) {
				super.authenticate(actor);
				this.reportService.findOne(9999999);
			}
			this.unauthenticate();
		} catch (final Throwable oops) {
			caught = oops.getClass();
		}
		super.checkExceptions(expected, caught);
	}

	/*
	 * 14. Un usuario autentificado como empleado podrá: e. Listar los avisos
	 * ordenados por fecha, siendo los primeros los más recientes.
	 */
}
