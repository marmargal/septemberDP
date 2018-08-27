package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Compostela;

@Repository
public interface CompostelaRepository extends JpaRepository<Compostela, Integer>{

	
	@Query("select c from Compostela c where c.finallyDecision = ?1")
	Collection<Compostela> findCompostelaByFinallyDecision(boolean decision);
}
