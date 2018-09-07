package services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import org.joda.time.LocalDate;
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
		
		//Eliminamos sus relaciones
		MedicalReport medicalReport = pet.getMedicalReport();
		if(medicalReport != null){
			this.medicalReportService.delete(medicalReport);
		}
		
		for(Application application: pet.getApplication()){
			if(application != null){
				this.applicationService.delete(application);
			}
		}
				
		petRepository.delete(pet);
	}

	// Other business methods
	
	public Set<Pet> findPetsWaitingAdoption(){
		Set<Pet> pets = new HashSet<Pet>();
		pets = this.petRepository.findPetsWaitingAdoption();
		return pets;
	}
	
	public Collection<Pet> findPetsPermitAdoption(){
		Collection<Pet> pets = new ArrayList<Pet>();
		pets = this.petRepository.findPetsPermitAdoption();
		return pets;
	}
	
	public Collection<Pet> findPetsByCenter(int centerId){
		Collection<Pet> pets = new ArrayList<Pet>();
		pets = this.petRepository.findPetsByCenter(centerId);
		return pets;
	}
	
	public String generatedIdentifier() {
		String identifier;
		LocalDate date;
		String letters;
		String numbers;
		Random r;
		
		letters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		numbers = "0123456789";
		r = new Random();
		date = new LocalDate();
		
		identifier = String.valueOf(date.getYear() % 100 < 10 ? "0" + date.getYear() : date.getYear() % 100) + 
					String.valueOf(date.getMonthOfYear() < 10 ? "0" + date.getMonthOfYear() : date.getMonthOfYear())
					+ String.valueOf(date.getDayOfMonth() < 10 ? "0" + date.getDayOfMonth() : date.getDayOfMonth()) + "-";
		for (int i = 0; i < 2; i++)
			identifier = identifier + numbers.charAt(r.nextInt(numbers.length()));
		identifier = identifier + "-";
		for (int i = 0; i < 4; i++)
			identifier = identifier + letters.charAt(r.nextInt(letters.length()));
		
		
		return identifier;
	}
}
