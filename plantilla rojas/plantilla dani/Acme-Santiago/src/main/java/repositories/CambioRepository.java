package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Cambio;

@Repository
public interface CambioRepository extends JpaRepository<Cambio, Integer> {
	
	@Query("select r.cambios from Route r where r.id = ?1")
	Collection<Cambio> findCambiosByRoute(int id);
	
	@Query("select c from Cambio c where c.approved = null and c.route != null")
	Collection<Cambio> findCambiosWithoutDecision();
	
	@Query("select u.cambios from User u where u.id = ?1")
	Collection<Cambio> findCambiosByUser(int id);

}
