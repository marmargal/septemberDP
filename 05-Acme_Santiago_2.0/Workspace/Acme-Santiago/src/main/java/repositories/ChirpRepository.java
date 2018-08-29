package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Chirp;

@Repository
public interface ChirpRepository extends JpaRepository<Chirp, Integer>{
	
	@Query("select c from Chirp c where c.user.id=?1")
	Collection<Chirp> findChirpByUser(int id);
	
	@Query("select c from Chirp c where c.taboo=true")
	Collection<Chirp> findChirpTaboo();
	
}
