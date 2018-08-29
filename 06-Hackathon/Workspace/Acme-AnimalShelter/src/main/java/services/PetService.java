package services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.PetRepository;
import domain.Pet;


@Service
@Transactional
public class PetService {

	// Managed repository

	@Autowired
	private PetRepository petRepository;

	// Suporting services

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
		petRepository.delete(pet);
	}

	// Other business methods
	
	

}
