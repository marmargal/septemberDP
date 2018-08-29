package repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import domain.Pet;

@Repository
public interface PetRepository extends JpaRepository<Pet, Integer>{

}
