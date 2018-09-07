package repositories;

import java.util.Collection;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Pet;

@Repository
public interface PetRepository extends JpaRepository<Pet, Integer>{
	
	@Query("select p from Pet p join p.application a where a.closed=false")
	Set<Pet> findPetsWaitingAdoption();
	
	@Query("select p from Pet p where p.status=true")
	Collection<Pet> findPetsPermitAdoption();
	
	@Query("select p from Pet p join p.center c where c.id=?1")
	Collection<Pet> findPetsByCenter(int centerId);
}
