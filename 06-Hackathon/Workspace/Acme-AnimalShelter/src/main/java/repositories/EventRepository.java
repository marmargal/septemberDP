package repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import domain.Event;

@Repository
public interface EventRepository extends JpaRepository<Event, Integer>{

}
