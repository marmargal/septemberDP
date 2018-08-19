package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Inn;


@Repository
public interface InnRepository extends JpaRepository<Inn, Integer>{
	
	@Query("select i from Inn i join i.creditCard c where c.expired=false")
	Collection<Inn> findCcExpiration();
	

	
}
