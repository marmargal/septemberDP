package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Application;
import domain.Center;

@Repository
public interface ApplicationRepository extends JpaRepository<Application, Integer>{
	
	@Query("select a from Application a where a.closed=false")
	Collection<Application> findApplicationsPending();
	
	@Query("select a from Application a join a.pet p where a.closed=false and p.center=?1")
	Collection<Application> findApplicationsPendingPerCentre(Center c);
	
	@Query("select a from Application a join a.client c where c.ban=true")
	Collection<Application> findApplicationsClientBan();
}
