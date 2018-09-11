package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Inn;


@Repository
public interface InnRepository extends JpaRepository<Inn, Integer>{
	
	
	@Query("select i from Inn i join i.creditCard c where c.expirationYear>=?1 OR (c.expirationYear=?1 AND c.expirationMonth>=?2)")
	Collection<Inn> findCcExpirationYear(int year, int month);
	
	@Query("select i from Inn i join i.creditCard c where c.expirationMonth>=?1")
	Collection<Inn> findCcExpirationMonth( int month);
	

	
}
