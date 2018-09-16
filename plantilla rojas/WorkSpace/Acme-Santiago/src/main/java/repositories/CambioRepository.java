package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Cambio;

@Repository
public interface CambioRepository extends JpaRepository<Cambio, Integer> {

	@Query("select c from Cambio c where c.decision=null")
	Collection<Cambio> cambiosWithoutDecision();

}
