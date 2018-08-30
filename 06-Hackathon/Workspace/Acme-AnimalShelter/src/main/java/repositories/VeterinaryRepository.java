package repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Veterinary;

@Repository
public interface VeterinaryRepository extends JpaRepository<Veterinary, Integer>{

	@Query("select v from Veterinary v join v.userAccount ua where ua.id=?1")
	Veterinary findVeterinaryByPrincipal(int uA);
}
