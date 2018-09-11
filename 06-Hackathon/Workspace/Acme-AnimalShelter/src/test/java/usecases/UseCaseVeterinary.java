package usecases;

import java.util.Collection;

import javax.transaction.Transactional;

import junit.framework.Assert;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import services.MedicalReportService;
import services.PetService;
import utilities.AbstractTest;
import domain.Boss;
import domain.MedicalReport;
import domain.Pet;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring/junit.xml" })
@Transactional
public class UseCaseVeterinary extends AbstractTest {
	@Autowired
	private MedicalReportService medicalReportService;
	@Autowired
	private PetService petService;
	

	/*
	 * Caso de uso: Veterinary->Buscar animales que ya estén registrados. 15b
	 */

	
	@Test
	public void listPetWaitingAdoptionTest() {

		final Object testingData[][] = { {
				// Positive
				"veterinary1", null }, {
				// Negative: with admin that not exists
				"veterinary6", IllegalArgumentException.class }, {
				// Negative: with anonymous user
				"", IllegalArgumentException.class }, };

		for (int i = 0; i < testingData.length; i++)
			this.templateListPetTest(i, (String) testingData[i][0],
					(Class<?>) testingData[i][1]);
	}

	protected void templateListPetTest(final Integer i,
			final String principal, final Class<?> expected) {
		Class<?> caught;

		caught = null;
		try {
			super.authenticate(principal);

			final Collection<Pet> listPet = this.petService.findPetsPermitAdoption();

			Assert.assertNotNull(listPet);

		} catch (final Throwable oops) {
			caught = oops.getClass();
		}
		super.checkExceptions(expected, caught);
	}
	
	
	
	/*
	 * Caso de uso: Veterinary->Crear y editar informes médicos sobre animales. 15b
	 */

	@Test
	public void createMedicalReportTest() {

		final Object testingData[][] = {
				{
						// Positive
						"veterinary1", "medicalReport1","pet1", null },
				{
						// Negative: employee try to create a medical report
						"employee1", "medicalReport1", "pet1",
						IllegalArgumentException.class },
				{
						// Negative: veterinary try to create a medicalReport without pet
						"veterinary1", "medicalReport1","",
						NumberFormatException.class}
						};

		for (int i = 0; i < testingData.length; i++)
			this.templateCreateMedicalReportTest(i, (String) testingData[i][0],
					(String) testingData[i][1], (String) testingData[i][2],
					(Class<?>) testingData[i][3]);
	}

	protected void templateCreateMedicalReportTest(final Integer i,
			final String principal, final String veterinary, final String pet,
			final Class<?> expected) {
		Class<?> caught;

		caught = null;
		try {
			super.authenticate(principal);
			int petId =super.getEntityId(pet);

			final MedicalReport medicalReportBD = this.medicalReportService.create(petId);
			medicalReportBD.setDiagnosis("ninguno");
			medicalReportBD.setVersion(1);

			this.medicalReportService.flush();

		} catch (final Throwable oops) {
			caught = oops.getClass();
		}
		super.checkExceptions(expected, caught);

	}

	
}
