package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Application;

@Repository
public interface ApplicationRepository extends JpaRepository<Application, Integer>{
	
	@Query("select a from Application a where a.closed=false")
	Collection<Application> findApplicationsPending();
	
	@Query("select a from Application a join a.client c where c.ban=true")
	Collection<Application> findApplicationsClientBan();
}
