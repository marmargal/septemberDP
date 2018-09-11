package usecases;

import javax.validation.ConstraintViolationException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import domain.Center;
import domain.Warehouse;

import services.BossService;
import services.CenterService;
import services.WarehouseService;
import utilities.AbstractTest;

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
//	@Test
//	public void deleteCenter(){
//		final Object testingData [][] = {
//				// Positive
//				{"boss1", "center1", null},
//				// Negative
//				{"boss2", "center1", IllegalArgumentException.class}, // no es el boss del center que se quiere editar
//				{"veterinary", "center1", IllegalArgumentException.class}, // usurio no válido
//		};
//		for (int i = 0; i < testingData.length; i++)
//			this.deleteCenterTemplate((String) testingData[i][0], // Username login
//				(String) testingData[i][1], // centro
//				(Class<?>) testingData[i][2]);
//	}
//
//	private void deleteCenterTemplate(final String boss, final String center,
//			 final Class<?> expected) {
//
//		Class<?> caught;
//		caught = null;
//
//		try {
//			super.authenticate(boss);
//			
//			final int centerId = this.getEntityId(center);
//			final Center centerFinal = this.centerService.findOne(centerId);
//			
//			this.centerService.delete(centerFinal);
//			
//			super.unauthenticate();
//			this.centerService.flush();
//		}catch (final Throwable oops) {
//			caught = oops.getClass();
//		}
//		super.checkExceptions(expected, caught);
//	}
	
	
	// editar almacen 17.b
	@Test
	public void editWarehouse(){
		final Object testingData [][] = {
				// Positive
				{"boss1", "warehouse1", "23456", null},
				// Negative
				{"boss2", "warehouse2", "-1", ConstraintViolationException.class}, // atributo mal
//				{"admin", "warehouse1", "234567", IllegalArgumentException.class}, // usurio no válido
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
			this.centerService.flush();
		}catch (final Throwable oops) {
			caught = oops.getClass();
		}
		super.checkExceptions(expected, caught);
	}
}
