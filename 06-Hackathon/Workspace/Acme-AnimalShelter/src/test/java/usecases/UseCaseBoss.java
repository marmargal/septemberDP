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
				{"boss1", "name", "address", "5000", "5000", "http://picture.com", "description", null},
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
				// Negative
				{"boss2", "center1", IllegalArgumentException.class}, // no es el boss del center que se quiere editar
				{"veterinary", "center1", IllegalArgumentException.class}, // usuario no válido
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
	public void crearYAsociarEmpleados(){ //TODO
		final Object testingData [][] = {
				// Positive
//				{"boss1", "center1", "name", "surname", "email@email.com", "678989898", "address", null},
				// Negative
//				{"boss1", "", "name", "surname", "email@email.com", "678989898", "address", IllegalArgumentException.class}, // centro vacío
//				{"boss1", "center1", "name", "surname", "email@email.com", "", "address", null}, //teléfono mal
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
	
	// 17.d Crear veterinarios
	@Test
	public void crearVeterinarios(){
		final Object testingData [][] = { //TODO
				// Positive
//				{"boss1", "center1", "name", "surname", "email@email.com", "678989898", "address", null},
				// Negative
//				{"boss1", "name", "surname", "email", "678989898", "address", IllegalArgumentException.class}, // email mal
//				{"boss1", "name", "surname", "email@email.com", "", "address", null}, //teléfono mal
		};
		for (int i = 0; i < testingData.length; i++)
			this.crearVeterinariosTemplate((String) testingData[i][0], // Username login
				(String)testingData[i][1], // name
				(String)testingData[i][2], // surname
				(String)testingData[i][3], // email
				(String)testingData[i][4], // phone
				(String)testingData[i][5], // address
				(Class<?>) testingData[i][6]);
	}

	private void crearVeterinariosTemplate(final String boss,
			final String name, String surname, String email, String phone, String address, final Class<?> expected) {

		Class<?> caught;
		caught = null;

		try {
			super.authenticate(boss);
			
			final Veterinary veterinary = this.veterinaryService.create();
			veterinary.setName(name);
			veterinary.setSurname(surname);
			veterinary.setEmail(email);
			veterinary.setPhoneNumber(phone);
			veterinary.setAddress(address);
			this.veterinaryService.save(veterinary);
			
			
			super.unauthenticate();
			this.veterinaryService.flush();
		}catch (final Throwable oops) {
			caught = oops.getClass();
		}
		super.checkExceptions(expected, caught);
	}

	// 17.d Crear otros directores.
	@Test
	public void crearDirectores(){
		final Object testingData [][] = { //TODO
				// Positive
//				{"boss1", "center1", "name", "surname", "email@email.com", "678989898", "address", null},
				// Negative
//				{"boss1", "name", "surname", "email", "678989898", "address", IllegalArgumentException.class}, // email mal
//				{"boss1", "name", "surname", "email@email.com", "", "address", null}, //teléfono mal
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
			
			final Veterinary veterinary = this.veterinaryService.create();
			veterinary.setName(name);
			veterinary.setSurname(surname);
			veterinary.setEmail(email);
			veterinary.setPhoneNumber(phone);
			veterinary.setAddress(address);
			this.veterinaryService.save(veterinary);
			
			
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
				// Positive
//				{"boss1", "center1", "title", "description", "nameSite", "address", "placard", "10/10/2020 11:00", "10/10/2020 11:00", null},
				// Negative: campos vacíos (address)
//				{"boss1", "center1", "title", "description", "nameSite", " ", "placard", "10/10/2020 11:00", "10/10/2020 11:00", IllegalArgumentException.class},
				// Negative: usuario no válido
//				{"admin", "center1", "title", "description", "nameSite", "address", "placard", "10/10/2020 11:00", "10/10/2020 11:00", IllegalArgumentException.class},
		};
		for (int i = 0; i < testingData.length; i++){
			this.crearYEditarEventosTemplate((String) testingData[i][0], // Username login
				(String) testingData[i][1], // center
				(String) testingData[i][2], // title
				(String) testingData[i][3], // description
				(String)testingData[i][4], // nameSite
				(String)testingData[i][5], // address
				(String) testingData[i][6], // placard
				((String)testingData[i][7]), // start
				((String)testingData[i][8]), // end
				(Class<?>) testingData[i][9]);
		}
	}

	private void crearYEditarEventosTemplate(String boss, String center, String title,
			String description, String nameSite, String address, String placard,
			String start, String end, Class<?> expected) {
		Class<?> caught;
		caught = null;

		try {
			super.authenticate(boss);
			
			final int centerId = this.getEntityId(center);
			final Center centerFinal = this.centerService.findOne(centerId);
			
			DateFormat df = new SimpleDateFormat("dd/MM/aa hh:mm");
			Date startDate = df.parse(start);
			Date endDate = df.parse(end);
			
			Event event = this.eventService.create();
			event.setTitle(title);
			event.setDescription(description);
			event.setNameSite(nameSite);
			event.setAddress(address);
			event.setPlacard(placard);
			event.setStartDate(startDate);
			event.setEndDate(endDate);
			event.setCenter(centerFinal);
			this.eventService.save(event);
			
			super.unauthenticate();
			this.eventService.flush();
		}catch (final Throwable oops) {
			caught = oops.getClass();
		}
		super.checkExceptions(expected, caught);
		
	}

	//e. listar y eliminar eventos
	// Todos los usurios, tanto autenticados como no, pueden listar eventos,
	// por lo que no existe un caso de uso negativo para listar eventos.
	@Test
	public void listarYEliminarEventos(){
		final Object testingData [][] = {
				// Positive
//				{"boss1", "event1", null},
				// Negative
//				{"boss1", "event2", IllegalArgumentException.class}, // no se puede eliminar un evento que no sea suyo
//				{"employee", "event1", IllegalArgumentException.class}, // usuario no válido para eliminar
		};
		for (int i = 0; i < testingData.length; i++){
			this.listarYEliminarEventosTemplate((String) testingData[i][0], // Username login
					(String) testingData[i][1], // event
					(Class<?>) testingData[i][2]);
		}
	}

	private void listarYEliminarEventosTemplate(String boss, String event,
			Class<?> expected) {
		Class<?> caught;
		caught = null;

		try {
			super.authenticate(boss);
			
			// listar:
			this.eventService.findAll();
			
			// eliminar:
			final int eventId = this.getEntityId(event);
			final Event eventFinal = this.eventService.findOne(eventId);
			
			this.eventService.delete(eventFinal);
			
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
				// Positive
//				{"boss1", "name", "description", "articles", "https://logo.com", "event1", null},
				// Negative
//				{"boss1", "name", "description", "articles", "logo", "event1", IllegalArgumentException.class}, // atributo mal
//				{"employee", "name", "description", "articles", "logo", "event1", IllegalArgumentException.class} // usuario no válido
		};
		for (int i = 0; i < testingData.length; i++){
			this.crearYEditarEmpresasTemplate((String) testingData[i][0], // Username login
					(String) testingData[i][1], // name
					(String) testingData[i][2], // description
					(String) testingData[i][3], // articles
					(String) testingData[i][4], // logo
					(String) testingData[i][5], // event
					(Class<?>) testingData[i][6]);
		}
	}

	private void crearYEditarEmpresasTemplate(String boss, String event,
			String name, String description, String articles, String logo, Class<?> expected) {
		Class<?> caught;
		caught = null;

		try {
			super.authenticate(boss);
			
			final int eventId = this.getEntityId(event);
			final Event eventFinal = this.eventService.findOne(eventId);
			
			Company company = this.companyService.create();
			company.setName(name);
			company.setDescription(description);
			company.setArticles(articles);
			company.setLogo(logo);
			company.setEvent(eventFinal);
			this.companyService.save(company);
			
			super.unauthenticate();
			this.companyService.flush();
		}catch (final Throwable oops) {
			caught = oops.getClass();
		}
		super.checkExceptions(expected, caught);
		
	}
	
	//f. listar y eliminar empresas
	@Test
	public void listarYEliminarEmpresas(){
		final Object testingData [][] = {
				// Positive
//				{"boss1", "company1", null},
				// Negative
//				{"admin", "company1", IllegalArgumentException.class}, // usuario no válido
//				{"employee", "company1", IllegalArgumentException.class}, // usuario no válido
		};
		for (int i = 0; i < testingData.length; i++){
			this.listarYEliminarEmpresasTemplate((String) testingData[i][0], // Username login
					(String) testingData[i][1], // company
					(Class<?>) testingData[i][2]);
		}
	}

	private void listarYEliminarEmpresasTemplate(String boss, String company,
			Class<?> expected) {
		Class<?> caught;
		caught = null;

		try {
			super.authenticate(boss);
			
			// listar:
			this.companyService.findAll();
			
			// eliminar:
			final int companyId = this.getEntityId(company);
			final Event companyFinal = this.eventService.findOne(companyId);
			
			this.eventService.delete(companyFinal);
			
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
//				{"boss1", "234", "fliers", "true", "company1", "employee1", null},
				// Negative
//				{"boss1", "234", "fliers", "true", "", "employee1", IllegalArgumentException.class}, // company null
//				{"boss1", "", "fliers", "true", "company1", "employee1", null}, // atributo vacío 
		};
		for (int i = 0; i < testingData.length; i++){
			this.crearYEditarStandsTemplate((String) testingData[i][0], // Username login
					(Integer)Integer.parseInt((String) testingData[i][1]), // numMaxVoluntaries
					(String) testingData[i][2], // fliers
					(Boolean)Boolean.parseBoolean((String) testingData[i][3]), // isOfCompany
					(String) testingData[i][4], // company
					(String) testingData[i][5], // employee
					(Class<?>) testingData[i][6]);
		}
	}

	private void crearYEditarStandsTemplate(String boss, Integer numMaxVoluntaries,
			String fliers, Boolean isOfCompany, String company, String employee, Class<?> expected) {
		Class<?> caught;
		caught = null;

		try {
			super.authenticate(boss);
			
			final int companyId = this.getEntityId(company);
			final int employeeId = this.getEntityId(employee);
			final Company companyFinal = this.companyService.findOne(companyId);
			final Employee employeeFinal = this.employeeService.findOne(employeeId);
			
			Stand stand = this.standService.create();
			stand.setNumMaxVoluntaries(numMaxVoluntaries);
			stand.setFliers(fliers);
			stand.setIsOfCompany(isOfCompany);
			stand.setCompany(companyFinal);
			stand.setEmployee(employeeFinal);
			this.standService.save(stand);
			
			
			super.unauthenticate();
			this.standService.flush();
		}catch (final Throwable oops) {
			caught = oops.getClass();
		}
		super.checkExceptions(expected, caught);
	}
	
	//g. listar y eliminar stands
	@Test
	public void listarYEliminarStands(){
		final Object testingData [][] = {
				// Positive
//				{"boss1", "stand1", null},
				// Negative
//				{"admin", "stand1", IllegalArgumentException.class}, // usuario no valido
//				{"employee", "stand1", IllegalArgumentException.class}, // usuario no valido 
		};
		for (int i = 0; i < testingData.length; i++){
			this.listarYEliminarStandsTemplate((String) testingData[i][0], // Username login
					(String) testingData[i][1],
					(Class<?>) testingData[i][2]);
		}
	}

	private void listarYEliminarStandsTemplate(String boss, String stand, Class<?> expected) {
		Class<?> caught;
		caught = null;

		try {
			super.authenticate(boss);
			
			// listar:
			this.standService.findAll();
			
			// eliminar:
			final int standId = this.getEntityId(stand);
			final Stand standFinal = this.standService.findOne(standId);
			
			this.standService.delete(standFinal);
			
			super.unauthenticate();
			this.standService.flush();
		}catch (final Throwable oops) {
			caught = oops.getClass();
		}
		super.checkExceptions(expected, caught);
	}
}
