package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Pet;

@Repository
public interface PetRepository extends JpaRepository<Pet, Integer>{
	
	@Query("select p from Pet p join p.application a where a.closed=false")
	Collection<Pet> findPetsWaitingAdoption();
	
	@Query("select p from Pet p where p.status=true")
	Collection<Pet> findPetsPermitAdoption();
}
