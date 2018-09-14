package usecases;

import java.util.ArrayList;
import java.util.Collection;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Assert;

import services.ApplicationService;
import services.EmployeeService;
import services.ReportService;
import utilities.AbstractTest;
import domain.Application;
import domain.Employee;
import domain.Report;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring/junit.xml" })
@Transactional
public class UseCaseEmployee extends AbstractTest {

	@Autowired
	private ApplicationService applicationService;

	@Autowired
	private EmployeeService employeeService;

	@Autowired
	private ReportService reportService;

	/*
	 * 14. Un usuario autentificado como empleado podrá: a. Listar las
	 * solicitudes de los animales asociados al mismo centro al que está
	 * asociado el empleado y asignárselas a si mismo. b. Crear reportes
	 * asociados a las solicitudes asignadas de los clientes que soliciten a un
	 * animal. c. Registrar, editar y eliminar animales en el sistema, siempre
	 * asociados al mismo centro que el empleado. d. Buscar animales que ya
	 * estén registrados, ver su ficha y su centro asociado. e. Listar los
	 * avisos ordenados por fecha, siendo los primeros los más recientes. f.
	 * Desligar voluntarios de su stand. g. Listar los avisos y descartarlos,
	 * bien porque no se pueden atender o bien porque ya han sido atendidos.
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
}
