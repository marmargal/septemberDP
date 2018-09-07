package services;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.PetRepository;
import domain.Application;
import domain.MedicalReport;
import domain.Pet;

@Service
@Transactional
public class PetService {

	// Managed repository

	@Autowired
	private PetRepository petRepository;

	// Suporting services

	@Autowired
	private MedicalReportService medicalReportService;

	@Autowired
	private ApplicationService applicationService;

	// Constructors

	public PetService() {
		super();
	}

	// Simple CRUD methods

	public Pet create() {
		Pet res = new Pet();
		System.out.println(this.generatedIdentifier());
		res.setIdentifier(this.generatedIdentifier());
		return res;

	}

	public Collection<Pet> findAll() {
		Collection<Pet> res;
		res = petRepository.findAll();
		Assert.notNull(res);
		return res;
	}

	public Pet findOne(int petId) {
		Assert.isTrue(petId != 0);
		Pet res;
		res = petRepository.findOne(petId);
		Assert.notNull(res);
		return res;
	}

	public Pet save(Pet pet) {
		Pet res;
		String s = pet.getIdentifier();
		String age = pet.getAge().toString();
		if (pet.getAge() < 10) {
			age = "0" + age;
		}
		s = s.replace(s.substring(7,9), age);
		pet.setIdentifier(s);
		res = petRepository.save(pet);
		return res;
	}

	public void delete(Pet pet) {
		Assert.notNull(pet);
		Assert.isTrue(pet.getId() != 0);
		Assert.isTrue(petRepository.exists(pet.getId()));

		// Eliminamos sus relaciones
		MedicalReport medicalReport = pet.getMedicalReport();
		if (medicalReport != null) {
			this.medicalReportService.delete(medicalReport);
		}

		for (Application application : pet.getApplication()) {
			if (application != null) {
				this.applicationService.delete(application);
			}
		}

		petRepository.delete(pet);
	}

	// Other business methods

	public Set<Pet> findPetsWaitingAdoption() {
		Set<Pet> pets = new HashSet<Pet>();
		pets = this.petRepository.findPetsWaitingAdoption();
		for (Pet pet : pets) {
			if (!pet.getApplication().isEmpty()) {
				for (Application app : pet.getApplication()) {
					if (app.getReport() != null
							&& app.getReport().getSuitable()) {
						pets.remove(pet);
					}
				}
			}
		}
		return pets;
	}

	public Collection<Pet> findPetsPermitAdoption() {
		Collection<Pet> pets = new ArrayList<Pet>();
		pets = this.petRepository.findPetsPermitAdoption();
		ArrayList<Pet> pList = new ArrayList<>();
		for (Pet pet : pets) {
			if (!pet.getApplication().isEmpty()) {
				for (Application app : pet.getApplication()) {
					if (app.getReport() != null
							&& app.getReport().getSuitable()) {
						pList.add(pet);
					}
				}
			}
		}
		pets.removeAll(pList);
		return pets;
	}

	public Collection<Pet> findPetsByCenter(int centerId) {
		Collection<Pet> pets = new ArrayList<Pet>();
		pets = this.petRepository.findPetsByCenter(centerId);
		return pets;
	}

	public String generatedIdentifier() {
		String identifier;
		String letters;
		String numbers;
		Random r;

		letters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		numbers = "0123456789";
		r = new Random();

		final Date date = new Date(System.currentTimeMillis() - 1);
		final SimpleDateFormat dt = new SimpleDateFormat("ddMMyy");
		
		identifier = dt.format(date).toString() + "-";
		for (int i = 0; i < 2; i++)
			identifier = identifier
					+ numbers.charAt(r.nextInt(numbers.length()));
		identifier = identifier + "-";
		for (int i = 0; i < 4; i++)
			identifier = identifier
					+ letters.charAt(r.nextInt(letters.length()));

		return identifier;
	}
}
