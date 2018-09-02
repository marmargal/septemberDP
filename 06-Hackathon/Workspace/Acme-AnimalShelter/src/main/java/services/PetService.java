package services;

import java.util.ArrayList;
import java.util.Collection;

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
		
		Application application = pet.getApplication();
		if(application != null){
			this.applicationService.delete(application);
		}
				
		petRepository.delete(pet);
	}

	// Other business methods
	
	public Collection<Pet> findPetsWaitingAdoption(){
		Collection<Pet> pets = new ArrayList<Pet>();
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
	

}
