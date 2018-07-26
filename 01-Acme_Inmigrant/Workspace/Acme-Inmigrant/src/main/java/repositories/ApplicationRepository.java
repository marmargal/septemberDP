package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Application;

@Repository
public interface ApplicationRepository extends JpaRepository<Application, Integer> {

	@Query("select a from Application a join a.immigrant i where i.id = ?1")
	Collection<Application> findApplicationByImmigrant(int immigrantId);

	@Query("select a from Officer o join o.decision d join d.application a where (d.accept=false and o.id=?1)")
	Application findApplicationRejected(int officerId);
	
	@Query("select p from Immigrant i join i.applications p where (p.closedMoment != null and i.id=?1)")
	Collection<Application> findApplicationClosed(int Id);
	
}
