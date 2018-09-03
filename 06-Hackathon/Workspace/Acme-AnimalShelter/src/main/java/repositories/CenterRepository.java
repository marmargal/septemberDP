package repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Center;

@Repository
public interface CenterRepository extends JpaRepository<Center, Integer>{
	
	@Query("select c from Pet p join p.center c where p.id=?1")
	Center findByPet(int petId);
}
