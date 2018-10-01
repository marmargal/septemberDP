package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Brid;

@Repository
public interface BridRepository extends JpaRepository<Brid, Integer> {
	
	@Query("select r.brids from Route r where r.id = ?1")
	Collection<Brid> findBridsByRoute(int id);
	
	@Query("select c from Brid c where c.approved = null and c.route != null")
	Collection<Brid> findBridsWithoutDecision();
	
	@Query("select u.brids from User u where u.id = ?1")
	Collection<Brid> findBridsByUser(int id);

}
