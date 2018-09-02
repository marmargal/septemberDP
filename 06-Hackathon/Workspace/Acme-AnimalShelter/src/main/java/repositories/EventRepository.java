package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Event;

@Repository
public interface EventRepository extends JpaRepository<Event, Integer>{
	
	@Query("select e from Event e join e.center c where c.id=?1")
	Collection<Event> findEventByCenter(int centerId);
}
