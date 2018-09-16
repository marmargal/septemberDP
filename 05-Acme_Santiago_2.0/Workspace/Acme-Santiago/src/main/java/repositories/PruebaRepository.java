package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Prueba;

@Repository
public interface PruebaRepository extends JpaRepository<Prueba, Integer> {

	@Query("select p from Prueba p where p.user.id = ?1")
	Collection<Prueba> findPruebasByUser(int userId);
	
	@Query("select p from Prueba p where p.decision = null")
	Collection<Prueba> findPruebasWithoutDecision();
	
	@Query("select p from Prueba p where p.route.id = ?1 and p.decision = null")
	Collection<Prueba> findPruebasByRouteAndWithoutDecision(int routeId);
	
//	@Query("select ")
//	Collection<Prueba> findPruebas...();

}
