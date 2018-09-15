package usecases;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.validation.ConstraintViolationException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import services.BossService;
import services.CenterService;
import services.CompanyService;
import services.EmployeeService;
import services.EventService;
import services.StandService;
import services.VeterinaryService;
import services.WarehouseService;
import utilities.AbstractTest;
import domain.Boss;
import domain.Center;
import domain.Company;
import domain.Employee;
import domain.Event;
import domain.Stand;
import domain.Veterinary;
import domain.Warehouse;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
		"classpath:spring/junit.xml"
	})
@Transactional
public class UseCaseBoss extends AbstractTest {

	@Autowired
	private BossService bossService;
	
	@Autowired
	private CenterService centerService;
	
	@Autowired
	private WarehouseService warehouseService; 
	
	@Autowired
	private EmployeeService employeeService;
	
	@Autowired
	private VeterinaryService veterinaryService;
	
	@Autowired
	private EventService eventService;
	
	@Autowired
	private CompanyService companyService;
	
	@Autowired
	private StandService standService;
	
	/*
	 * 17. Un usuario autentificado como director podrá:
			a. Crear, editar y eliminar centros.
			b. Editar almacenes de sus centros.
			c. Asociar empleados a su centro siempre que este no esté asociado a otro y desligarlo de sus centros.
			d. Crear empleados, veterinarios y otros directores.
			e. Crear, editar, listar, eliminar eventos y filtrarlos según en qué estado estén, aún sin publicar, ya publicado, empezado y finalizado.
			f. Crear, editar, listar, eliminar empresas y asociarlas a eventos.
			g. Crear, editar, listar, eliminar stands y asociarle un empleado.
	 */
	
	//crear centro 17.1
	@Test
	public void createCenter(){
		final Object testingData [][] = {
				// Positive
				{"boss1", "name", "address", "5000", "5000", "http://picture.com", "description", ConstraintViolationException.class},
				// Negative: campos vacíos
				{"boss1", "name", "", "5000", "5000", "http://picture.com", "description", ConstraintViolationException.class},
				// Negative: URL mal
				{"boss1", "name", "address", "5000", "5000", "asdfg", "description", ConstraintViolationException.class},
		};
		for (int i = 0; i < testingData.length; i++)
			this.createCenterTemplate((String) testingData[i][0], // Username login
				(String) testingData[i][1], // name
				(String) testingData[i][2], //address
				(Integer) Integer.parseInt((String)testingData[i][3]), // capacity
				(Integer) Integer.parseInt((String)testingData[i][4]), // stock
				(String) testingData[i][5], // picture
				(String) testingData[i][6], // description
				(Class<?>) testingData[i][7]);
	}

	private void createCenterTemplate(final String boss, final String name,
			final String address, final Integer capacity, final Integer stock, final String picture,
			final String description, final Class<?> expected) {

		Class<?> caught;
		caught = null;

		try {
			super.authenticate(boss);
			
			Center center = this.centerService.create();
			center.setName(name);
			center.setAddress(address);
			center.setCapacity(capacity);
			center.setStock(stock);
			center.setPicture(picture);
			center.setDescription(description);
			this.centerService.save(center);
			
			super.unauthenticate();
			this.centerService.flush();
		}catch (final Throwable oops) {
			caught = oops.getClass();
		}
		super.checkExceptions(expected, caught);
	}
	
	// editar centros 17.b
	@Test
	public void editCenter(){
		final Object testingData [][] = {
				// Positive
				{"boss1", "center1", "atributo", null},
				// Negative
				{"boss2", "center1", "atributo", IllegalArgumentException.class}, // no es el boss del center que se quiere editar
				{"admin", "center1", "atributo", IllegalArgumentException.class}, // usurio no válido
		};
		for (int i = 0; i < testingData.length; i++)
			this.editCenterTemplate((String) testingData[i][0], // Username login
				(String) testingData[i][1], // centro
				(String) testingData[i][2], //atributo
				(Class<?>) testingData[i][3]);
	}

	private void editCenterTemplate(final String boss, final String center,
			final String atributo, final Class<?> expected) {

		Class<?> caught;
		caught = null;

		try {
			super.authenticate(boss);
			
			final int centerId = this.getEntityId(center);
			final Center centerFinal = this.centerService.findOne(centerId);
			centerFinal.setAddress(atributo);
			
			this.centerService.save(centerFinal);
			
			super.unauthenticate();
			this.centerService.flush();
		}catch (final Throwable oops) {
			caught = oops.getClass();
		}
		super.checkExceptions(expected, caught);
	}
	
	// eliminar centros 17 b
	@Test
	public void deleteCenter(){
		final Object testingData [][] = {
				// Positive
				{"boss1", "center1", null},
				
		};
		for (int i = 0; i < testingData.length; i++)
			this.deleteCenterTemplate((String) testingData[i][0], // Username login
				(String) testingData[i][1], // centro
				(Class<?>) testingData[i][2]);
	}

	private void deleteCenterTemplate(final String boss, final String center,
			 final Class<?> expected) {

		Class<?> caught;
		caught = null;

		try {
			super.authenticate(boss);
			
			final int centerId = this.getEntityId(center);
			final Center centerFinal = this.centerService.findOne(centerId);
			
			this.centerService.delete(centerFinal);
			
			super.unauthenticate();
			this.centerService.flush();
		}catch (final Throwable oops) {
			caught = oops.getClass();
		}
		super.checkExceptions(expected, caught);
	}
	
	
	// editar almacen 17.b
	@Test
	public void editWarehouse(){
		final Object testingData [][] = {
				// Positive
				{"boss1", "warehouse1", "23456", null},
				// Negative
				{"admin", "warehouse1", "234567", IllegalArgumentException.class}, // usurio no válido
		};
		for (int i = 0; i < testingData.length; i++)
			this.editWarehouseTemplate((String) testingData[i][0], // Username login
				(String) testingData[i][1], // warehouse
				(Integer) Integer.parseInt((String)testingData[i][2]), //atributo
				(Class<?>) testingData[i][3]);
	}

	private void editWarehouseTemplate(final String boss, final String warehouse,
			final Integer atributo, final Class<?> expected) {

		Class<?> caught;
		caught = null;

		try {
			super.authenticate(boss);
			
			final int warehouseId = this.getEntityId(warehouse);
			final Warehouse warehouseFinal = this.warehouseService.findOne(warehouseId);
			warehouseFinal.setStock(atributo);
			
			this.warehouseService.save(warehouseFinal);
			
			super.unauthenticate();
			this.warehouseService.flush();
		}catch (final Throwable oops) {
			caught = oops.getClass();
		}
		super.checkExceptions(expected, caught);
	}
	
	//17.c. Asociar empleados a su centro siempre que este no esté asociado a otro y desligarlo de sus centros.
	// 17.d 
	@Test
	public void crearYAsociarEmpleados(){ 
		final Object testingData [][] = {
				// Positive
				{"boss1", "center1", "name", "surname", "email@email.com", "678989898", "address", null},
				// Negative
				{"admin", "center1", "name", "surname", "email@email.com", "678989898", "address", IllegalArgumentException.class}, // usuario no válido
				{"boss1", "center1", "name", " ", "email@email.com", "678989898", "address", ConstraintViolationException.class}, // atributo mal
		};
		for (int i = 0; i < testingData.length; i++)
			this.crearYAsociarEmpleadosTemplate((String) testingData[i][0], // Username login
				(String) testingData[i][1], // center
				(String)testingData[i][2], // name
				(String)testingData[i][3], // surname
				(String)testingData[i][4], // email
				(String)testingData[i][5], // phone
				(String)testingData[i][6], // address
				(Class<?>) testingData[i][7]);
	}

	private void crearYAsociarEmpleadosTemplate(final String boss, final String center,
			final String name, String surname, String email, String phone, String address, final Class<?> expected) {

		Class<?> caught;
		caught = null;

		try {
			super.authenticate(boss);
			final int centerId = this.getEntityId(center);
			final Center centerFinal = this.centerService.findOne(centerId);
			
			final Employee employeeFinal = this.employeeService.create();
			employeeFinal.setName(name);
			employeeFinal.setSurname(surname);
			employeeFinal.setEmail(email);
			employeeFinal.setPhoneNumber(phone);
			employeeFinal.setAddress(address);
			employeeFinal.setCenter(centerFinal);
			this.employeeService.save(employeeFinal);
			
			
			super.unauthenticate();
			this.employeeService.flush();
		}catch (final Throwable oops) {
			caught = oops.getClass();
		}
		super.checkExceptions(expected, caught);
	}
	
	

	// 17.d Crear otros directores.
	@Test
	public void crearDirectores(){
		final Object testingData [][] = {
				// Positive
				{"boss1", "name", "surname", "email@email.com", "678989898", "address", null},
				// Negative
				{"boss1", " ", "surname", "email", "678989898", "address", ConstraintViolationException.class}, // atributo vacío
		};
		for (int i = 0; i < testingData.length; i++)
			this.crearDirectoresTemplate((String) testingData[i][0], // Username login
				(String)testingData[i][1], // name
				(String)testingData[i][2], // surname
				(String)testingData[i][3], // email
				(String)testingData[i][4], // phone
				(String)testingData[i][5], // address
				(Class<?>) testingData[i][6]);
	}

	private void crearDirectoresTemplate(final String boss,
			final String name, String surname, String email, String phone, String address, final Class<?> expected) {

		Class<?> caught;
		caught = null;

		try {
			super.authenticate(boss);
			
			final Boss newBoss = this.bossService.create();
			newBoss.setName(name);
			newBoss.setSurname(surname);
			newBoss.setEmail(email);
			newBoss.setPhoneNumber(phone);
			newBoss.setAddress(address);
			this.bossService.save(newBoss);
			
			
			super.unauthenticate();
			this.veterinaryService.flush();
		}catch (final Throwable oops) {
			caught = oops.getClass();
		}
		super.checkExceptions(expected, caught);
	}
	
	//e. Crear y editar eventos
	@Test
	public void crearYEditarEventos(){
		final Object testingData [][] = {
				{"boss1", "event1", null},
				// Negative
//				{"admin", "event1", IllegalArgumentException.class},
//				{"employee1", "event1", IllegalArgumentException.class},
		};
		for (int i = 0; i < testingData.length; i++){
			this.crearYEditarEventosTemplate((String) testingData[i][0], // Username login
				(String) testingData[i][1], // event
				(Class<?>) testingData[i][2]);
		}
	}

	private void crearYEditarEventosTemplate(String boss, String event, Class<?> expected) {
		Class<?> caught;
		caught = null;

		try {
			super.authenticate(boss);
			
			final int eventId = this.getEntityId(event);
			final Event eventFinal = this.eventService.findOne(eventId);
			eventFinal.setDescription("new description");
			this.eventService.save(eventFinal);
			
			super.unauthenticate();
			this.eventService.flush();
		}catch (final Throwable oops) {
			caught = oops.getClass();
		}
		super.checkExceptions(expected, caught);
		
	}

		
	//f. Crear y editar empresas y asociarlas a eventos
	@Test
	public void crearYEditarEmpresas(){
		final Object testingData [][] = {
				{"boss1", "company1", null},
		};
		for (int i = 0; i < testingData.length; i++){
			this.crearYEditarEmpresasTemplate((String) testingData[i][0], // Username login
					(String) testingData[i][1], // company
					(Class<?>) testingData[i][2]);
		}
	}

	private void crearYEditarEmpresasTemplate(String boss, String company, Class<?> expected) {
		Class<?> caught;
		caught = null;

		try {
			super.authenticate(boss);

			final int companyId = this.getEntityId(company);
			final Company companyFinal = this.companyService.findOne(companyId);
			
			companyFinal.setName("new name");
			this.companyService.save(companyFinal);
			
			super.unauthenticate();
			this.companyService.flush();
		}catch (final Throwable oops) {
			caught = oops.getClass();
		}
		super.checkExceptions(expected, caught);
		
	}
	
	
	
	//g. Crear y editar stands y asociarlo a un empleado
	@Test
	public void crearYEditarStands(){
		final Object testingData [][] = {
				// Positive
				{"boss1", "stand1", null},
				// Negative
				{"admin", "stand1", IllegalArgumentException.class}, // usuario no valido
				{"veterinary1", "stand1", IllegalArgumentException.class}, // usuario no valido
		};
		for (int i = 0; i < testingData.length; i++){
			this.crearYEditarStandsTemplate((String) testingData[i][0], // Username login
					(String) testingData[i][1], // stand
					(Class<?>) testingData[i][2]);
		}
	}

	private void crearYEditarStandsTemplate(String boss, 
			String stand, Class<?> expected) {
		Class<?> caught;
		caught = null;

		try {
			super.authenticate(boss);
			
			this.standService.create();
			final int standId = this.getEntityId(stand);
			final Stand standFinal = this.standService.findOne(standId);
			standFinal.setNumMaxVoluntaries(123456);
			this.standService.save(standFinal);
			
			
			super.unauthenticate();
			this.standService.flush();
		}catch (final Throwable oops) {
			caught = oops.getClass();
		}
		super.checkExceptions(expected, caught);
	}
	
	
}
